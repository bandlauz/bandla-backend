package uz.nazarovctrl.bandla.entity;

import uz.nazarovctrl.bandla.enums.ProfileRole;
import uz.nazarovctrl.bandla.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"phoneNumber", "isVisible"}))
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role = ProfileRole.USER;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status = ProfileStatus.NOT_VERIFIED;

    @Column(nullable = false)
    private Boolean isVisible = true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}
