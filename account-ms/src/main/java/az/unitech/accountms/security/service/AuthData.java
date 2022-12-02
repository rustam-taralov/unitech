package az.unitech.accountms.security.service;

import lombok.Data;

@Data
public class AuthData {
    private String pin;
    private String role;

}
