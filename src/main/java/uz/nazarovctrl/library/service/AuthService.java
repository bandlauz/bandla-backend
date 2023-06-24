package uz.nazarovctrl.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import uz.nazarovctrl.library.dto.ProfileCreateDTO;
import uz.nazarovctrl.library.dto.ProfileVerifyDTO;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileService profileService;

    public String registration(ProfileCreateDTO profile, BindingResult result, Model model) {

        if (!profile.getPhoneNumber().matches("[+]9[98][0-9]{10}")) {
            result.rejectValue("phoneNumber", null, "In correct phone number");
        }

        if (profileService.existsByPhoneNumber(profile.getPhoneNumber())) {
            result.rejectValue("phoneNumber", null, "User already registered");
        }

        if (result.hasErrors()) {
            model.addAttribute("profile", profile);
            return "registration";
        }

        profileService.saveProfile(profile);
        model.addAttribute("dto", new ProfileVerifyDTO(profile.getPhoneNumber()));
//        return "redirect:/auth/verify?phone=" + profile.getPhoneNumber();
        return "send";
    }


    public void sendSms(String phone, Model model) {
        System.out.println("send sms" + phone);
    }

    public String verify(ProfileVerifyDTO verifyDTO, Model model) {
        return "verify";
    }
}
