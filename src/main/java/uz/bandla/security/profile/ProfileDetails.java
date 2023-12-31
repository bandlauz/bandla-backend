package uz.bandla.security.profile;

import uz.bandla.entity.ProfileEntity;
import uz.bandla.enums.ProfileRole;
import uz.bandla.enums.ProfileStatus;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class ProfileDetails implements UserDetails {
    private final ProfileEntity profile;

    public ProfileDetails(ProfileEntity profile) {
        this.profile = profile;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(profile.getRole().name()));
    }

    @Override
    public String getPassword() {
        return profile.getPassword();
    }

    @Override
    public String getUsername() {
        return profile.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return profile.getStatus().equals(ProfileStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return profile.getIsVisible();
    }

    public ProfileRole getRole() {
        return profile.getRole();
    }

    public ProfileEntity getProfile() {
        return profile;
    }
}
