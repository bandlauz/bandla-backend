package uz.bandla.repository;

import uz.bandla.entity.SmsEntity;
import uz.bandla.enums.SmsType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {
    Optional<SmsEntity> findFirstByPhoneNumberAndTypeOrderByCreatedDateDesc(String phoneNumber, SmsType type);

}
