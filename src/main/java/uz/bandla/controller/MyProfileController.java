package uz.bandla.controller;

import uz.bandla.dto.Response;
import uz.bandla.annotations.validation.Url;
import uz.bandla.dto.profile.MyProfileDTO;
import uz.bandla.dto.profile.MyProfileResponseDTO;
import uz.bandla.service.MyProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profile/my")
@RequiredArgsConstructor
@Validated
@Tag(name = "My profile controller")
public class MyProfileController {
    private final MyProfileService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get profile information")
    @GetMapping
    public ResponseEntity<Response<MyProfileResponseDTO>> get() {
        return service.get();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update profile information")
    @PutMapping("/update")
    public ResponseEntity<Response<MyProfileResponseDTO>> update(@RequestBody @Valid MyProfileDTO dto) {
        return service.update(dto);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update profile photo")
    @PutMapping(value = {"/update-photo/{photoUrl}","/update-photo"})
    public ResponseEntity<Response<MyProfileResponseDTO>> updatePhoto(
            @PathVariable(value = "photoUrl",required = false) @Url(canBeNull = true) String photoUrl) {
        return service.updatePhoto(photoUrl);
    }
}