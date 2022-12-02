package az.unitech.authuserms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthUserMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthUserMsApplication.class, args);
	}

}
