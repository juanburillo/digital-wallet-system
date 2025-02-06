package com.qaracter.digitalwallet.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Long id;
    private String name;
    private Map<String, Double> wallets = new HashMap<>();

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
    public Map<String, Double> getWallets() {
        return wallets;
    }
}
