package com.batarang.api.Exceptions;

import java.util.Date;

public class ErrorResponse {

    private String errorMessage;
    private String reasonCode;
    private Date timeStamp;

    public ErrorResponse(String errorMessage, String reasonCode, Date timeStamp) {
        this.errorMessage = errorMessage;
        this.reasonCode = reasonCode;
        this.timeStamp = timeStamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
