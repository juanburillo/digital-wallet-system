package com.qaracter.digitalwallet.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SchedulePayment extends Thread{
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

    public SchedulePayment(Wallet sendingWallet, Wallet receivingWallet, double amount, int days){
        this.sendingWallet = sendingWallet;
        this.receivingWallet = receivingWallet;
        this.amount = amount;
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
