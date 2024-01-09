package uz.bandla.sysadmin_panel.controller;

import uz.bandla.dto.Response;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.sysadmin_panel.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SysadminCompanyController")
@RequestMapping("/api/sysadmin-panel/company")
@RequiredArgsConstructor
@Tag(name = "Company controller")
public class CompanyController {
    private final CompanyService companyService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create company")
    @GetMapping("/list")
    public ResponseEntity<Response<List<CompanyDTO>>> getList() {
        return companyService.getList();
    }
}