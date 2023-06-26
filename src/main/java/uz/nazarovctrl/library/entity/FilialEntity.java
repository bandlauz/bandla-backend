package uz.nazarovctrl.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "filial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;
    @OneToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "address_id")
    private Integer addressId;
    @OneToOne
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private AddressEntity address;

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;
    @OneToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "created_by", nullable = false)
    private Integer createdById;
    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private ProfileEntity createdBy;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private Timestamp updatedOn;
    @Column(name = "created_on", updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(name = "is_visible")
    private boolean isVisible = true;

}
