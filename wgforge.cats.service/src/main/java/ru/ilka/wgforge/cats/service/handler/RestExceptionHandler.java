package ru.ilka.wgforge.cats.service.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ilka.wgforge.cats.service.exception.RestException;
import ru.ilka.wgforge.cats.service.util.ExceptionUtil;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleUnexpectedException(Exception exception, WebRequest request) {
        logException(exception, Level.ERROR);
        return new ResponseEntity<>(ExceptionUtil.generateJsonedMessage(exception), jsonMediaTypeHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestException.class)
    public final ResponseEntity<String> handleRestException(RestException exception, WebRequest request) {
        logException(exception, Level.TRACE);
        return new ResponseEntity<>(ExceptionUtil.generateJsonedMessage(exception), jsonMediaTypeHeader(), exception.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
        logException(exception, Level.TRACE);
        return new ResponseEntity<>(ExceptionUtil.generateJsonedConstraintViolationMessage(exception),
                jsonMediaTypeHeader(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        logException(exception, Level.TRACE);
        return new ResponseEntity<>(ExceptionUtil.generateJsonedMethodArgumentNotValidMessage(exception),
                jsonMediaTypeHeader(), HttpStatus.BAD_REQUEST);
    }

    private void logException(Exception exception, Level level) {
        StringBuilder info = new StringBuilder();
        info.append("ExceptionHandler caught an exception: ");
        info.append(exception.getClass()).append("\n");
        info.append("Message: ").append(exception.getMessage()).append("\n");
        info.append("Stack trace: ").append(ExceptionUtils.getStackTrace(exception));
        logger.log(level, info.toString());
    }

    private HttpHeaders jsonMediaTypeHeader() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return responseHeaders;
    }
}
