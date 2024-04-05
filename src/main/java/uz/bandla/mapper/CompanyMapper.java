package uz.bandla.mapper;

import uz.bandla.entity.CompanyEntity;
import uz.bandla.dto.company.request.CreateCompanyDTO;
import uz.bandla.dto.company.response.CompanyDTO;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyMapper {
    public CompanyEntity map(CreateCompanyDTO dto) {
        return new CompanyEntity(dto.getName(),
                dto.getAddress(),
                dto.getPhotoUrl());
    }

    public CompanyDTO map(CompanyEntity company) {
        return new CompanyDTO(company.getId(), company.getName(),
                company.getAddress(),
                company.getPhotoUrl(),
                company.getStatus(),
                company.getCreatedDate());
    }

    public CompanyDTO map(Optional<CompanyEntity> optional) {
        return optional.map(this::map).orElse(null);
    }
}