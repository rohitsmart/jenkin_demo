package com.hospital.rohit.repository;

import com.hospital.rohit.employee.EmployeeController;
import com.hospital.rohit.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
