package com.seeker.globleExceptionHandling;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.OwnExceptions.UnauthorizedException;
import com.seeker.dtos.UnSuccessfullDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED)
        		.body(new UnSuccessfullDto(false,"invalid email or password ! "));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
                .body(new UnSuccessfullDto(false,"email not found ! "));
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex){
    	return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(new UnSuccessfullDto(false, "Login First"));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
    	return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(new UnSuccessfullDto(false, ex.getMessage()));
    }
}
