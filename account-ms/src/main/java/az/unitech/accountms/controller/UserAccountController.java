package az.unitech.accountms.controller;

import az.unitech.accountms.dtos.dto.UserAccountDto;
import az.unitech.accountms.dtos.response.CommonResponse;
import az.unitech.accountms.security.service.AuthService;
import az.unitech.accountms.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/account")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/{pin}")
    @PreAuthorize("@authService.validateJwtAll(#jwtToken,'USER')")
    public ResponseEntity<CommonResponse<List<UserAccountDto>>> getAccountByPin(@PathVariable("pin") String pin,
                                                                                @RequestHeader("Authorization") String jwtToken){
        var userAccountList= userAccountService.getAllAccountByPin(pin);
        return ResponseEntity.ok(CommonResponse.success(userAccountList));
    }
}
