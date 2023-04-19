package com.hospital.rohit.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorPOJO {

    private String error_description;
    private String user_description;

    private String status;

    private String date;

    private String request;
}
