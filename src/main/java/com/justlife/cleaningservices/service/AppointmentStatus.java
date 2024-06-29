package com.justlife.cleaningservices.service;

public enum AppointmentStatus {

    PENDING(1), IN_PROGRESS(2), DONE(3);

    private int status;

    AppointmentStatus(int id) {
        this.status = id;
    }

    public int getStatusId() {
        return status;
    }
}
