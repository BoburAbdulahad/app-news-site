package uz.bob.appnewssite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * bu class exception ning message ni qaytarish un ocildi lekin
     * stack trace degan massiv qaytarvoryapti
     * mana wunga yecim topiw kk!!!
     *
     */
    @ExceptionHandler(value
            = ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody
    ForbiddenException
    handleException(ForbiddenException ex)
    {
        return new ForbiddenException(
                HttpStatus.FORBIDDEN.name(), ex.getMessage());
    }
}
