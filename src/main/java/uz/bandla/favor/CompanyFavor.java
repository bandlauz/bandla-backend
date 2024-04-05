package uz.bandla.favor;

import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.enums.CompanyStatus;

import java.util.List;
import java.util.Optional;

public interface CompanyFavor {
    void save(CompanyEntity entity);

    boolean hasCompany(ProfileEntity profile);

    List<CompanyDTO> getList();

    void updateStatus(Integer id,CompanyStatus status);

    CompanyEntity findById(Integer id);

    Optional<CompanyEntity> findByAdminId(Integer id);
}