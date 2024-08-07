package com.application.cab_application.enums;

import com.application.cab_application.Models.Payment;

public enum PaymentType {
    UPI(1),
    CASH(2);

    private final int code;

    PaymentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentType fromCode(int code) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getCode() == code)
                return paymentType;
        }
        throw new IllegalArgumentException("Unknown code:" + code);
    }
}
