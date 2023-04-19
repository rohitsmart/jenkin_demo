package com.hospital.rohit.login;

import com.hospital.rohit.repository.CustomerRepository;
import com.hospital.rohit.repository.UserCredentialsRepository;
import com.hospital.rohit.security.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepository customerRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public LoginResponse login(LoginRequest request) {

        try{
            String login_flag="FALSE";

            String username=request.getUsername();
            String password = request.getPassword();
            String response_user_id=null;
            String response_full_name=null;
            String response_user_contact_number=null;
            String response_account_type=null;
            String response_account_current_balance=null;
            String response_last_login_date=null;

            if(request!=null)
            {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

                login_flag="TRUE";

            }

            if(login_flag.equals("TRUE"))
            {

                    var findCustomer = userCredentialsRepository.findByUsername(username).orElseThrow();

                    if(request.getRole().equals("C"))
                    {
                        response_user_id = String.valueOf(findCustomer.getCustomer().getId());
                        response_full_name = findCustomer.getCustomer().getFull_name();
                        response_user_contact_number = findCustomer.getCustomer().getContact_number();
                        response_account_type = findCustomer.getCustomer().getAccount().getAccount_type();
                        response_account_current_balance = findCustomer.getCustomer().getAccount().getCurrent_balance();
                        response_last_login_date = findCustomer.getCustomer().getAccount().getLast_login();
                    }
                    else if(request.getRole().equals("E"))
                    {
                        response_user_id = String.valueOf(findCustomer.getEmployee().getId());
                        response_full_name = findCustomer.getEmployee().getFull_name();
                        response_user_contact_number = findCustomer.getEmployee().getContact_number();
                        response_account_type = findCustomer.getEmployee().getAccount().getAccount_type();
                        response_account_current_balance = findCustomer.getEmployee().getAccount().getCurrent_balance();
                        response_last_login_date = findCustomer.getEmployee().getAccount().getLast_login();
                    }

                    else
                    {
                        throw new
                                RuntimeException("Wrong role given:");
                    }

                    var bearer_token =  jwtService.generateToken(findCustomer);

                    var refresh_token = jwtService.generateToken(findCustomer.getId());



                return LoginResponse.builder()
                        .message("success")
                        .bearer_token(bearer_token)
                        .refresh_token(refresh_token)
                        .response_full_name(response_full_name)
                        .response_user_id(response_user_id)
                        .response_account_type(response_account_type)
                        .response_account_current_balance(response_account_current_balance)
                        .response_user_contact_number(response_user_contact_number)
                        .response_last_login_date(response_last_login_date)
                        .build();
            }
            else {
                throw new RuntimeException("wrong");
            }

        }catch (Exception e)
        {
            throw new RuntimeException("Exception caught "+e.getLocalizedMessage());
        }
    }
}
