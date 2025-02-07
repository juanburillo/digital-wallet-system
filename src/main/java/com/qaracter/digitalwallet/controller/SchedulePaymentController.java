package com.qaracter.digitalwallet.controller;

import com.qaracter.digitalwallet.model.SchedulePayment;
import com.qaracter.digitalwallet.service.SchedulePaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @Operation(summary = "Retrieve all scheduled payments",
            description = "Fetches all the scheduled payments from the system.")
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
    @Operation(summary = "Get scheduled payment by ID",
            description = "Fetches a scheduled payment by its unique ID.")
    @GetMapping("/{id}")
    public SchedulePayment getScheduleById(
            @PathVariable
            @Schema(description = "The ID of the scheduled payment", example = "1") Long id) {
        return scheduleService.getScheduleById(id);
    }

    /**
     * Creates a new scheduled payment.
     *
     * @param schedulePayment the {@link SchedulePayment} object to be created.
     * @return the created {@link SchedulePayment} object.
     */
    @Operation(summary = "Create a new scheduled payment",
            description = "Creates a new scheduled payment based on the provided details.")
    @ApiResponse(responseCode = "201", description = "Scheduled payment created successfully")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public SchedulePayment createSchedulePayment(
            @RequestBody
            @Schema(description = "Scheduled payment details to be created") SchedulePayment schedulePayment) {
        return scheduleService.createSchedule(schedulePayment);
    }

    /**
     * Deletes a scheduled payment by its ID.
     *
     * @param id the ID of the scheduled payment to be deleted.
     */
    @Operation(summary = "Delete a scheduled payment",
            description = "Deletes a scheduled payment by its unique ID.")
    @ApiResponse(responseCode = "204", description = "Scheduled payment deleted successfully")
    @DeleteMapping("/{id}")
    public void deleteSchedule(
            @PathVariable
            @Schema(description = "The ID of the scheduled payment to delete", example = "1") Long id) {
        scheduleService.deleteSchedule(id);
    }

}
