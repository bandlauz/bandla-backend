package uz.bandla.user_panel.controller;

import uz.bandla.dto.Response;
import uz.bandla.user_panel.dto.company.request.CreateCompanyDTO;
import uz.bandla.user_panel.dto.company.response.CompanyDTO;
import uz.bandla.user_panel.service.CompanyService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-panel/company")
@RequiredArgsConstructor
@Tag(name = "Company controller")
public class CompanyController {
    private final CompanyService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create company")
    @PostMapping("/create")
    public ResponseEntity<Response<CompanyDTO>> create(@RequestBody @Valid CreateCompanyDTO dto) {
        return service.create(dto);
    }
}