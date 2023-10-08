package uz.nazarovctrl.bandla.mapper;

import uz.nazarovctrl.bandla.dto.profile.ProfileResponseDTO;
import uz.nazarovctrl.bandla.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileResponseDTO map(ProfileEntity entity) {
        return ProfileResponseDTO.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .isVisible(entity.getIsVisible())
                .createdDate(entity.getCreatedDate()).build();
    }
}
