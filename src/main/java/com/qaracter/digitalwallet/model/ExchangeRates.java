package com.qaracter.digitalwallet.model;

import java.util.Map;

/**
 * Serves as an exchange rate calculator for the available currencies (EUR, USD, GBP, JPY)
 */
public class ExchangeRates {

    // The rates to be applied depending on the currency pair
    public static final Map<Currency, Map<Currency, Double>> RATES = Map.of(
            // EUR as domestic currency
            Currency.EUR, Map.of(Currency.USD, 1.11, Currency.GBP, 0.87, Currency.JPY, 166.67),
            // USD as domestic currency
            Currency.USD, Map.of(Currency.EUR, 0.9, Currency.GBP, 0.78, Currency.JPY, 149.25),
            // GBP as domestic currency
            Currency.GBP, Map.of(Currency.EUR, 1.15, Currency.USD, 1.28, Currency.JPY, 192.31),
            // JPY as domestic currency
            Currency.JPY, Map.of(Currency.EUR, 0.0060, Currency.USD, 0.0067, Currency.GBP, 0.0052)
    );

    /**
     * Returns the exchange rate between two different currencies
     *
     * @param from The source currency
     * @param to The target currency
     * @return The corresponding exchange rate
     */
    public static double getRate(Currency from, Currency to) {
        if (from == to) return 1.0; // If both currencies are the same
        return RATES.get(from).get(to); // Returns the exchange rate
    }

}
