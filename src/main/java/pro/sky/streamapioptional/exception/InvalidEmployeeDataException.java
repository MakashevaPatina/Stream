package pro.sky.streamapioptional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmployeeDataException extends RuntimeException{
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
