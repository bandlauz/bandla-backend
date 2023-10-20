package uz.bandla.service;

import uz.bandla.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileService {
    Optional<ProfileEntity> findByPhoneNumber(String phoneNumber);

    void save(ProfileEntity profile);

    ProfileEntity findByPhoneNumberOrElseThrow(String phoneNumber);

    void savePassword(Integer id, String password);
}
