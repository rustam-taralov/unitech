package az.unitech.accountms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ApplicationProperties {

    @Value("${jwt.key}")
    private String jwtSecret;
    @Value("${jwt.issuer}")
    private String jwtIssuer;

    private Long jwtExpiredDate;

    public String jwtSecret() {
        return jwtSecret;
    }

    public String jwtIssuer() {
        return jwtIssuer;
    }

    public Long jwtExpiredDate() {
        return jwtExpiredDate;
    }
}
