package uz.nazarovctrl.bandla.entity;

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

    @Column(name = "phone_number",updatable = false)
    private String phoneNumber;
    @Column(updatable = false)
    private String code;

    @Builder.Default
    @Column
    private boolean isUsed = false;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;
}

