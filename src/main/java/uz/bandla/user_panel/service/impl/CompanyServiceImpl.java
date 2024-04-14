package uz.bandla.user_panel.service.impl;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.exp.CompanyExistsException;
import uz.bandla.dto.company.request.CreateCompanyDTO;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.mapper.CompanyMapper;
import uz.bandla.repository.CompanyRepository;
import uz.bandla.user_panel.service.CompanyService;
import uz.bandla.util.ProfileUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public ResponseEntity<Response<CompanyDTO>> create(CreateCompanyDTO dto) {
        ProfileEntity profile = ProfileUtil.getProfile();
        if (companyRepository.existsByAdmin(profile)) {
            throw new CompanyExistsException("Faqatgina bitta company yaratish mumkin");
        }

        CompanyEntity company = companyMapper.map(dto);
        company.setAdmin(profile);

        companyRepository.save(company);
        return GoodResponse.ok(companyMapper.map(company));
    }

    @Override
    public ResponseEntity<Response<CompanyDTO>> find() {
        ProfileEntity admin = ProfileUtil.getProfile();
        Optional<CompanyEntity> optionalEntity = companyRepository.findByAdmin(admin);

        return GoodResponse.ok(companyMapper.map(optionalEntity));
    }
}