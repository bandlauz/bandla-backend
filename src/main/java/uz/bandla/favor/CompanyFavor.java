package uz.bandla.favor;

import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;

import java.util.List;

public interface CompanyFavor {
    void save(CompanyEntity entity);

    boolean hasCompany(ProfileEntity profile);

    List<CompanyDTO> getList();
}