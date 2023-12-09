package uz.bandla.repository;

import uz.bandla.entity.NonceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NonceRepository extends JpaRepository<NonceEntity, String> {
}