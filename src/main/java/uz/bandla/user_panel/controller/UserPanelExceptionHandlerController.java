package uz.bandla.user_panel.controller;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.exp.CompanyExistsException;
import uz.bandla.exp.ResponseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserPanelExceptionHandlerController {

    @ExceptionHandler(CompanyExistsException.class)
    private ResponseEntity<Response<?>> handle(ResponseException e) {
        return GoodResponse.error(e.getStatus(), e.getCode(), e.getMessage());
    }
}