package az.unitech.currencyratems.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExternalCurrencyRateResponse {

    private String success;
    private String timestamp;
    private String base;
    private String date;
    private Map<String, String> rates;

}
