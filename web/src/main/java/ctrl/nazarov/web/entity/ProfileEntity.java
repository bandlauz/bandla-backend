package ctrl.nazarov.web.entity;

import ctrl.nazarov.web.enums.Role;
import ctrl.nazarov.web.enums.Status;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Builder
@Entity
@Table(name = "profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @Column(name = "company_id")
    private Integer companyId;
    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.NOT_VERIFIED;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedOn;

    @Builder.Default
    @Column(name = "is_visible")
    private boolean isVisible = true;

}
