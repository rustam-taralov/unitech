package az.unitech.authuserms.dtos.dto;

import az.unitech.authuserms.enums.UserRole;
import az.unitech.authuserms.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String pin;
    private String password;
    private String name;
    private String surname;
    private UserStatus status;
    private UserRole userRole;
    private Boolean isDelete;
    private LocalDateTime createdAt;
}
