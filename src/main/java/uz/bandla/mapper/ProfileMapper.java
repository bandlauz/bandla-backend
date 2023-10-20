package uz.bandla.mapper;

import uz.bandla.dto.profile.ProfileResponseDTO;
import uz.bandla.entity.ProfileEntity;

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
