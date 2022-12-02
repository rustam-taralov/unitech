package az.unitech.currencyratems.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRateResponse {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
}
