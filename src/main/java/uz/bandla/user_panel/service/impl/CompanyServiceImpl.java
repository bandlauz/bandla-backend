package uz.bandla.user_panel.service.impl;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.exp.CompanyExistsException;
import uz.bandla.user_panel.favor.CompanyFavor;
import uz.bandla.user_panel.dto.company.request.CreateCompanyDTO;
import uz.bandla.user_panel.dto.company.response.CompanyDTO;
import uz.bandla.user_panel.mapper.CompanyMapper;
import uz.bandla.user_panel.service.CompanyService;
import uz.bandla.util.ProfileUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyFavor companyFavor;
    private final CompanyMapper companyMapper;

    @Override
    public ResponseEntity<Response<CompanyDTO>> create(CreateCompanyDTO dto) {
        ProfileEntity profile = ProfileUtil.getProfile();
        if (companyFavor.hasCompany(profile)) {
            throw new CompanyExistsException("Faqatgina bitta company yaratish mumkin");
        }

        CompanyEntity company = companyMapper.map(dto);
        company.setAdmin(profile);

        companyFavor.save(company);
        return GoodResponse.ok(companyMapper.map(company));
    }
}