package ru.ilka.wgforge.cats.service.util;

import com.google.gson.JsonObject;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ExceptionUtil {
    public static String generateJsonedMessage(Exception exception) {
        JsonObject jsonObject = generateExceptionJsonObjectTemplate(exception, exception.getMessage());
        return jsonObject.toString();
    }

    public static String generateJsonedConstraintViolationMessage(ConstraintViolationException exception) {
        StringBuilder message = new StringBuilder();
        message.append("Following constraint violations were found: ");
        message.append(String.join(" ", exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toArray(String[]::new)));

        JsonObject jsonObject = generateExceptionJsonObjectTemplate(exception, message.toString());
        return jsonObject.toString();
    }


    public static String generateJsonedMethodArgumentNotValidMessage(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        message.append("Following constraint violations were found: ");
        message.append(String.join("  ", exception.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage).toArray(String[]::new)));

        JsonObject jsonObject = generateExceptionJsonObjectTemplate(exception, message.toString());
        return jsonObject.toString();
    }

    private static JsonObject generateExceptionJsonObjectTemplate(Exception exception, String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", (message != null && !message.isEmpty()) ? message : exception.getMessage());
        if (exception.getCause() != null) {
            jsonObject.addProperty("cause", exception.getCause().getMessage());
        } else {
            jsonObject.addProperty("cause", "");
        }
        return jsonObject;
    }
}
