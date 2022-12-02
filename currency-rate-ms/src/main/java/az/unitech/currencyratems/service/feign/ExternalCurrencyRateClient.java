package az.unitech.currencyratems.service.feign;

import az.unitech.currencyratems.config.FeignConfig;
import az.unitech.currencyratems.dtos.response.ExternalCurrencyRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( url = "https://api.apilayer.com", configuration = FeignConfig.class)
public interface ExternalCurrencyRateClient {

    @GetMapping("/fixer/latest")
    ResponseEntity<ExternalCurrencyRateResponse> getCurrencyRate(@RequestParam("base") String base);
}
