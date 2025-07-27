package com.finance.chitmanagement.common.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(Exception exception) { super(exception); }

    public BadRequestException(String message) { super(message); }
}
