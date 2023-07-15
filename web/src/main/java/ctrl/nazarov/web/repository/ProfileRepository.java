package ctrl.nazarov.web.repository;

import ctrl.nazarov.web.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ctrl.nazarov.web.entity.ProfileEntity;

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
