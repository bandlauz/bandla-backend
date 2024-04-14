package uz.bandla.security.profile;

import uz.bandla.entity.ProfileEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.bandla.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileDetailsService implements UserDetailsService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) {
        ProfileEntity profile = profileRepository.getByPhoneNumberAndIsVisibleTrue(phoneNumber);
        return new ProfileDetails(profile);
    }
}
