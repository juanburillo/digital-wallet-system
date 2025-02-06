package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.SchedulePayment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulePaymentService {
    private final List<SchedulePayment> schedulePaymentList = new ArrayList<>();
    private Long nextID = 1L;

    public SchedulePaymentService(){

    }

    public List<SchedulePayment> getSchedulesPayments(){
        return schedulePaymentList;
    }

    public SchedulePayment getScheduleById(Long id){
        return schedulePaymentList.stream()
                .filter(schedulePayment -> schedulePayment.getScheduleId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public SchedulePayment createSchedule(SchedulePayment schedulePayment){
        schedulePayment.run();
        schedulePaymentList.add(schedulePayment);
        return schedulePayment;
    }

    public void deleteSchedule(Long id) {
        schedulePaymentList.removeIf(schedulePayment -> schedulePayment.getScheduleId().equals(id));
    }

}
