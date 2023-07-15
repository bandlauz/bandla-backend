package ctrl.nazarov.bot.entity;

import ctrl.nazarov.bot.enums.ProfileRole;
import ctrl.nazarov.bot.enums.Step;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //auto
    @Column
    private Long userId;
    @Column
    private String username;

    @Column(name = "language_code")
    private String languageCode;

    @Enumerated(EnumType.STRING)
    @Column
    private Step step = Step.MAIN;

    @Enumerated(EnumType.STRING)
    @Column
    private ProfileRole role = ProfileRole.USER;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = true;


    //registration
    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private Boolean isRegistered = false;
}
