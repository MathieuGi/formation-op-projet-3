// Custom Error Response Class
package com.girardon.chatop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseException {

    private int statusCode;
    private String message;
}