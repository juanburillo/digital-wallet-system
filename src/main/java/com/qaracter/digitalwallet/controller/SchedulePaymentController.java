package com.qaracter.digitalwallet.controller;

import com.qaracter.digitalwallet.model.SchedulePayment;
import com.qaracter.digitalwallet.service.SchedulePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing scheduled payments.
 */
@RestController
@RequestMapping("/schedule")
public class SchedulePaymentController {
    private SchedulePaymentService scheduleService;

    /**
     * Constructor for SchedulePaymentController.
     *
     * @param scheduleService the service for handling scheduled payments.
     */
    @Autowired
    public SchedulePaymentController(SchedulePaymentService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Retrieves all scheduled payments.
     *
     * @return a list of {@link SchedulePayment} objects.
     */
    @GetMapping
    public List<SchedulePayment> getSchedulesPayments() {
        return scheduleService.getSchedulesPayments();
    }

    /**
     * Retrieves a scheduled payment by its ID.
     *
     * @param id the ID of the scheduled payment.
     * @return the {@link SchedulePayment} object if found.
     */
    @GetMapping("/{id}")
    public SchedulePayment getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    /**
     * Creates a new scheduled payment.
     *
     * @param schedulePayment the {@link SchedulePayment} object to be created.
     * @return the created {@link SchedulePayment} object.
     */
    @PostMapping
    public SchedulePayment createSchedulePayment(@RequestBody SchedulePayment schedulePayment) {
        return scheduleService.createSchedule(schedulePayment);
    }

    /**
     * Deletes a scheduled payment by its ID.
     *
     * @param id the ID of the scheduled payment to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}

