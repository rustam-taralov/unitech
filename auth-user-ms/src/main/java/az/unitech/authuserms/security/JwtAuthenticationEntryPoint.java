package az.unitech.authuserms.security;

import az.unitech.authuserms.dtos.response.CommonResponse;
import az.unitech.authuserms.exception.BusinessStatus;
import az.unitech.authuserms.utils.Translator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Translator translator;

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(Translator translator, ObjectMapper objectMapper) {
        this.translator = translator;
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setHeader("access-control-allow-origin", "*");
        response.getWriter().write(
                objectMapper
                        .writeValueAsString(CommonResponse.of(BusinessStatus.BAD_CREDENTIAL.code(), translator
                                .toLocale(BusinessStatus.BAD_CREDENTIAL.messageKey()))
                        )
        );
    }
}
