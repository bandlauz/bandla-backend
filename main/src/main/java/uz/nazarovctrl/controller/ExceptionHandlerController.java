package uz.nazarovctrl.controller;

import uz.nazarovctrl.dto.payme.result.Result;
import uz.nazarovctrl.dto.payme.result.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.nazarovctrl.exp.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({OrderNotFoundException.class})
    private ResponseEntity<?> handler(OrderNotFoundException e) {
        return ResponseEntity.ok(new Result(new Error(-31050, e.getMessage(), "order")));
    }

    @ExceptionHandler({WrongAmountException.class})
    private ResponseEntity<?> handler(WrongAmountException e) {
        return ResponseEntity.ok(new Result(new Error(-31001, e.getMessage(), "amount")));
    }

    @ExceptionHandler({UnableCompleteException.class})
    private ResponseEntity<?> handler(UnableCompleteException e) {
        return ResponseEntity.ok(new Result(new Error(-31008, e.getMessage(), "transaction")));
    }

    @ExceptionHandler({TransactionNotFoundException.class})
    private ResponseEntity<?> handler(TransactionNotFoundException e) {
        return ResponseEntity.ok(new Result(new Error(-31003, e.getMessage(), "transaction")));
    }

    @ExceptionHandler({TransactionInWaiting.class})
    private ResponseEntity<?> handler(TransactionInWaiting e) {
        return ResponseEntity.ok(new Result(new Error(-31099, e.getMessage(), "transaction")));
    }
}
