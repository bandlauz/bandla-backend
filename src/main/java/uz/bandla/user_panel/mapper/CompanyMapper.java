package uz.bandla.user_panel.mapper;

import uz.bandla.entity.CompanyEntity;
import uz.bandla.user_panel.dto.company.request.CreateCompanyDTO;
import uz.bandla.user_panel.dto.company.response.CompanyDTO;

import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyEntity map(CreateCompanyDTO dto) {
        return new CompanyEntity(dto.getName(),
                dto.getAddress(),
                dto.getPhotoUrl());
    }

    public CompanyDTO map(CompanyEntity company) {
        return new CompanyDTO(company.getName(),
                company.getAddress(),
                company.getPhotoUrl(),
                company.getStatus(),
                company.getCreatedDate());
    }
}