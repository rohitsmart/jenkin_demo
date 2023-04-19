package com.hospital.rohit.employee;

import com.hospital.rohit.entity.Employee;
import com.hospital.rohit.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployee() {

        try{

            return employeeRepository.findAll();

        }
        catch (Exception e)
        {
            throw new RuntimeException("Exception caught:"+e.getLocalizedMessage());
        }
    }
}
