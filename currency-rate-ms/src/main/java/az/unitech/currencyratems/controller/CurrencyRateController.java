package az.unitech.currencyratems.controller;

import az.unitech.currencyratems.dtos.dto.CurrencyRateDto;
import az.unitech.currencyratems.dtos.request.CurrencyRateRequest;
import az.unitech.currencyratems.dtos.response.CommonResponse;
import az.unitech.currencyratems.dtos.response.CurrencyRateResponse;
import az.unitech.currencyratems.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/rate")
@RequiredArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @GetMapping
    public ResponseEntity<CommonResponse<CurrencyRateDto>> getCurrency(@RequestParam("fromRate") String fromRate,
                                                                       @RequestParam("toRate") String toRate){
        var currencyRate= currencyRateService.getCurrency(CurrencyRateRequest.builder()
                                                                  .fromCurrency(fromRate)
                                                                  .toCurrency(toRate).build());
        return ResponseEntity.ok(CommonResponse.success(currencyRate));
    }
}
