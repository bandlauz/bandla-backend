package uz.bandla.security.profile;

import uz.bandla.service.impl.ProfileServiceImpl;
import uz.bandla.entity.ProfileEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileDetailsService implements UserDetailsService {
    private final ProfileServiceImpl profileService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) {
        ProfileEntity profile = profileService.findByPhoneNumberOrElseThrow(phoneNumber);
        return new ProfileDetails(profile);
    }
}
