package uz.nazarovctrl.bandla.security.profile;

import lombok.RequiredArgsConstructor;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.bandla.service.ProfileService;


@Service
@RequiredArgsConstructor
public class ProfileDetailsService implements UserDetailsService {
    private final ProfileService profileService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) {
        ProfileEntity profile = profileService.findByPhoneNumberOrElseThrow(phoneNumber);
        return new ProfileDetails(profile);
    }
}
