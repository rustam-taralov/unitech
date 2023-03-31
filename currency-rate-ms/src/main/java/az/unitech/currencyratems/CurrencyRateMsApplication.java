package az.unitech.currencyratems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyRateMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateMsApplication.class, args);
	}

}
