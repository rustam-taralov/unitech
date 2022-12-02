package az.unitech.currencyratems.mapper;

import az.unitech.currencyratems.dtos.dto.CurrencyRateDto;
import az.unitech.currencyratems.model.CurrencyRateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyRateMapper {

    CurrencyRateMapper INSTANCE= Mappers.getMapper(CurrencyRateMapper.class);

    CurrencyRateDto toDto(CurrencyRateEntity entity);

}
