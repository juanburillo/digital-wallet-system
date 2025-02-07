package com.qaracter.digitalwallet.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qaracter.digitalwallet.model.SchedulePayment;
import com.qaracter.digitalwallet.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing scheduled payments.
 */
@Service
public class SchedulePaymentService {

    private final List<SchedulePayment> schedulePaymentList = new ArrayList<>();
    private Long nextID = 1L;

    /**
     * Default constructor.
     */
    public SchedulePaymentService() {
    }

    /**
     * Retrieves the list of all scheduled payments.
     *
     * @return a list of {@link SchedulePayment} objects.
     */
    public List<SchedulePayment> getSchedulesPayments() {
        return schedulePaymentList;
    }

    /**
     * Retrieves a scheduled payment by its ID.
     *
     * @param id the ID of the scheduled payment.
     * @return the {@link SchedulePayment} object if found, otherwise null.
     */
    public SchedulePayment getScheduleById(Long id) {
        return schedulePaymentList.stream()
                .filter(schedulePayment -> schedulePayment.getScheduleId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Creates a new scheduled payment and starts its execution.
     *
     * @param schedulePayment the {@link SchedulePayment} object to be created.
     * @return the created {@link SchedulePayment} object.
     */
    public SchedulePayment createSchedule(SchedulePayment schedulePayment) {
        schedulePayment.setScheduleId(this.nextID++);
        schedulePayment.start();
        schedulePaymentList.add(schedulePayment);
        return schedulePayment;
    }

    /**
     * Deletes a scheduled payment by its ID.
     *
     * @param id the ID of the scheduled payment to be deleted.
     */
    public void deleteSchedule(Long id) {
        Optional<SchedulePayment> scheduleToDelete = schedulePaymentList.stream()
                .filter(schedulePayment -> schedulePayment.getScheduleId().equals(id))
                .findFirst();

        if (scheduleToDelete.isPresent()) {
            SchedulePayment schedulePayment = scheduleToDelete.get();
            schedulePayment.stop();
        }
        schedulePaymentList.removeIf(schedulePayment -> schedulePayment.getScheduleId().equals(id));
    }

}