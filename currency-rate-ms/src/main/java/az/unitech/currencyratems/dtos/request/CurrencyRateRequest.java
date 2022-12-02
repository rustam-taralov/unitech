package az.unitech.currencyratems.dtos.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRateRequest {
    @NotBlank
    private String fromCurrency;
    @NotBlank
    private String toCurrency;
}
