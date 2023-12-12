package uz.bandla.sysadmin_panel.service.impl;

import uz.bandla.dto.GoodResponse;
import uz.bandla.dto.Response;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.favor.CompanyFavor;
import uz.bandla.sysadmin_panel.service.CompanyService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SysadminCompanyService")
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyFavor companyFavor;

    @Override
    public ResponseEntity<Response<List<CompanyDTO>>> getList() {
        List<CompanyDTO> entityList = companyFavor.getList();
        return GoodResponse.ok(entityList);
    }
}