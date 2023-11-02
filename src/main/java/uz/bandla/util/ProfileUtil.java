package uz.bandla.util;

import uz.bandla.entity.ProfileEntity;
import uz.bandla.security.profile.ProfileDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ProfileUtil {
    public static ProfileEntity getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ProfileDetails profileDetails = (ProfileDetails) authentication.getPrincipal();
        return profileDetails.getProfile();
    }
}