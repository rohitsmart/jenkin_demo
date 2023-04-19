package com.hospital.rohit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.rohit.error.ErrorPOJO;
import com.hospital.rohit.login.LoginRequest;
import com.hospital.rohit.login.LoginResponse;
import com.hospital.rohit.repository.UserCredentialsRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    private final UserCredentialsRepository userCredentialsRepository;

    @PostMapping("/customer/register")
    public ResponseEntity<CreateResponse> register(
            @RequestBody UserCredentialsDTO request, @NonNull HttpServletResponse response
    ) throws IOException {
        Authentication auth=null;//
        UserDetails userDetails=null;
        try {


            auth = SecurityContextHolder.getContext().getAuthentication();

            userDetails = (UserDetails) auth.getPrincipal();

            var loginUser = userCredentialsRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow();


            if(request.getCustomerDTO()!=null)
                return ResponseEntity.ok(userService.createCustomer(request));
            else
                return ResponseEntity.ok(userService.createEmployee(request));

        }
        catch(Exception e)
        {
            ErrorPOJO errorPOJO= new ErrorPOJO();
            Date date = new Date();
            errorPOJO.setError_description("login controller failed "+e.getStackTrace()+
                    " "+e.getMessage()+" "+e.getLocalizedMessage());
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
