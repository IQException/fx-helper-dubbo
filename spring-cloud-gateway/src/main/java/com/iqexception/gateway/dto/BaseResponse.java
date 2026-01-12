package com.iqexception.gateway.dto;


public class BaseResponse  {

    private ResponseStatus status;

    public BaseResponse() {
    }

    public BaseResponse(ResponseStatus status) {
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
