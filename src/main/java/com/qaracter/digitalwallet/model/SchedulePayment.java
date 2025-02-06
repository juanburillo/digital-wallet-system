package com.qaracter.digitalwallet.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SchedulePayment extends Thread{
    private Transaction transaction;
    private Long id;
    private int days;
    private final Timer timer = new Timer();
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Money Transfers Successfully");
        }
    };

    public SchedulePayment(Long id, Transaction transaction, int days){
        this.transaction = transaction;
        this.days = days;
    }

    public Long getScheduleId() {
        return id;
    }

    public void setScheduleId(Long id) {
        this.id = id;
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
                System.out.println("Send " + transaction.getAmount() + transaction.getCurrency() + " to user X in" + this.days + " days");
            } catch (Exception e) {
                System.err.println("Error caught in the thread execution");
                e.printStackTrace();
            }
        }

    }

}
