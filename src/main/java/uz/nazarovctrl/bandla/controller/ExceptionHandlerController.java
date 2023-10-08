package uz.nazarovctrl.bandla.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import uz.nazarovctrl.bandla.component.ResponseGenerator;
import uz.nazarovctrl.bandla.dto.Response;
import uz.nazarovctrl.bandla.exp.auth.*;
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
import uz.nazarovctrl.bandla.exp.ResponseException;

import java.util.*;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private final ResponseGenerator responseGenerator;

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
        return responseGenerator.generateError(e.getStatus(), e.getCode(), e.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    private ResponseEntity<Response<?>> handle(ConstraintViolationException e) {
        return responseGenerator.generateError(HttpStatus.BAD_REQUEST, 100, e.getMessage());
    }


    @ExceptionHandler({JWTDecodeException.class, SignatureException.class})
    private ResponseEntity<Response<?>> forbiddenHandler(RuntimeException e) {
        return responseGenerator.generateError(HttpStatus.FORBIDDEN, 0, e.getMessage());
    }
}
