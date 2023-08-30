package recruitment.Task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Missing or bad value for \"Accept\" header, required \"application/json\" value.")
public class BadHeaderException extends RuntimeException{
}
