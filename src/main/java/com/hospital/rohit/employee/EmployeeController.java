package com.hospital.rohit.employee;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.rohit.customer.CustomerService;
import com.hospital.rohit.entity.Customer;
import com.hospital.rohit.entity.Employee;
import com.hospital.rohit.error.ErrorPOJO;
import com.hospital.rohit.user.UserCredentialsDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping("/employee/all")
    public List<Employee> getAllEmployee(@NonNull HttpServletResponse response) throws IOException {

        Authentication auth=null;//
        UserDetails userDetails=null;
        try
        {

            auth = SecurityContextHolder.getContext().getAuthentication();

            userDetails = (UserDetails) auth.getPrincipal();
            return employeeService.getAllEmployee();
        }
        catch (Exception e)
        {
            ErrorPOJO errorPOJO= new ErrorPOJO();
            Date date = new Date();
            errorPOJO.setError_description("login controller failed "+e.getLocalizedMessage());
            errorPOJO.setUser_description("login declined");
            response.setStatus(FORBIDDEN.value());
            errorPOJO.setStatus(String.valueOf(response.getStatus()));
            response.setContentType(APPLICATION_JSON_VALUE);
            FileWriter myWriter = new FileWriter("filename.txt",true);
            if(myWriter== null)
            {
                File file = new File("SecondError.txt");
                myWriter = new FileWriter("SecondError.txt",true);
            }
            myWriter.append("\nerror in login"+errorPOJO);
            myWriter.close();
            new ObjectMapper().writeValue(response.getOutputStream(),errorPOJO);
            // log.error("error logging "+e.getLocalizedMessage());
            return null;
        }
    }
}
