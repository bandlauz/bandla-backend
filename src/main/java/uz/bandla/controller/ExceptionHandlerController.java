package uz.bandla.controller;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.exp.ResponseException;
import uz.bandla.exp.auth.*;

import jakarta.validation.ConstraintViolationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = new LinkedList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({ProfileNotFoundException.class, VerificationCodeNotValidException.class,
            PhoneNumberNotFoundException.class, PhoneNumberAlreadyRegisteredException.class,
            TokenExpiredException.class, ShortIntervalException.class, PasswordAlreadySavedException.class,
            ProfileLockedException.class, ProfileStatusIncorrectException.class})
    private ResponseEntity<Response<?>> handle(ResponseException e) {
        return GoodResponse.error(e.getStatus(), e.getCode(), e.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    private ResponseEntity<Response<?>> handle(ConstraintViolationException e) {
        String message = e.getMessage();
        int index = message.indexOf(": ");
        message = message.substring(index + 1);
        return GoodResponse.error(HttpStatus.BAD_REQUEST, 100, message);
    }


    @ExceptionHandler({JWTDecodeException.class, SignatureException.class})
    private ResponseEntity<Response<?>> forbiddenHandler(RuntimeException e) {
        return GoodResponse.error(HttpStatus.FORBIDDEN, 0, e.getMessage());
    }
}
