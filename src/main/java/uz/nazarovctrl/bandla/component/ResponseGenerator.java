package uz.nazarovctrl.bandla.component;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.nazarovctrl.bandla.dto.Response;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseGenerator {
    public ResponseEntity<Response<?>> generateError(HttpStatus status, int code, String errorMessage) {
        Response<Object> response = new Response<>(code, errorMessage);
        return generate(status, response);
    }

    public ResponseEntity<Response<?>> generateOk(String key, Object message) {
        return generate(HttpStatus.OK, key, message);
    }

    public ResponseEntity<Response<String>> generateSuccess() {
        return ResponseEntity.ok(new Response<>("success"));
    }

    public ResponseEntity<Response<?>> generate(HttpStatus status, String key, Object message) {
        Map<String, Object> body = new HashMap<>();
        body.put(key, message);

        return ResponseEntity.status(status).body(new Response<>(body));
    }

    public ResponseEntity<Response<?>> generate(HttpStatus status, Response<?> body) {
        return ResponseEntity.status(status).body(body);
    }
}