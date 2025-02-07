package com.qaracter.digitalwallet.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a user within the system.
 */
public class User {

    private Long id;
    private String name;
    private Map<Currency, Double> wallets = new HashMap<>();

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Currency, Double> getWallets() {
        return wallets;
    }

}
