package az.unitech.accountms.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PageUtils {

    public static Pageable getPage(){
        Optional<String> page = HttpContextUtil.getParamValueByName("page");
        Optional<String> size = HttpContextUtil.getParamValueByName("size");
        if(page.isPresent()) {
            Integer intSize= size.map(Integer::parseInt).orElse(10);
            return PageRequest.of(Integer.parseInt(page.get()) - 1, intSize);
        }

        return Pageable.unpaged();
    }
}
