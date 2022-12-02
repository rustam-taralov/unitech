package az.unitech.accountms.dtos.dto;

import az.unitech.accountms.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {

    private Long id;
    private String pin;
    private String accountNumber;
    private AccountStatus status;
    private String currency;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}
