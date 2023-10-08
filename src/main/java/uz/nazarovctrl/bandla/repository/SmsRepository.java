package uz.nazarovctrl.bandla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nazarovctrl.bandla.entity.SmsEntity;
import uz.nazarovctrl.bandla.enums.SmsType;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {
    Optional<SmsEntity> findFirstByPhoneNumberAndTypeOrderByCreatedDateDesc(String phoneNumber, SmsType type);

}
