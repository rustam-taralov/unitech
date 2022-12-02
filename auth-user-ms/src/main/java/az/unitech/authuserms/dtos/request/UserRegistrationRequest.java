package az.unitech.authuserms.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationRequest {

    @NotBlank
    private String pin;
    @NotBlank
    private String password;

}
