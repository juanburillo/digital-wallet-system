package com.qaracter.digitalwallet.controller;

import com.qaracter.digitalwallet.model.SchedulePayment;
import com.qaracter.digitalwallet.service.SchedulePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class SchedulePaymentController {
    private SchedulePaymentService scheduleService;

    @Autowired
    public SchedulePaymentController(SchedulePaymentService scheduleService){
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<SchedulePayment> getSchedulesPayments(){
        return scheduleService.getSchedulesPayments();
    }

    @GetMapping("/{id}")
    public SchedulePayment getScheduleById(Long id){
        return scheduleService.getScheduleById(id);
    }

    @PostMapping
    public SchedulePayment createSchedulePayment(@RequestBody SchedulePayment schedulePayment){
        return scheduleService.createSchedule(schedulePayment);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }

}
