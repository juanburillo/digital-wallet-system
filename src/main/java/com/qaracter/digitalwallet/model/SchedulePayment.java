package com.qaracter.digitalwallet.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SchedulePayment extends Thread{
    private Long id;
    private Wallet sendingWallet;
    private Wallet receivingWallet;
    private double amount;
    private int days;
    private final Timer timer = new Timer();
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            sendingWallet.sendMoney(receivingWallet, amount);
        }
    };

    public SchedulePayment(Long id, Wallet sendingWallet, Wallet receivingWallet, double amount, int days){
        this.id = id;
        this.sendingWallet = sendingWallet;
        this.receivingWallet = receivingWallet;
        this.amount = amount;
        this.days = days;
    }

    public Long getScheduleId() {
        return id;
    }

    public void setScheduleId(Long id) {
        this.id = id;
    }

    public Wallet getSendingWallet() {
        return sendingWallet;
    }

    public void setSendingWallet(Wallet sendingWallet) {
        this.sendingWallet = sendingWallet;
    }

    public Wallet getReceivingWallet() {
        return receivingWallet;
    }

    public void setReceivingWallet(Wallet receivingWallet) {
        this.receivingWallet = receivingWallet;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public void run(){
        while (true){
            try{
                timer.schedule(task, TimeUnit.DAYS.toMillis(days));
                System.out.println("The user " + sendingWallet.getUser() + " will be giving " + receivingWallet.getUser() +
                        " the amount of " + amount + sendingWallet.getCurrency() + " in " + days + " days.");
            } catch (Exception e) {
                System.err.println("Error caught in the thread execution");
                e.printStackTrace();
            }
        }

    }

}
