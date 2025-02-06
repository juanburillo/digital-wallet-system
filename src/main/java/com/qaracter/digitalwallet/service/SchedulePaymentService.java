package com.qaracter.digitalwallet.service;

import com.qaracter.digitalwallet.model.SchedulePayment;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
        Optional<SchedulePayment> scheduleToDelete = schedulePaymentList.stream()
                .filter(schedulePayment -> schedulePayment.getScheduleId().equals(id))
                .findFirst();

        if (scheduleToDelete.isPresent()){
            SchedulePayment schedulePayment = scheduleToDelete.get();
            try{
                schedulePayment.interrupt();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        schedulePaymentList.removeIf(schedulePayment -> schedulePayment.getScheduleId().equals(id));
    }

}
