package cc.jktu.gis.controller;

import cc.jktu.gis.model.schema.CommonResp;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
@RequiredArgsConstructor
public class GlobalErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping
    public CommonResp error(HttpServletRequest request) {
        final Throwable ex = errorAttributes.getError(new ServletWebRequest(request));
        final HttpStatus httpStatus = getHttpStatus(request);
        return CommonResp.builder().message(ex != null ? ex.getLocalizedMessage() : httpStatus.name()).build();
    }

    protected HttpStatus getHttpStatus(HttpServletRequest request) {
        final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return HttpStatus.valueOf(((Integer) status));
    }

}
