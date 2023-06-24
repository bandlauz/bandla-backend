package uz.nazarovctrl.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.nazarovctrl.library.dto.ProfileCreateDTO;
import uz.nazarovctrl.library.dto.ProfileVerifyDTO;
import uz.nazarovctrl.library.service.AuthService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        ProfileCreateDTO profile = new ProfileCreateDTO();
        model.addAttribute("profile", profile);
        return "registration";
    }

    @PostMapping("/send")
    private String send(@ModelAttribute("dto") ProfileVerifyDTO dto, Model model) {
        authService.sendSms(dto.getPhoneNumber(), model);
        return "verify";
    }

    @PostMapping("/verify")
    private String verify(@ModelAttribute("dto") ProfileVerifyDTO verifyDTO, Model model) {
        return authService.verify(verifyDTO, model);
    }


    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("profile") ProfileCreateDTO profile,
                               BindingResult result, Model model) {
        return authService.registration(profile, result, model);
    }

}
