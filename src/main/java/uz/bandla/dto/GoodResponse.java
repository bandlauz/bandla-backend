package uz.bandla.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GoodResponse {

    public static ResponseEntity<Response<?>> ok(String message) {
        Response<?> response = new Response<>();
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Response<T>> ok(T data) {
        return ResponseEntity.ok(new Response<>(data));
    }

    public static ResponseEntity<Response<?>> error(HttpStatus status, int code, String error) {
        return ResponseEntity.status(status).body(new Response<>(code, error));
    }
}
