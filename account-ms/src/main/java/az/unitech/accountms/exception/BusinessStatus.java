package az.unitech.accountms.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum BusinessStatus {

    ILLEGAL_DATA(-422,"illegal-data",HttpStatus.UNPROCESSABLE_ENTITY),
    ACCOUNT_BELONG_TO_PIN(-435, "account-belong-pin", HttpStatus.UNAUTHORIZED),
    PIN_IS_NULL(-432,"pin-is-null",HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BALANCE(-433,"not-enough-balance",HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(-434,"account-not-found",HttpStatus.BAD_REQUEST),
    ACCOUNT_SAME(-437,"account-same",HttpStatus.BAD_REQUEST),
    ACCOUNT_BLOCKED(-435,"account-blocked",HttpStatus.BAD_REQUEST),
    ACCOUNT_NUMBER_IS_NULL(-436, "account-is-null", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(-500,"internal-server-error",HttpStatus.INTERNAL_SERVER_ERROR);

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

    public static BusinessStatus getByCode(Integer code){
        return Arrays.stream(BusinessStatus.values())
                .filter(businessStatus -> businessStatus.code().equals(code))
                .findAny().orElse(BusinessStatus.INTERNAL_SERVER_ERROR);
    }
}
