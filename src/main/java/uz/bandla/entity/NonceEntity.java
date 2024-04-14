package uz.bandla.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nonce")
@Getter
@Setter
public class NonceEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = false;

    @ManyToOne
    private ProfileEntity profile;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    public NonceEntity() {
        this.id = UUID.randomUUID().toString();
    }
}