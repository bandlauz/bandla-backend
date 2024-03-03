package uz.bandla.service.impl;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.dto.profile.MyProfileDTO;
import uz.bandla.dto.profile.MyProfileResponseDTO;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.favor.ProfileFavor;
import uz.bandla.mapper.MyProfileMapper;
import uz.bandla.service.MyProfileService;
import uz.bandla.util.ProfileUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyProfileServiceImpl implements MyProfileService {
    private final MyProfileMapper myProfileMapper;
    private final ProfileFavor profileFavor;

    @Override
    public ResponseEntity<Response<MyProfileResponseDTO>> get() {
        ProfileEntity profile = ProfileUtil.getProfile();
        return GoodResponse.ok(myProfileMapper.map(profile));
    }

    @Override
    public ResponseEntity<Response<MyProfileResponseDTO>> update(MyProfileDTO dto) {
        ProfileEntity profile = ProfileUtil.getProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());

        profileFavor.save(profile);

        return GoodResponse.ok(myProfileMapper.map(profile));
    }

    @Override
    public ResponseEntity<Response<MyProfileResponseDTO>> updatePhoto(String photoUrl) {
        ProfileEntity profile = ProfileUtil.getProfile();
        profile.setPhotoUrl(photoUrl);

        profileFavor.save(profile);

        return GoodResponse.ok(myProfileMapper.map(profile));
    }
}