package uz.bandla.repository;

import uz.bandla.entity.CompanyEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
}