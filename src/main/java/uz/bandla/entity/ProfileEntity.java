package uz.bandla.entity;

import uz.bandla.enums.ProfileRole;
import uz.bandla.enums.ProfileStatus;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String phoneNumber;

    @Column
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProfileRole role = ProfileRole.USER;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProfileStatus status = ProfileStatus.NOT_VERIFIED;

    @NotNull
    private Boolean isVisible = true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
}
