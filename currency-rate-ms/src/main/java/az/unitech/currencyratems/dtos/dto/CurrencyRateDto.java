package az.unitech.currencyratems.dtos.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRateDto {

    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
}
