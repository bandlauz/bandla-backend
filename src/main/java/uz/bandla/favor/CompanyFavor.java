package uz.bandla.favor;

import org.springframework.http.ResponseEntity;
import uz.bandla.dto.Response;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;

import java.util.List;

public interface CompanyFavor {
    void save(CompanyEntity entity);

    boolean hasCompany(ProfileEntity profile);

    List<CompanyDTO> getList();
}