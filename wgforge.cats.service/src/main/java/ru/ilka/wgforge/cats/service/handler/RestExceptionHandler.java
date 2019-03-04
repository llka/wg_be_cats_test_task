package ru.ilka.wgforge.cats.service.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ilka.wgforge.cats.service.exception.RestException;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LogManager.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleUnexpectedException(Exception exception, WebRequest request) {
        logException(exception, Level.ERROR);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestException.class)
    public final ResponseEntity<String> handleRestException(RestException exception, WebRequest request) {
        logException(exception, Level.TRACE);
        return new ResponseEntity<>(exception.getMessage(), exception.getHttpStatus());
    }

    private void logException(Exception exception, Level level) {
        StringBuilder info = new StringBuilder();
        info.append("ExceptionHandler caught an exception: ");
        info.append(exception.getClass()).append("\n");
        info.append("Message: ").append(exception.getMessage()).append("\n");
        info.append("Stack trace: ").append(ExceptionUtils.getStackTrace(exception));
        logger.log(level, info.toString());
    }
}
