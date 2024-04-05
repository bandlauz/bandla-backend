package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.exp.auth.ProfileNotFoundException;
import uz.bandla.favor.ProfileFavor;
import uz.bandla.repository.ProfileRepository;

import java.util.Optional;

@Favor
public class ProfileFavorImpl implements ProfileFavor {
    private final ProfileRepository repository;

    public ProfileFavorImpl(ProfileRepository repository) {
        this.repository = repository;
    }

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
