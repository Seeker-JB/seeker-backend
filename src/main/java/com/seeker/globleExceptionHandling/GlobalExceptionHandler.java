package com.seeker.globleExceptionHandling;

import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.seeker.OwnExceptions.AlreadyExistsException;
import com.seeker.OwnExceptions.ResourceNotFoundException;
import com.seeker.OwnExceptions.UnauthorizedException;
import com.seeker.dtos.ApiResponse;
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
    	return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(new UnSuccessfullDto(false, ex.getMessage()));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
    	return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(new UnSuccessfullDto(false, ex.getMessage()));
    }
    
    
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleAlreadyExists(AlreadyExistsException ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return  ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleAlreadyExists(IllegalArgumentException ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return  ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ApiResponse(false, errorMsg));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.SC_METHOD_NOT_ALLOWED)
                             .body(new ApiResponse(false, "Request method not supported: " + ex.getMethod()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> handleMissingParam(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest()
                             .body(new ApiResponse(false, "Missing parameter: " + ex.getParameterName()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleUnreadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                             .body(new ApiResponse(false, "Invalid request body"));
    }

    
 
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex) {
        ex.printStackTrace(); // Optional: log error for debugging
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                             .body(new ApiResponse(false, "Something went wrong"));
    }
    
    

}