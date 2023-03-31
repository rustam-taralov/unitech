package az.unitech.authuserms.controller;

import az.unitech.authuserms.dtos.request.LoginRequest;
import az.unitech.authuserms.dtos.request.UserJwtCheckRequest;
import az.unitech.authuserms.dtos.request.UserRegistrationRequest;
import az.unitech.authuserms.dtos.response.CommonResponse;
import az.unitech.authuserms.dtos.response.JwtCheckResponse;
import az.unitech.authuserms.dtos.response.LoginResponse;
import az.unitech.authuserms.dtos.response.RefreshResponse;
import az.unitech.authuserms.service.AuthService;
import az.unitech.authuserms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;
    @PostMapping("login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        var tokens = authService.loginUser(request);
        return ResponseEntity.ok(CommonResponse.success(tokens));
    }

    @PostMapping("register/user")
    public ResponseEntity<CommonResponse<?>> registerUser(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest){
        userService.registerUser(userRegistrationRequest);
        return ResponseEntity.ok(CommonResponse.success("User added successfully"));
    }

    @PostMapping("refresh")
    public ResponseEntity<CommonResponse<RefreshResponse>> refresh(@RequestHeader("Authorization") String refreshToken) {

        var  refresh = authService.refresh(refreshToken);

        if(StringUtils.isBlank(refresh.getAccessToken())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(
                            CommonResponse.of(-3,"Can't be authenticated")
                    );
        }

        return ResponseEntity.ok(CommonResponse.success(refresh));
    }

    @PostMapping("check")
    public ResponseEntity<CommonResponse<JwtCheckResponse>> checkJwt(@RequestBody @Valid UserJwtCheckRequest request){
        CommonResponse<JwtCheckResponse> jwtCheckResponseCommonResponse = authService.validateJwt(request);
        return ResponseEntity.ok(jwtCheckResponseCommonResponse);
    }

}
