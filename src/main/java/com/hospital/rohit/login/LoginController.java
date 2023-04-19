package com.hospital.rohit.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.rohit.error.ErrorPOJO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    public static final DateFormat sdfErrorPOJO = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request, @NonNull HttpServletResponse response
    ) throws IOException {
        try {
            return ResponseEntity.ok(loginService.login(request));
        }
        catch(Exception e)
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
