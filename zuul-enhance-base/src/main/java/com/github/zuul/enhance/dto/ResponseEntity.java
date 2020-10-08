package com.github.zuul.enhance.dto;

import lombok.Data;

public class ResponseEntity {

    public final static String STATUS_OK = "OK";
    public final static String STATUS_ERROR = "ERROR";

    public final static String OK_MESSAGE = "success";

    private String status;
    private String message;
    private Object data;

    public ResponseEntity withData(Object data){
        this.data = data;
        return this;
    }

    public static ResponseEntity okay() {
        ResponseEntity result = new ResponseEntity();
        result.setStatus(STATUS_OK);
        result.setMessage(OK_MESSAGE);
        return result;
    }

    public static ResponseEntity okayWithData(Object data) {
        return okay().withData(data);
    }

    public static ResponseEntity error(String errorMessage) {
        ResponseEntity result = new ResponseEntity();
        result.setStatus(STATUS_ERROR);
        result.setMessage(errorMessage);
        return result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
