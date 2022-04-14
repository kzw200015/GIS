package cc.jktu.gis.config;

import cc.jktu.gis.model.constant.Role;
import cc.jktu.gis.model.schema.CommonResp;
import cc.jktu.gis.security.JsonUsernamePasswordAuthenticationFilter;
import cc.jktu.gis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        final JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(this::handleSuccess);
        filter.setAuthenticationFailureHandler(this::handleException);
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .logout(logout -> {
                    logout.logoutUrl("/api/logout");
                    logout.invalidateHttpSession(true);
                    logout.logoutSuccessHandler(this::handleSuccess);
                })
                .exceptionHandling(exceptionHanding -> {
                    exceptionHanding.authenticationEntryPoint(this::handleException);
                    exceptionHanding.accessDeniedHandler(this::handleException);
                }).authorizeRequests(authorizeRequests -> {
                    authorizeRequests.mvcMatchers("/api/login", "/api/register", "/api/ws/**").permitAll();
                    authorizeRequests.mvcMatchers("/api/admin/**").hasRole(Role.ADMIN.name());
                    authorizeRequests.mvcMatchers("/api/**").authenticated();
                    authorizeRequests.anyRequest().permitAll();
                }).addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    protected void handleException(HttpServletRequest request, HttpServletResponse response, RuntimeException exception) throws IOException {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (exception instanceof AccessDeniedException) {
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (exception instanceof AuthenticationException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        response.sendError(httpStatus.value(), exception.getLocalizedMessage());
    }

    protected void handleSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final CommonResp commonResp = CommonResp.builder().build();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        final PrintWriter responseWriter = response.getWriter();
        responseWriter.write(commonResp.toJson());
        responseWriter.flush();
        responseWriter.close();
    }

}
