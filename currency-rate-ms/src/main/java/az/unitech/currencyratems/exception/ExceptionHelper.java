package az.unitech.currencyratems.exception;

import az.unitech.currencyratems.utils.Translator;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHelper {
    private final Translator translator;

    public ExceptionHelper(Translator translator) {
        this.translator = translator;
    }

    public void throwException(BusinessStatus businessStatus){
        throw new BusinessException(
                translator.toLocale(businessStatus.messageKey()),
                businessStatus.code(),
                businessStatus.status()
        );
    }

    public BusinessException getException(BusinessStatus businessStatus){
        return new BusinessException(
                translator.toLocale(businessStatus.messageKey()),
                businessStatus.code(),
                businessStatus.status()
        );
    }
}
