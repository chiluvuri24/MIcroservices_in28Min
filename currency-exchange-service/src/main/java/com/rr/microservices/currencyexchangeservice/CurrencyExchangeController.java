package com.rr.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable("from") String from,
			                                    @PathVariable("to") String to) {
		
		logger.info(" Start of methood {} with from {} and to {}",getClass().getName(),from,to);
		
		String env = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = new CurrencyExchange(1000l,from,to,new BigDecimal(85));
		currencyExchange.setEnvironment(env);
	
		logger.info(" End of methood {} with from {} and to {}",getClass().getName(),from,to);
		
		return currencyExchange;
		
		
		
	}
	
}
