package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.ExchangeRates;
import com.qaracter.digitalwallet.model.Currency;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    /**
     * Converts an amount from one currency to another
     *
     * @param from The source currency
     * @param to The target currency
     * @param amount The amount to be converted
     * @return The converted amount
     */
    public double convert(Currency from, Currency to, double amount) {
        double rate = ExchangeRates.getRate(from, to); // Gets the exchange rate
        return amount * rate; // Returns the converted amount;
    }

}
