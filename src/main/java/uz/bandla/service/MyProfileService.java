package uz.bandla.service;

import org.springframework.http.ResponseEntity;
import uz.bandla.dto.Response;
import uz.bandla.dto.profile.MyProfileDTO;
import uz.bandla.dto.profile.MyProfileResponseDTO;

public interface MyProfileService {

    ResponseEntity<Response<MyProfileResponseDTO>> get();

    ResponseEntity<Response<MyProfileResponseDTO>> update(MyProfileDTO dto);

    ResponseEntity<Response<MyProfileResponseDTO>> updatePhoto(String photoUrl);
}