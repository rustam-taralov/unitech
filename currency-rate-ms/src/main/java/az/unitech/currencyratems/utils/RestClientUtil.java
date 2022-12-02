package az.unitech.currencyratems.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Component
public class RestClientUtil {

    private final Logger logger = LoggerFactory.getLogger(RestClientUtil.class.getName());
    private final RestTemplate restTemplate;

    public RestClientUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseClass) {
        logger.info("url: {}", url);
        return exec(() -> restTemplate.getForEntity(url, responseClass), responseClass);
    }

    public <T, R> ResponseEntity<T> postForEntity(String url, R requestBody, Class<T> responseClass) {
        logger.info("url: {} body:{}", url, requestBody.toString());
        return exec(() -> restTemplate.postForEntity(url, requestBody, responseClass), responseClass);
    }

    public <R, T> ResponseEntity<T> postForObject(String url, R requestBody, Class<T> responseClass) {
        logger.info("url: {} body:{}", url, requestBody.toString());
        return exec(() -> ResponseEntity.ok(restTemplate.postForObject(url, requestBody, responseClass)), responseClass);
    }

    public <R, T> ResponseEntity<T> putForEntity(String url, R requestBody, Class<T> responseClass) {
        logger.info("url: {} body:{}", url, requestBody.toString());
        return exec(() -> {
            restTemplate.put(url, requestBody);
            return ResponseEntity.ok().build();
        }, responseClass);
    }

    public <R> ResponseEntity putForEntityOrThrowEx(String url, R requestBody) {
        logger.info("url: {} body:{}", url, requestBody.toString());
        return execOrThrowEx(() -> {
            restTemplate.put(url, requestBody);
            return ResponseEntity.ok().build();
        });
    }

    public <T> ResponseEntity<T> getForEntityOrThrowEx(String url, Class<T> responseClass) {
        logger.info("url: {}", url);
        return execOrThrowEx(() -> restTemplate.getForEntity(url, responseClass));
    }

    public <T> ResponseEntity<T> getWithExchangeOrThrowEx(String url, ParameterizedTypeReference<T> responseTypeReference) {
        logger.info("url: {}", url);
        return execOrThrowEx(() -> restTemplate.exchange(url, HttpMethod.GET, null, responseTypeReference));
    }

    public <T> ResponseEntity<T> getParametrizedWithExchange(String url, ParameterizedTypeReference<T> responseTypeReference) {
        logger.info("url: {}", url);
        return exec(() -> restTemplate.exchange(url, HttpMethod.GET, null, responseTypeReference), null);
    }

    public <T, R> ResponseEntity<T> postForEntityOrThrowEx(String url, R requestBody, Class<T> responseClass) {
        logger.info("url: {} body:{}", url, requestBody.toString());
        return execOrThrowEx(() -> restTemplate.postForEntity(url, requestBody, responseClass));
    }

    public <T, R> ResponseEntity<T> postForObjectOrThrowEx(String url, R requestBody, Class<T> responseClass) {
        logger.info("url: {} body:{}", url, requestBody.toString());
        return execOrThrowEx(() -> {
            restTemplate.postForObject(url, requestBody, responseClass);
            return ResponseEntity.ok().build();
        });
    }

    private <T> ResponseEntity<T> exec(Supplier<ResponseEntity> supplier, Class<T> response) {
        try {
            return supplier.get();
        } catch (HttpStatusCodeException httpException) {
            logger.error("HttpClientException: {}", httpException.getLocalizedMessage());
            logger.error("HttpStatusCodeException: ", httpException);
            return ResponseEntity.status(httpException.getStatusCode()).body(parse(httpException.getResponseBodyAsString(), response));
        } catch (Exception exception) {
            logger.error("Exception: {}", exception.getLocalizedMessage());
            return ResponseEntity.status(500).build();
        }
    }

    private <T> T parse(String str, Class<T> response) {
        try {
            return new ObjectMapper().readValue(str, response);
        } catch (Exception ex) {
            return null;
        }
    }

    private ResponseEntity execOrThrowEx(Supplier supplier) {
        try {
            return (ResponseEntity) supplier.get();
        } catch (Exception ex) {
            logger.error("HttpClientException: {}", ex.getLocalizedMessage());
            logger.error("HttpClientException: ", ex);
            throw new RuntimeException(ex);
        }
    }

}



