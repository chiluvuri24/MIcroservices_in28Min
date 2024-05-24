package com.rr.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	
	@Autowired
	private Environment environment;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable("from") String from,
			                                    @PathVariable("to") String to) {
		
		String env = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = new CurrencyExchange(1000l,from,to,new BigDecimal(85));
		currencyExchange.setEnvironment(env);
		return currencyExchange;
		
		
	}
	
}
