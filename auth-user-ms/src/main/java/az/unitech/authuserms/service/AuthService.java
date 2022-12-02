package az.unitech.authuserms.service;


import az.unitech.authuserms.annotations.Logable;
import az.unitech.authuserms.dtos.request.LoginRequest;
import az.unitech.authuserms.dtos.request.UserJwtCheckRequest;
import az.unitech.authuserms.dtos.response.CommonResponse;
import az.unitech.authuserms.dtos.response.JwtCheckResponse;
import az.unitech.authuserms.dtos.response.LoginResponse;
import az.unitech.authuserms.dtos.response.RefreshResponse;
import az.unitech.authuserms.exception.BusinessStatus;
import az.unitech.authuserms.exception.ExceptionHelper;
import az.unitech.authuserms.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticateManager;

    private final ExceptionHelper exceptionHelper;

    private final JwtTokenUtil jwtTokenUtil;


    @Logable
    public LoginResponse loginUser(LoginRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getPin(), request.getPassword());
        Authentication authentication = authenticateManager.authenticate(token);

        if(!authentication.isAuthenticated())
            exceptionHelper.throwException(BusinessStatus.BAD_CREDENTIAL);

        var userByUserName = userService.getUserByPin(request.getPin());

        String accessToken = jwtTokenUtil.generateAccessToken(userByUserName);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userByUserName);

        return LoginResponse.of(accessToken,refreshToken);
    }

    @Logable
    public RefreshResponse refresh(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            if (jwtTokenUtil.validate(refreshToken)) {
                var userByUserName = userService.getUserByPin(jwtTokenUtil.getUserName(refreshToken));

                String accessToken = jwtTokenUtil.generateAccessToken(userByUserName);

                return RefreshResponse.of(accessToken);
            }
        }

        return RefreshResponse.builder().build();
    }

    @Logable
    public CommonResponse<JwtCheckResponse> validateJwt(UserJwtCheckRequest userJwtCheckRequest){

        if(userJwtCheckRequest==null|| StringUtils.isBlank(userJwtCheckRequest.getJwt()))
            exceptionHelper.throwException(BusinessStatus.ILLEGAL_DATA);

        if(!jwtTokenUtil.validate(userJwtCheckRequest.getJwt()))
            exceptionHelper.throwException(BusinessStatus.TOKEN_EXPIRED);

        var userEmail = jwtTokenUtil.getUserName(userJwtCheckRequest.getJwt());

        var userByEmail = userService.getUserByPin(userEmail);

        return CommonResponse.success(JwtCheckResponse.of(userByEmail));

    }
}
