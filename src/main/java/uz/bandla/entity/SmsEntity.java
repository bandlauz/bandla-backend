package uz.bandla.entity;

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
public class SmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, length = 4)
    private String code;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SmsType type;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private boolean isUsed = false;
}
