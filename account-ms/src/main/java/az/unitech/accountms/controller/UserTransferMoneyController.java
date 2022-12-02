package az.unitech.accountms.controller;

import az.unitech.accountms.dtos.request.AccountToAccountRequest;
import az.unitech.accountms.dtos.response.CommonResponse;
import az.unitech.accountms.security.service.AuthService;
import az.unitech.accountms.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/transfer-money")
@RequiredArgsConstructor
public class UserTransferMoneyController {

    private final TransferService transferService;

    @PostMapping
    @PreAuthorize("@authService.validateJwtAll(#jwtToken,'USER')")
    public ResponseEntity<CommonResponse<?>> transferMoney(@RequestBody @Valid AccountToAccountRequest request,
                                                           @RequestHeader("Authorization") String jwtToken){
        transferService.transferMoney(request);
        return ResponseEntity.ok(CommonResponse.success("Money transfer success"));
    }


}
