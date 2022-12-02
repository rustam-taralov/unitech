package az.unitech.accountms.dtos.response;

import java.time.LocalDateTime;

public class CommonResponse<T> {

    private Integer code;
    private String message;
    private T data;
    private LocalDateTime currentTime;

    public static CommonResponse of(Integer code, String message){
        return new CommonResponse<>()
                .setCode(code)
                .setMessage(message)
                .setCurrentTime(LocalDateTime.now());
    }

    public static<T>CommonResponse of(Integer code, String message, T data){
        return new CommonResponse<>()
                .setCode(code)
                .setMessage(message)
                .setCurrentTime(LocalDateTime.now());
    }

    public static<T>CommonResponse<T> success(T data){
        return new CommonResponse<T>()
                .setCode(1)
                .setMessage("success")
                .setData(data)
                .setCurrentTime(LocalDateTime.now());
    }

    public static<T>CommonResponse<T> success(String message){
        return new CommonResponse<T>()
                .setCode(1)
                .setMessage(message)
                .setCurrentTime(LocalDateTime.now());
    }

    public static<T>CommonResponse<T> error(T data){
        return new CommonResponse<T>()
                .setCode(-1)
                .setMessage("error")
                .setData(data)
                .setCurrentTime(LocalDateTime.now());
    }

    public static<T>CommonResponse<T> error(String message){
        return new CommonResponse<T>()
                .setCode(-1)
                .setMessage(message)
                .setCurrentTime(LocalDateTime.now());
    }

    public Integer getCode() {
        return code;
    }

    public CommonResponse<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public CommonResponse<T> setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
        return this;
    }
}
