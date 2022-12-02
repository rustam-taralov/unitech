package az.unitech.authuserms.exception;

import org.springframework.http.HttpStatus;

public enum BusinessStatus {

    ILLEGAL_DATA(-422,"illegal-data",HttpStatus.UNPROCESSABLE_ENTITY),
    NO_SUCH_USER(-423,"no-such-user",HttpStatus.NOT_FOUND),
    PIN_NULL(-424,"pin-null",HttpStatus.NOT_FOUND),
    ROLE_NULL(-425,"role-null",HttpStatus.NOT_FOUND),
    ID_CAN_NOT_BE_NULL(-426,"id-is-null",HttpStatus.NOT_ACCEPTABLE),
    USER_ALREADY_EXIST(-427,"user-already-exist",HttpStatus.NOT_FOUND),
    BAD_CREDENTIAL(-428,"bad-credential",HttpStatus.UNAUTHORIZED),
    NO_SUCH_ROLE(-429,"no-such-role",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(-431,"invalid-token",HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(-11,"token-expired",HttpStatus.UNAUTHORIZED),
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
