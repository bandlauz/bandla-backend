package uz.bandla.repository;

import uz.bandla.entity.NonceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NonceRepository extends JpaRepository<NonceEntity, UUID> {
}