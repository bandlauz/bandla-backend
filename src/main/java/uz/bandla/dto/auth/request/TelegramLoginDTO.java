package uz.bandla.dto.auth.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class TelegramLoginDTO {
    @NotNull
    private Long id;

    private String first_name;

    private String last_name;

    private String username;

    @NotNull
    private String photo_url;

    @NotNull
    private long auth_date;

    @NotNull
    private String hash;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            Field[] declaredFields = this.getClass().getDeclaredFields();
            List<Field> sortedFields = Arrays.asList(declaredFields)
                    .stream()
                    .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                    .toList();

            for (int i = 0; i < sortedFields.size(); i++) {
                Field field = sortedFields.get(i);
                String fieldName = field.getName();
                Object fieldValue = field.get(this);
                if (fieldName.equals("hash") || fieldValue == null)
                    continue;
                sb.append(fieldName).append("=").append(fieldValue);

                if (i != sortedFields.size() - 1)
                    sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
