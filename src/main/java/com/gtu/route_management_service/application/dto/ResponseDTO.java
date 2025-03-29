package com.gtu.route_management_service.application.dto;

public class ResponseDTO {
    private String message;
    private Object data;
    private int statusCode;

    public ResponseDTO(String message, Object data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", statusCode=" + statusCode +
                '}';
    }

    @Override
    public int hashCode() {
        return message.hashCode() + data.hashCode() + statusCode;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResponseDTO that = (ResponseDTO) obj;
        return message.equals(that.message) && data.equals(that.data);
    }
}
