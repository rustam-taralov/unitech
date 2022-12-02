package az.unitech.accountms.dtos.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
public class AccountToAccountRequest {

    @NotBlank
    private String accountFrom;
    @NotBlank
    private String accountTo;
    @Positive
    private BigDecimal amount;
    @NotBlank
    private String currency;
}
