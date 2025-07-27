package com.finance.chitmanagement.common.exception;

public class ServerErrorException extends RuntimeException{

    public ServerErrorException(Exception exception) { super(exception); }

    public ServerErrorException(String message) { super(message); }
}
