package uz.bandla.entity;

import uz.bandla.enums.CompanyStatus;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "photo_url")
    private String photoUrl;

    @JoinColumn(name = "admin", nullable = false, unique = true)
    @OneToOne
    private ProfileEntity admin;

    @OneToMany
    private List<ProfileEntity> managers;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CompanyStatus status = CompanyStatus.CREATED;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    public CompanyEntity(String name, String address, String photoUrl) {
        this.name = name;
        this.address = address;
        this.photoUrl = photoUrl;
    }
}