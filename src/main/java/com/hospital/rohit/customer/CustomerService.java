package com.hospital.rohit.customer;

import com.hospital.rohit.entity.Customer;
import com.hospital.rohit.repository.CustomerRepository;
import com.hospital.rohit.repository.UserCredentialsRepository;
import com.hospital.rohit.user.CustomerDTO;
import com.hospital.rohit.user.UserCredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserCredentialsRepository userCredentialsRepository;

    private final CustomerRepository customerRepository;



    public List<Customer> getAllCustomer() {

        try{

                return customerRepository.findAll();

        }catch (Exception e)
        {
            throw new RuntimeException("Exception caught :"+e.getLocalizedMessage());
        }
    }
}
