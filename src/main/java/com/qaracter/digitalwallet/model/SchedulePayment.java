package com.qaracter.digitalwallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a scheduled payment that runs as a separate thread.
 */
public class SchedulePayment {

    private Transaction transaction;
    private Long id;
    private int days;
    @JsonIgnore
    private final Timer timer = new Timer();
    @JsonIgnore
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @JsonIgnore
    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Money Transfers Successfully");
        }
    };
    @JsonIgnore
    private final Thread thread = new Thread(() -> {
        try {
            while (!Thread.currentThread().isInterrupted()) {  // Avoid infinite
                timer.schedule(task, TimeUnit.DAYS.toMillis(days));
                System.out.println("Send money transfer request...");
                Thread.sleep(TimeUnit.DAYS.toMillis(days)); // Pause before next execution
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted. Stopping execution.");
            Thread.currentThread().interrupt(); // Restore the interruption
        } catch (Exception e) {
            System.err.println("Error caught in the thread execution.");
        }
    });

    public SchedulePayment(){}

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

    public SchedulePayment(SchedulePayment schedulePayment){
        this.id = schedulePayment.getScheduleId();
        this.transaction = schedulePayment.transaction;
        this.days = schedulePayment.getDays();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
     * Start the thread
     *
     */
    public void start(){
        thread.start();
    }

    /**
     * Stop the thread
     *
     */
    public void stop(){
        thread.interrupt();
        scheduler.shutdown();
        timer.cancel();
    }

}

