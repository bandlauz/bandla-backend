package ctrl.nazarov.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "stadium")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StadiumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "filial_id")
    private Integer filialId;
    @ManyToOne
    @JoinColumn(name = "filial_id", insertable = false, updatable = false)
    private FilialEntity filial;

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
