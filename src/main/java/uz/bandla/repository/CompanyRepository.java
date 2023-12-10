package uz.bandla.repository;

import uz.bandla.entity.CompanyEntity;
import uz.bandla.entity.ProfileEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    boolean existsByAdmin(ProfileEntity admin);
}