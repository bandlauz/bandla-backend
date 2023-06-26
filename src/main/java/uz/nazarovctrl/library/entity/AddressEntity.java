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
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String address;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

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
