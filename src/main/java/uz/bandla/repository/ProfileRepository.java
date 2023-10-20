package uz.bandla.repository;

import uz.bandla.entity.ProfileEntity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByPhoneNumberAndIsVisibleTrue(String phoneNumber);

    @Transactional
    @Modifying
    @Query("update ProfileEntity " +
            "set password = :password," +
            "status = 'ACTIVE'" +
            "where id = :id and status='NOT_VERIFIED'")
    void savePasswordById(@Param("id") Integer id, @Param("password") String password);
}