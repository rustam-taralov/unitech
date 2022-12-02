package az.unitech.authuserms.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserJwtCheckRequest {

    @NotBlank
    private String jwt;

}
