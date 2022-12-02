package az.unitech.accountms.security.service;

public class AuthResponse {

    private Integer code;
    private String message;

    private AuthData data;
    private String currentTime;

    public Boolean isSuccess(){
        return code==1;
    }

    public Integer getCode() {
        return code;
    }

    public AuthResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AuthResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public AuthData getData() {
        return data;
    }

    public AuthResponse setData(AuthData data) {
        this.data = data;
        return this;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public AuthResponse setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
        return this;
    }
}
