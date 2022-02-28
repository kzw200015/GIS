package cc.jktu.gis.controller;

import cc.jktu.gis.model.schema.CommonResp;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(@NonNull MappingJacksonValue bodyContainer, @NonNull MediaType contentType, @NonNull MethodParameter returnType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        Object body = bodyContainer.getValue();
        if (!CommonResp.class.isAssignableFrom(body.getClass())) {
            bodyContainer.setValue(CommonResp.builder().data(body).build());
        }
    }

}