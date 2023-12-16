package uz.bandla.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "telegram_user")
@Getter
@Setter
@NoArgsConstructor
public class TelegramUserEntity {
    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "is_premium")
    private Boolean isPremium;

    @Column(name = "is_visible")
    private Boolean isVisible = true;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToOne
    private ProfileEntity profile;

    public TelegramUserEntity(Long id, String firstName, String lastName, String username, String photoUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.photoUrl = photoUrl;
    }
}
