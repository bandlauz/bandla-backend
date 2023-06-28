package uz.nazarovctrl.bandla.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import uz.nazarovctrl.bandla.repository.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumberAndIsVisible(username, true);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        ProfileEntity profile = optional.get();
        return new CustomUserDetail(profile.getPhoneNumber(), profile.getPassword(), profile.getStatus(), profile.getRole(), profile.isVisible());
    }


}
