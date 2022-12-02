package az.unitech.accountms.security.service;

import az.unitech.accountms.config.FeignConfig;
import az.unitech.accountms.dtos.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-user-ms",path = "auth-user",configuration = FeignConfig.class)
public interface AuthFeignClient {

    @PostMapping(value = "auth/check")
    ResponseEntity<CommonResponse<AuthData>> checkJwt(@RequestBody UserJwtCheckRequest  request);
}