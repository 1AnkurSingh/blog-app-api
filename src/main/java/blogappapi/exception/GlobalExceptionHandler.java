package blogappapi.exception;

import blogappapi.dto.ApiResponse;
import org.modelmapper.internal.bytebuddy.description.modifier.MethodArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFountException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFountException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,"false");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handelMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String>resp =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((objectError) ->{
            String fieldName=((FieldError)objectError).getField();
           String message= objectError.getDefaultMessage();
           resp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
