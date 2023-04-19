package com.hospital.rohit.user;


import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialsDTO {


    private String username;

    private String password;

    private EmployeeDTO employeeDTO;

    private CustomerDTO customerDTO;

    private String type;
}
