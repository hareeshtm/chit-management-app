package com.finance.chitmanagement.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChitErrorResponse {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timeStamp;
}
