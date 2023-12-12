package uz.bandla.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    boolean existsByAdmin(ProfileEntity admin);

    @Query(value="SELECT new uz.bandla.dto.company.response.CompanyDTO(w.id,w.name,w.address,w.photoUrl,w.status,w.createdDate) from CompanyEntity as w")
    List<CompanyDTO> getShortInfoList();
}