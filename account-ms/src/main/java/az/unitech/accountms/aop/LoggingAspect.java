package az.unitech.accountms.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger;

    private final ObjectMapper mapper;

    public LoggingAspect(Logger logger, ObjectMapper mapper) {
        this.logger = logger;
        this.mapper = mapper;
    }

    @Pointcut("@annotation(az.unitech.authuserms.annotations.Logable)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logMethod(JoinPoint pjp) {
        Map<String, Object> parameters = getParameters(pjp);
        try {
            logger.info("Method name: {}, values: {}",pjp.getSignature().getName(), mapper.writeValueAsString(parameters));
        } catch (JsonProcessingException e) {
            logger.error("Error while converting", e);
        }
    }

    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        HashMap<String, Object> map = new HashMap<>();

        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }

}
