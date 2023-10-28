package uz.bandla.entity;

import lombok.NoArgsConstructor;
import uz.bandla.enums.SmsType;

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

    @Column(name = "phone_number", nullable = false, updatable = false)
    private String phoneNumber;

    @Column(length = 4, nullable = false, updatable = false)
    private String code;

    @Column(name = "message", nullable = false, updatable = false)
    private String message;

    @Column(name = "type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private SmsType type;

    @Column(name = "is_used", nullable = false)
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
