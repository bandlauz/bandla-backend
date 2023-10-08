package uz.nazarovctrl.bandla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import uz.nazarovctrl.bandla.exp.auth.ProfileNotFoundException;
import uz.nazarovctrl.bandla.repository.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository repository;

    public void save(ProfileEntity profile) {
        repository.save(profile);
    }

    public ProfileEntity findByPhoneNumberOrElseThrow(String phoneNumber) {
        return repository.findByPhoneNumberAndIsVisibleTrue(phoneNumber)
                .orElseThrow(ProfileNotFoundException::new);
    }

    public Optional<ProfileEntity> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumberAndIsVisibleTrue(phoneNumber);
    }

    public void savePassword(Integer id, String password) {
        repository.savePasswordById(id, password);
    }
}
