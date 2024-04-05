package uz.bandla.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import uz.bandla.dto.company.response.CompanyDTO;
import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.enums.CompanyStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    boolean existsByAdmin(ProfileEntity admin);

    @Query(value = "SELECT new uz.bandla.dto.company.response.CompanyDTO(w.id,w.name,w.address,w.photoUrl,w.status,w.createdDate) from CompanyEntity as w")
    List<CompanyDTO> getShortInfoList();


    @Transactional
    @Modifying
    @Query("Update CompanyEntity as w " +
            "set w.status = :status " +
            "where w.id = :id")
    void updateStatusById(@Param("id") Integer id, @Param("status") CompanyStatus status);

    Optional<CompanyEntity> findByAdmin_Id(Integer adminId);
}