package uz.nazarovctrl.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.library.dto.ProfileCreateDTO;
import uz.nazarovctrl.library.entity.ProfileEntity;
import uz.nazarovctrl.library.enums.Status;
import uz.nazarovctrl.library.repository.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;

    public boolean existsByEmail(String email) {
        Optional<ProfileEntity> optional = repository.findByEmailAndIsVisible(email, true);
        if (optional.isEmpty()) {
            return false;
        }
        ProfileEntity entity = optional.get();

        if (entity.getStatus().equals(Status.NOT_VERIFIED)) {
            repository.delete(entity);
            return false;
        }

        return true;
    }

    public void saveProfile(ProfileCreateDTO profile) {
        ProfileEntity entity = ProfileEntity.builder()
                .name(profile.getName())
                .email(profile.getEmail())
                .password(profile.getPassword()).build();
        repository.save(entity);
    }
}
