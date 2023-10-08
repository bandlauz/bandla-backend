package uz.nazarovctrl.bandla.entity;

import uz.nazarovctrl.bandla.enums.Role;
import uz.nazarovctrl.bandla.enums.Status;
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
    private Role role = Role.USER;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_VERIFIED;

    @Column(nullable = false)
    private Boolean isVisible = true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}
