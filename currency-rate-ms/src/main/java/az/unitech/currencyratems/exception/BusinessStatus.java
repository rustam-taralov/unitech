package az.unitech.currencyratems.exception;

import org.springframework.http.HttpStatus;

public enum BusinessStatus {

    ILLEGAL_DATA(-422,"illegal-data",HttpStatus.UNPROCESSABLE_ENTITY),
    FORBIDDEN_CURRENCY(-423,"forbidden-currency", HttpStatus.FORBIDDEN),
    CURRENCY_NOT_FOUND(-424,"currency-not-found", HttpStatus.NOT_FOUND)
    ;

    private Integer code;
    private String messageKey;
    private HttpStatus status;

    BusinessStatus(Integer code, String messageKey, HttpStatus status) {
        this.code = code;
        this.messageKey = messageKey;
        this.status = status;
    }

    public Integer code() {
        return code;
    }

    public String messageKey() {
        return messageKey;
    }

    public HttpStatus status() {
        return status;
    }
}
