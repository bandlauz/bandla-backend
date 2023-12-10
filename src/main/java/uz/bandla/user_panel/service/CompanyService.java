package uz.bandla.user_panel.service;

import uz.bandla.dto.Response;
import uz.bandla.user_panel.dto.company.request.CreateCompanyDTO;
import uz.bandla.user_panel.dto.company.response.CompanyDTO;

import org.springframework.http.ResponseEntity;

public interface CompanyService {
    ResponseEntity<Response<CompanyDTO>> create(CreateCompanyDTO dto);
}