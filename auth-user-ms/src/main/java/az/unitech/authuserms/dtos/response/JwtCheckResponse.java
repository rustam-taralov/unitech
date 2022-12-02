package az.unitech.authuserms.dtos.response;

import az.unitech.authuserms.dtos.dto.UserDto;
import az.unitech.authuserms.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtCheckResponse {

    private String pin;
    private UserRole role;

    public static JwtCheckResponse of(UserDto userDto){
        return JwtCheckResponse.builder()
                .pin(userDto.getPin())
                .role(userDto.getUserRole())
                .build();
    }
}