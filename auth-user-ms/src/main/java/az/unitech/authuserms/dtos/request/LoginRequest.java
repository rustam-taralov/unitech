package az.unitech.authuserms.dtos.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class LoginRequest {

    @NotBlank
    private String pin;
    @NotEmpty
    private String password;

}
