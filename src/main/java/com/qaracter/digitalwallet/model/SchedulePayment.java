package com.qaracter.digitalwallet.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Represents a scheduled payment that runs as a separate thread.
 */
public class SchedulePayment extends Thread {

    private final Transaction transaction;
    private Long id;
    private int days;
    private final Timer timer = new Timer();
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Money Transfers Successfully");
        }
    };

    /**
     * Constructs a scheduled payment with the specified ID, transaction, and delay in days.
     *
     * @param id          the unique identifier of the scheduled payment.
     * @param transaction the transaction to be executed.
     * @param days        the delay in days before execution.
     */
    public SchedulePayment(Long id, Transaction transaction, int days) {
        this.id = id;
        this.transaction = transaction;
        this.days = days;
    }

    /**
     * Gets the unique identifier of the scheduled payment.
     *
     * @return the schedule ID.
     */
    public Long getScheduleId() {
        return id;
    }

    /**
     * Sets the unique identifier of the scheduled payment.
     *
     * @param id the schedule ID to set.
     */
    public void setScheduleId(Long id) {
        this.id = id;
    }

    /**
     * Gets the number of days before the scheduled payment is executed.
     *
     * @return the delay in days.
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the number of days before the scheduled payment is executed.
     *
     * @param days the delay in days to set.
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * Runs the scheduled payment, executing the transaction after the specified delay.
     */
    @Override
    public void run() {
        while (true) {
            try {
                timer.schedule(task, TimeUnit.DAYS.toMillis(days));
                System.out.println("Send " + transaction.getAmount() + transaction.getCurrency() + " to user X in" + this.days + " days");
            } catch (Exception e) {
                System.err.println("Error caught in the thread execution.");
            }
        }
    }

}

