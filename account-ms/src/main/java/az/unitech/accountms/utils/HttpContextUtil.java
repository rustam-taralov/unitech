package az.unitech.accountms.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class HttpContextUtil {

    public static Optional<ServletRequestAttributes> getServletRequestAttributes() {
        return Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()));
    }

    public static HttpServletRequest getHttpServletRequest() {
        Optional<ServletRequestAttributes> requestAttributes = getServletRequestAttributes();
        return requestAttributes.map(ServletRequestAttributes::getRequest)
                .orElse(null);
    }

    public static Optional<Enumeration<String>> getParameterNames() {
        HttpServletRequest servletRequest = getHttpServletRequest();
        return servletRequest == null ? Optional.empty() : Optional.ofNullable(servletRequest.getParameterNames());
    }

    public static Optional<String> getParamValueByName(String paramName) {
        HttpServletRequest servletRequest = getHttpServletRequest();
        return servletRequest == null ? Optional.empty() : Optional.ofNullable(servletRequest.getParameter(paramName));
    }

    public static Optional<List<String>> getParamValueArrayByName(String paramName) {
        HttpServletRequest servletRequest = getHttpServletRequest();
        return servletRequest == null ?
                Optional.empty() :
                Optional.ofNullable(List.of(servletRequest.getParameterValues(paramName)));
    }

    public static Optional<Map<String, String>> getParamKeyAndValueMap() {
        Optional<Enumeration<String>> parameterNames = getParameterNames();
        Map<String, String> paramNameAndValueMap = new HashMap<>();
        if(parameterNames.isPresent()) {
            paramNameAndValueMap = Collections.list(parameterNames.get())
                    .stream()
                    .collect(Collectors.toMap((p) -> p, (p) -> getParamValueByName(p).orElseGet(String::new)));
        }
        return Optional.of(paramNameAndValueMap);
    }
    public static Optional<Map<String, String[]>> getParamKeyAndValueMapArray() {
        return Optional.ofNullable(getHttpServletRequest().getParameterMap());
    }
}


