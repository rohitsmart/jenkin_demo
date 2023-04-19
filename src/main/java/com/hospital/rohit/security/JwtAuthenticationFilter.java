package com.hospital.rohit.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.rohit.error.ErrorPOJO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import static com.hospital.rohit.login.LoginController.sdfErrorPOJO;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter

{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try{
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String username;
            if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
     /* boolean checkToken= jwtService.checkTokenInDB(jwt,username);
      if(checkToken==false)
      {
        ErrorPOJO errorPOJO= new ErrorPOJO();
        errorPOJO.setError_description("bad token");
        errorPOJO.setUser_description("login again");
        errorPOJO.setStatus(String.valueOf(response.getStatus()));
        response.setStatus(FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),errorPOJO);
      }*/
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
            filterChain.doFilter(request, response);
        }
        catch(Exception e)
        {
            ErrorPOJO errorPOJO= new ErrorPOJO();
            errorPOJO.setError_description(e.getLocalizedMessage());
            errorPOJO.setUser_description("token error");
            errorPOJO.setStatus(String.valueOf(response.getStatus()));
            response.setStatus(FORBIDDEN.value());
            response.setContentType(APPLICATION_JSON_VALUE);


            errorPOJO.setRequest("jwt configuration ");
            errorPOJO.setDate(sdfErrorPOJO.format(new Date()));
            FileWriter myWriter = new FileWriter("error.txt",true);
            if(myWriter== null)
            {
                File file = new File("/home/sr8/gaming-backend/error.txt");
                myWriter = new FileWriter("error.txt",true);
            }
            myWriter.append("\n error in jwt config"+errorPOJO);
            myWriter.close();


            new ObjectMapper().writeValue(response.getOutputStream(),errorPOJO);
        }
    }
}
