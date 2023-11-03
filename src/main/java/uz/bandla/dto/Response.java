package uz.bandla.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Response<T> {
    private int code;
    private T data;
    private List<String> errors = new LinkedList<>();
    private String message;

    public Response(T data) {
        this.data = data;
    }

    public Response(int code, String error) {
        this.code = code;
        addError(error);
    }

    public Response() {
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
