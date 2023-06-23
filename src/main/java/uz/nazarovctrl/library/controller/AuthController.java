package uz.nazarovctrl.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.nazarovctrl.library.dto.ProfileCreateDTO;
import uz.nazarovctrl.library.entity.ProfileEntity;
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

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("profile") ProfileCreateDTO profile,
                               BindingResult result, Model model) {
        return authService.registration(profile, result, model);
    }

}
