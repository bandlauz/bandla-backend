package uz.nazarovctrl.bandla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import uz.nazarovctrl.bandla.exp.auth.ProfileNotFoundException;
import uz.nazarovctrl.bandla.repository.ProfileRepository;
import uz.nazarovctrl.bandla.service.ProfileService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository repository;

    @Override
    public void save(ProfileEntity profile) {
        repository.save(profile);
    }

    @Override
    public ProfileEntity findByPhoneNumberOrElseThrow(String phoneNumber) {
        return repository.findByPhoneNumberAndIsVisibleTrue(phoneNumber)
                .orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    public Optional<ProfileEntity> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumberAndIsVisibleTrue(phoneNumber);
    }

    @Override
    public void savePassword(Integer id, String password) {
        repository.savePasswordById(id, password);
    }
}
