package com.qaracter.digitalwallet.model;

public class Wallet {
    private String user;
    private String currency;

    public Wallet() {
    }

    public Wallet(String user, String currency) {
        this.user = user;
        this.currency = currency;
    }

    public void sendMoney(Wallet receivingWallet, double amount) {
    }

    public String getUser() {
        return user;
    }

    public String getCurrency() {
        return currency;
    }
}
