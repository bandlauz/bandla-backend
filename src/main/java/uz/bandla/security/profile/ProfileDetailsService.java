package uz.bandla.security.profile;

import uz.bandla.favor.ProfileFavor;
import uz.bandla.entity.ProfileEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileDetailsService implements UserDetailsService {
    private final ProfileFavor profileFavor;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) {
        ProfileEntity profile = profileFavor.findByPhoneNumberOrElseThrow(phoneNumber);
        return new ProfileDetails(profile);
    }
}
