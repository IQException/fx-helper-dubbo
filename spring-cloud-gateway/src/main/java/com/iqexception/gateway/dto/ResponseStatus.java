package com.iqexception.gateway.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public class ResponseStatus {

    @NotBlank
    private String errorCode;
    private String errorMessage;
    private Map<String, String> errors;


    public ResponseStatus() {
    }

    public ResponseStatus(String errorCode, String errorMessage, Map<String, String> errors) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
