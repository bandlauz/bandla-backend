package uz.nazarovctrl.library.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import uz.nazarovctrl.library.dto.ProfileCreateDTO;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileService profileService;

    public String registration(ProfileCreateDTO profile, BindingResult result, Model model) {
        if (profileService.existsByEmail(profile.getEmail())) {
            result.rejectValue("email", null, "User already registered");
        }
        if (result.hasErrors()) {
            model.addAttribute("profile", profile);
            return "/registration";
        }
        profileService.saveProfile(profile);
        return "redirect:/auth/login";
    }

}
