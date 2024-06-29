package com.justlife.cleaningservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationMessage {

    private String errorMessage;

}
