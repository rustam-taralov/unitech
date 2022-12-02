package az.unitech.accountms.security.service;

import az.unitech.accountms.dtos.response.CommonResponse;
import az.unitech.accountms.exception.BusinessStatus;
import az.unitech.accountms.exception.ExceptionHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final AuthFeignClient authFeignClient;
    private final ExceptionHelper exceptionHelper;


    public AuthService(AuthFeignClient authFeignClient, ExceptionHelper exceptionHelper, ObjectMapper objectMapper, ExceptionHelper exceptionHelper1) {
        this.authFeignClient = authFeignClient;
        this.exceptionHelper = exceptionHelper1;
    }

    public Boolean validateJwtAll(String jwt, String... roles){
        var authResponse = checkJwt(UserJwtCheckRequest.builder().jwt(jwt).build());

        boolean isAuthenticated = List.of(roles).contains(authResponse.getData().getRole());

        return authResponse.getCode()==1 && isAuthenticated;
    }
    public Boolean validateJwtNoneMatch(String jwt, String... roles){
        var authResponse = checkJwt(UserJwtCheckRequest.builder().jwt(jwt).build());

        boolean isAuthenticated = List.of(roles).contains(authResponse.getData().getRole());

        return authResponse.getCode()==1 && isAuthenticated;
    }

    public CommonResponse<AuthData> checkJwt(UserJwtCheckRequest userJwtCheckRequest){
        try {
            return authFeignClient.checkJwt(userJwtCheckRequest).getBody();
        }catch (FeignException ex){
            var response= new Gson().fromJson(ex.contentUTF8(), AuthResponse.class);
            exceptionHelper.throwException(BusinessStatus.getByCode(response.getCode()));
        }
        return null;
    }
}
