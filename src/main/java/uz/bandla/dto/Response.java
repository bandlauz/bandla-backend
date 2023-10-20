package uz.bandla.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Response<T> {
    private int code;
    private T data;
    private String error;
    private String message;

    public Response(T data) {
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(int code, String error) {
        this.code = code;
        this.error = error;
    }
}
