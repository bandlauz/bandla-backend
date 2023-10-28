package uz.bandla.entity;

import uz.bandla.enums.ProfileRole;
import uz.bandla.enums.ProfileStatus;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"phone_number", "is_visible"}))
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false, updatable = false)
    private String phoneNumber;

    @Column
    private String password;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role = ProfileRole.USER;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status = ProfileStatus.NOT_VERIFIED;

    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible = true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    public ProfileEntity(String firstName, String lastName, String phoneNumber, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
    }
}