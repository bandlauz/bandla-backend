package uz.bandla.entity;

import lombok.NoArgsConstructor;
import uz.bandla.enums.SmsType;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "sms")
@Getter
@Setter
@NoArgsConstructor
public class SmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Column(length = 4)
    private String code;

    @NotNull
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SmsType type;

    @NotNull
    private boolean isUsed = false;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    public SmsEntity(String phoneNumber, String message, String code, SmsType type) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.code = code;
        this.type = type;
    }
}
