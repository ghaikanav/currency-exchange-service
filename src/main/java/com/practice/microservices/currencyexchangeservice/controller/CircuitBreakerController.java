package com.practice.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    // @Retry(name = "sample-api", fallbackMethod = "fallbackResponse")
    // @CircuitBreaker(name = "default")
    // @RateLimiter(name = "sample-api")
    // 10sec -> 2 calls for this API
    @Bulkhead(name = "default")
    // 10 concurrent calls
    public String sampleApi() {
        logger.info("Sample Api call received");
//        ResponseEntity<String> forEntity = new RestTemplate()
//                .getForEntity("http://localhost:8080/dummy-url", String.class);
//        return forEntity.getBody();
        return "Sample Api";
    }

    public String fallbackResponse(Exception exception) {
        return "service is down currently, pls retry later.";
    }
}
