package uz.bandla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import uz.bandla.dto.Response;
import uz.bandla.dto.profile.MyProfileDTO;
import uz.bandla.dto.profile.MyProfileResponseDTO;
import uz.bandla.service.MyProfileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profile/my")
@RequiredArgsConstructor
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
}