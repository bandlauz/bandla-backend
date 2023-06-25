package uz.nazarovctrl.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.nazarovctrl.library.entity.SmsHistoryEntity;

import java.time.LocalDateTime;


@Repository
public interface SmsHistoryRepository extends JpaRepository<SmsHistoryEntity, Long> {

    long countByPhoneNumberAndCreatedOnBetween(String email, LocalDateTime fromDate, LocalDateTime toDate);

    Page<SmsHistoryEntity> findByPhoneNumberAndIsUsedOrderByCreatedOnDesc(String phone, boolean isUsed, Pageable pageable);

}
