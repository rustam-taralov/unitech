package az.unitech.accountms.exception;


import az.unitech.accountms.dtos.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse> catchPasswordException(BusinessException e) {
        return ResponseEntity
                .status(e.status())
                .body(
                        CommonResponse.of(
                                e.code(),
                                e.getMessage()
                        )
                );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<?>> catchMethodArgumentExceptionException(MethodArgumentNotValidException e) {
        var resultMap= e.getBindingResult().getAllErrors()
                .stream()
                .map(err -> err.unwrap(ConstraintViolation.class))
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return ResponseEntity.badRequest().body(CommonResponse.error(resultMap));
    }
}
