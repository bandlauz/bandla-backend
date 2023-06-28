package uz.nazarovctrl.bandla.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import uz.nazarovctrl.bandla.enums.Status;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByPhoneNumberAndIsVisible(String username, boolean isVisible);

    @Modifying
    @Transactional
    @Query("update ProfileEntity set status=?2 " +
            "where phoneNumber=?1 and isVisible=true")
    void updateStatusByPhoneNumber(String phoneNumber, Status status);
}
