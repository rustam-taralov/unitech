package az.unitech.accountms.dtos.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CurrencyRateRequest {
    @NotBlank
    private String fromCurrency;
    @NotBlank
    private String toCurrency;
}
