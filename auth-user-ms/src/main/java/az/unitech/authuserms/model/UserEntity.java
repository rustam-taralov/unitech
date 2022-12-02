package az.unitech.authuserms.model;

import az.unitech.authuserms.enums.UserRole;
import az.unitech.authuserms.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
