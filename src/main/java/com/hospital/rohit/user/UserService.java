package com.hospital.rohit.user;

import com.hospital.rohit.entity.*;
import com.hospital.rohit.repository.UserCredentialsRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hospital.rohit.login.LoginController.sdfErrorPOJO;

@Service
public class UserService {
    private final UserCredentialsRepository userCredentialsRepository;

    public UserService(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public CreateResponse createCustomer(UserCredentialsDTO request) {
        try {

            String username= request.getUsername();
            String password = request.getPassword();

            Customer customer = new Customer();

            customer.setContact_number(request.getCustomerDTO().getContact_number());
            customer.setEmail_id(request.getCustomerDTO().getEmail_id());
            customer.setFather_name(request.getCustomerDTO().getFather_name());
            customer.setFull_name(request.getCustomerDTO().getFull_name());
            customer.setProfession(request.getCustomerDTO().getProfession());
            customer.setDate(sdfErrorPOJO.format(new Date()));
            customer.setDate_of_birth(request.getCustomerDTO().getDate_of_birth());

            Account account = new Account();

            account.setAccount_type(request.getCustomerDTO().getAccountDTO().getAccount_type());
            account.setAccount_opening_cost(request.getCustomerDTO().getAccountDTO().getAccount_opening_cost());
            account.setCurrent_balance(request.getCustomerDTO().getAccountDTO().getCurrent_balance());
            account.setAccount_number(request.getCustomerDTO().getAccountDTO().getAccount_number());
            account.setLast_login(sdfErrorPOJO.format(new Date()));

            List<Address> addressList = new ArrayList<>();

            for(AddressDTO addressDTO: request.getCustomerDTO().getAddressDTO())
            {
                Address address = new Address();
                address.setCountry(addressDTO.getCountry());
                address.setState(addressDTO.getState());
                address.setCity(addressDTO.getCity());
                address.setZip_code(addressDTO.getZip_code());

                 AddressProof addressProof = new AddressProof();
                 addressProof.setAddress_type(addressDTO.getAddressProofDTO().getAddress_type());
                 addressProof.setAddress_number(addressDTO.getAddressProofDTO().getAddress_number());

                address.setAddressProof(addressProof);
                addressList.add(address);
            }

            Identity identity = new Identity();
            identity.setIdentity_type(request.getCustomerDTO().getIdentityDTO().getIdentity_type());
            identity.setIdentity_number(request.getCustomerDTO().getIdentityDTO().getIdentity_number());

            PanCardProof panCardProof = new PanCardProof();
            panCardProof.setPancard_number(request.getCustomerDTO().getPancardDTO().getPancard_number());

            customer.setAccount(account);
            customer.setAddress(addressList);
            customer.setIdentity(identity);
            customer.setPanCardProof(panCardProof);

            var createUserCredentials = UserCredentials.builder()
                    .username(username)
                    .password(new BCryptPasswordEncoder().encode(password))
                    .customer(customer)
                    .build();
           var postdata= userCredentialsRepository.save(createUserCredentials);


                return CreateResponse.builder()
                        .message("success "+postdata.getId())
                        .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error "+e.getLocalizedMessage());
        }
    }

    public CreateResponse createEmployee(UserCredentialsDTO request) {
        try {

            String username= request.getUsername();
            String password = request.getPassword();

            Employee employee = new Employee();


            employee.setDesignation(request.getEmployeeDTO().getDesignation());
            employee.setDate(sdfErrorPOJO.format(new Date()));
            employee.setContact_number(request.getEmployeeDTO().getContact_number());
            employee.setFull_name(request.getEmployeeDTO().getFull_name());
            employee.setFather_name(request.getEmployeeDTO().getFather_name());
            employee.setDesignation(request.getEmployeeDTO().getDesignation());
            employee.setEmail_id(request.getEmployeeDTO().getEmail_id());
            employee.setDate_of_birth(request.getEmployeeDTO().getDate_of_birth());


            Account account = new Account();

            account.setAccount_type(request.getEmployeeDTO().getAccountDTO().getAccount_type());
            account.setAccount_opening_cost(request.getEmployeeDTO().getAccountDTO().getAccount_opening_cost());
            account.setCurrent_balance(request.getEmployeeDTO().getAccountDTO().getCurrent_balance());
            account.setLast_login(sdfErrorPOJO.format(new Date()));
            account.setAccount_number(request.getEmployeeDTO().getAccountDTO().getAccount_number());

            List<Address> addressList = new ArrayList<>();

            for(AddressDTO addressDTO: request.getEmployeeDTO().getAddressDTO())
            {
                Address address = new Address();
                address.setCountry(addressDTO.getCountry());
                address.setState(addressDTO.getState());
                address.setCity(addressDTO.getCity());
                address.setZip_code(addressDTO.getZip_code());

                AddressProof addressProof = new AddressProof();
                addressProof.setAddress_type(addressDTO.getAddressProofDTO().getAddress_type());
                addressProof.setAddress_number(addressDTO.getAddressProofDTO().getAddress_number());

                address.setAddressProof(addressProof);
                addressList.add(address);
            }

            Identity identity = new Identity();
            identity.setIdentity_type(request.getEmployeeDTO().getIdentityDTO().getIdentity_type());
            identity.setIdentity_number(request.getEmployeeDTO().getIdentityDTO().getIdentity_number());

            PanCardProof panCardProof = new PanCardProof();
            panCardProof.setPancard_number(request.getEmployeeDTO().getPancardDTO().getPancard_number());



            employee.setAccount(account);
            employee.setAddress(addressList);
            employee.setIdentity(identity);
            employee.setPanCardProof(panCardProof);


            var createUserCredentials = UserCredentials.builder()
                    .username(username)
                    .password(new BCryptPasswordEncoder().encode(password))
                    .employee(employee)
                    .build();
            var postdata= userCredentialsRepository.save(createUserCredentials);


            return CreateResponse.builder()
                    .message("success "+postdata.getId())
                    .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error "+e.getLocalizedMessage());
        }
    }
}
