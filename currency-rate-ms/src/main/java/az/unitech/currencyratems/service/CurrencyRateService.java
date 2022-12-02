package az.unitech.currencyratems.service;

import az.unitech.currencyratems.dtos.dto.CurrencyRateDto;
import az.unitech.currencyratems.dtos.request.CurrencyRateRequest;
import az.unitech.currencyratems.exception.BusinessStatus;
import az.unitech.currencyratems.exception.ExceptionHelper;
import az.unitech.currencyratems.mapper.CurrencyRateMapper;
import az.unitech.currencyratems.model.CurrencyRateEntity;
import az.unitech.currencyratems.repository.CurrencyRateRepository;
import az.unitech.currencyratems.service.feign.ExternalCurrencyRateClient;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;

    private final ExternalCurrencyRateClient externalCurrencyRateClient;
    private final ExceptionHelper exceptionHelper;

    public CurrencyRateDto getCurrency(CurrencyRateRequest currencyRateRequest){
        if(!"AZN".equalsIgnoreCase(currencyRateRequest.getFromCurrency()))
            exceptionHelper.throwException(BusinessStatus.FORBIDDEN_CURRENCY);

        var currencyRate= currencyRateRepository.findByFromCurrencyAndToCurrency(currencyRateRequest.getFromCurrency(), currencyRateRequest.getToCurrency());
        return CurrencyRateMapper.INSTANCE.toDto(currencyRate);
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    private void scheduledRate(){
        val base= "AZN";
        var response= externalCurrencyRateClient.getCurrencyRate(base).getBody();
        var entityList=new LinkedList<CurrencyRateEntity>();
        response.getRates()
                .forEach((k,v)->{
                    entityList.add(new CurrencyRateEntity(base,k,new BigDecimal(v)));
                });
        currencyRateRepository.saveAll(entityList);
    }


}
