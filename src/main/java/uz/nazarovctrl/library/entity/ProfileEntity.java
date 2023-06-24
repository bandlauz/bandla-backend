package uz.nazarovctrl.library.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uz.nazarovctrl.library.enums.Role;
import uz.nazarovctrl.library.enums.Status;

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
    @Column
    private Role role = Role.ROLE_USER;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column
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
