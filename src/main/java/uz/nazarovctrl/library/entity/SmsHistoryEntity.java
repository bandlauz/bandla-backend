package uz.nazarovctrl.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@Entity
@Table(name = "sms_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String code;

    @Builder.Default
    @Column
    private boolean isUsed = false;

    @Column
    @CreationTimestamp
    private Timestamp createdOn;

    public SmsHistoryEntity(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
    }
}

