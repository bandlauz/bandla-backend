package uz.bandla.service.impl;

import uz.bandla.exp.auth.ProfileNotFoundException;
import uz.bandla.repository.ProfileRepository;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
