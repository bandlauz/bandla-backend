package uz.bandla.entity;

import uz.bandla.enums.CompanyStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "admin", nullable = false, unique = true)
    @OneToOne
    private ProfileEntity admin;

    @OneToMany
    private List<ProfileEntity> managers;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyStatus status = CompanyStatus.CREATED;
}