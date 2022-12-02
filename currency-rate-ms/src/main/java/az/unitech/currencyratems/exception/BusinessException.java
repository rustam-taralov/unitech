package az.unitech.currencyratems.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{
    private Integer code;
    private HttpStatus status;

    public BusinessException(String message, Integer code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public Integer code() {
        return code;
    }

    public HttpStatus status() {
        return status;
    }
}
