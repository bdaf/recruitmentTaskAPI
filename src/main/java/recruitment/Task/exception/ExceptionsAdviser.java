package recruitment.Task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import recruitment.Task.DTO.ErrorDTO;

@ControllerAdvice
public class ExceptionsAdviser {
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(BadHeaderException.class)
    public @ResponseBody
    ErrorDTO handleBadHeaderException(){
        return new ErrorDTO (406, "Missing or bad value for 'Accept' header, required 'application/json' value.");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundLoginException.class)
    public @ResponseBody
    ErrorDTO handleRepositoryNotValidLoginException(){
        return new ErrorDTO (404, "Not found user with provided login.");
    }
}
