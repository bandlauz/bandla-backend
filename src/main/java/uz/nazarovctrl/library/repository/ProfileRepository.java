package uz.nazarovctrl.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nazarovctrl.library.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByEmail(String username);

    Optional<ProfileEntity> findByEmailAndIsVisible(String username, boolean isVisible);

}
