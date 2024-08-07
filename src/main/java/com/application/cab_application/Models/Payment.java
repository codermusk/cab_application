package com.application.cab_application.Models;

import com.application.cab_application.enums.PaymentType;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Payment {
    private int id;
    private PaymentType paymentType;
    private Timestamp paymentDate;

    public Payment(int id, PaymentType paymentType, Timestamp paymentDate) {
        this.id = id;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Map<String, Object> paymentTableMapper() {
        Map<String, Object> paymentMapper = new LinkedHashMap<>();
        paymentMapper.put("payment_type", paymentType.getCode());
        paymentMapper.put("payment_date", paymentDate);
        return paymentMapper;
    }

}
