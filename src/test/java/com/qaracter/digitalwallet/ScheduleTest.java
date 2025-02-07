package com.qaracter.digitalwallet;

import com.qaracter.digitalwallet.model.SchedulePayment;
import com.qaracter.digitalwallet.model.Transaction;
import com.qaracter.digitalwallet.service.SchedulePaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    private SchedulePaymentService schedulePaymentService;

    @BeforeEach
    public void setUp() {
        schedulePaymentService = new SchedulePaymentService();
    }

    @Test
    public void testCreateSchedulePayment() {
        Transaction transaction = new Transaction("1", "1-USD", "2-USD", BigDecimal.valueOf(100), null, LocalDateTime.now(), null);
        SchedulePayment schedulePayment = new SchedulePayment(1L, transaction, 1);

        SchedulePayment createdSchedule = schedulePaymentService.createSchedule(schedulePayment);

        assertNotNull(createdSchedule);
        assertEquals(1L, createdSchedule.getScheduleId());
        assertEquals(transaction, createdSchedule.getTransaction());
        assertEquals(1, createdSchedule.getDays());
    }

    @Test
    public void testGetScheduleById() {
        Transaction transaction = new Transaction("1", "1-USD", "2-USD", BigDecimal.valueOf(100), null, LocalDateTime.now(), null);
        SchedulePayment schedulePayment = new SchedulePayment(1L, transaction, 1);
        schedulePaymentService.createSchedule(schedulePayment);

        SchedulePayment retrievedSchedule = schedulePaymentService.getScheduleById(1L);

        assertNotNull(retrievedSchedule);
        assertEquals(1L, retrievedSchedule.getScheduleId());
    }

    @Test
    public void testDeleteSchedule() {
        Transaction transaction = new Transaction("1", "1-USD", "2-USD", BigDecimal.valueOf(100), null, LocalDateTime.now(), null);
        SchedulePayment schedulePayment = new SchedulePayment(1L, transaction, 1);
        schedulePaymentService.createSchedule(schedulePayment);

        schedulePaymentService.deleteSchedule(1L);
        SchedulePayment deletedSchedule = schedulePaymentService.getScheduleById(1L);

        assertNull(deletedSchedule);
    }
}