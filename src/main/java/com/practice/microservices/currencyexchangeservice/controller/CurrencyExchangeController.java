package com.practice.microservices.currencyexchangeservice.controller;

import com.practice.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.practice.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange")
    public ExchangeValue getExchangeValue(@RequestParam String from, @RequestParam String to) {
        logger.info("getExchangeValue called from {} to {}", from, to);
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        Integer port = Integer.parseInt(environment.getProperty("local.server.port"));
        exchangeValue.setPort(port);
        return exchangeValue;
    }
}
