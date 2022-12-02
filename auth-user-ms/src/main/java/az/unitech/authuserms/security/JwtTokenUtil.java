package az.unitech.authuserms.security;

import az.unitech.authuserms.config.ApplicationProperties;
import az.unitech.authuserms.dtos.dto.UserDto;
import az.unitech.authuserms.exception.BusinessStatus;
import az.unitech.authuserms.exception.ExceptionHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final ApplicationProperties applicationProperties;
    private final ExceptionHelper exceptionHelper;


    public String generateAccessToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC512(applicationProperties.jwtSecret());

        return JWT.create()
                .withSubject(String.format(user.getPin()))
                .withIssuer(applicationProperties.jwtIssuer())
                .withClaim("roles", List.of(user.getUserRole()))
                .withExpiresAt(Instant.now().plus(150, ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    public String generateRefreshToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC512(applicationProperties.jwtSecret());

        return JWT.create()
                .withSubject(user.getPin())
                .withIssuer(applicationProperties.jwtIssuer())
                .withExpiresAt(Instant.now().plus(15, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    public boolean validate(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(applicationProperties.jwtSecret())).build();
            Instant expires = jwtVerifier.verify(token).getExpiresAtAsInstant();
            if (Instant.now().isBefore(expires)) {
                return true;
            }
        } catch (TokenExpiredException e) {
            exceptionHelper.throwException(BusinessStatus.TOKEN_EXPIRED);
        }catch (JWTVerificationException ex) {
            exceptionHelper.throwException(BusinessStatus.INVALID_TOKEN);
        }
        return false;
    }

    public String getUserName(String token) {
        return JWT.decode(token).getSubject();
    }
}
