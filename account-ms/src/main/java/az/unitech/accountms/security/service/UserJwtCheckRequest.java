package az.unitech.accountms.security.service;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserJwtCheckRequest {

    @NotBlank
    private String jwt;
}
