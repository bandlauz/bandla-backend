package uz.nazarovctrl.bandla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import uz.nazarovctrl.bandla.repository.ProfileRepository;
import uz.nazarovctrl.bandla.dto.ProfileCreateDTO;
import uz.nazarovctrl.bandla.dto.ProfileVerifyDTO;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import uz.nazarovctrl.bandla.enums.Status;
import uz.nazarovctrl.bandla.exp.InvalidSmsCode;
import uz.nazarovctrl.bandla.exp.LimitOutPutException;
import uz.nazarovctrl.bandla.exp.PhoneNumberIsNotRegistered;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final SmsService smsService;
    private final SmsHistoryService smsHistoryService;

    public String registration(ProfileCreateDTO profile, BindingResult result, Model model) {

        if (!profile.getPhoneNumber().matches("[+]9[98][0-9]{10}")) {
            result.rejectValue("phoneNumber", HttpStatus.BAD_REQUEST.name(), "In correct phone number");
        }

        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumberAndIsVisible(profile.getPhoneNumber(), true);
        if (optional.isPresent()) {
            ProfileEntity entity = optional.get();
            if (entity.getStatus().equals(Status.NOT_VERIFIED)) {
                profileRepository.delete(entity);
            } else {
                result.rejectValue("phoneNumber", HttpStatus.BAD_REQUEST.name(), "User already registered");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("profile", profile);
            return "registration";
        }

        Function<ProfileCreateDTO, ProfileEntity> function = dto -> ProfileEntity.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber()).build();

        profileRepository.save(function.apply(profile));
        model.addAttribute("dto", new ProfileVerifyDTO(profile.getPhoneNumber()));
        return "verify";
    }


    public String sendSms(String phone) {
        phone = "+" + phone.trim();

        Long countInMinute = smsHistoryService.getCountInTwoMinute(phone);
        if (countInMinute >= 1) {
            throw new LimitOutPutException("Resent limit");
        }

        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumberAndIsVisible(phone, true);
        if (optional.isEmpty()) {
            throw new PhoneNumberIsNotRegistered("Phone number is not registered");
        }
        smsService.sendSms(phone);
        return "success";
    }


    public String verify(ProfileVerifyDTO verifyDTO) {

        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumberAndIsVisible(verifyDTO.getPhoneNumber(), true);
        if (optional.isEmpty()) {
            throw new PhoneNumberIsNotRegistered("Phone number is not registered");
        }


        if (!smsHistoryService.check(verifyDTO.getPhoneNumber(), verifyDTO.getCode())) {
            throw new InvalidSmsCode("Invalid sms code");
        }
        profileRepository.updateStatusByPhoneNumber(verifyDTO.getPhoneNumber(), Status.ACTIVE);


        return "success";
    }
}
