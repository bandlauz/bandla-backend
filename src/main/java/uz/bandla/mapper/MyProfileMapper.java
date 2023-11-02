package uz.bandla.mapper;

import uz.bandla.dto.profile.MyProfileResponseDTO;
import uz.bandla.entity.ProfileEntity;

import org.springframework.stereotype.Component;

@Component
public class MyProfileMapper {

    public MyProfileResponseDTO map(ProfileEntity profile) {
        return new MyProfileResponseDTO(profile.getId(), profile.getFirstName(),
                profile.getLastName(), profile.getPhotoUrl(),
                profile.getPhoneNumber(), profile.getCreatedDate());
    }
}