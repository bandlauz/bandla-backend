package uz.bandla.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "file")
@NoArgsConstructor
@Getter
@Setter
public class FileEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    private ProfileEntity createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    public FileEntity(UUID id, String url, ProfileEntity createdBy) {
        this.id = id;
        this.url = url;
        this.createdBy = createdBy;
    }
}