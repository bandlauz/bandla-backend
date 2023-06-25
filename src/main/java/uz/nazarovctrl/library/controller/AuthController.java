package uz.nazarovctrl.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @ResponseBody
    @GetMapping("/send")
    private ResponseEntity<String> send(@RequestParam("phone") String phoneNumber) {
        return ResponseEntity.ok(authService.sendSms(phoneNumber));
    }

    @ResponseBody
    @PostMapping("/verify")
    private ResponseEntity<String> verify(@RequestBody ProfileVerifyDTO verifyDTO) {
        return ResponseEntity.ok(authService.verify(verifyDTO));
    }


    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("profile") ProfileCreateDTO profile,
                               BindingResult result, Model model) {
        return authService.registration(profile, result, model);
    }

}
