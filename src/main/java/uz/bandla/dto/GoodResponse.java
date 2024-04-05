package uz.bandla.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GoodResponse {

    public static ResponseEntity<Response<?>> okMessage(String message) {
        Response<?> response = new Response<>();
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Response<T>> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<Response<T>> ok(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Response<?>> error(HttpStatus status, int code, String error) {
        return ResponseEntity.status(status).body(new Response<>(code, error));
    }

    public static <T> ResponseEntity<Response<?>> error(HttpStatus status, int code, String error, T data) {
        return ResponseEntity.status(status).body(new Response<>(code, error, data));
    }

    public static ResponseEntity<Response<?>> badRequest(String message) {
        return ResponseEntity.badRequest().body(new Response<>(101, message));
    }
}
