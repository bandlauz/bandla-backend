package uz.bandla.repository;

import uz.bandla.entity.NonceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bandla.exp.NotFoundException;

import java.util.Optional;

public interface NonceRepository extends JpaRepository<NonceEntity, String> {
    default NonceEntity findByIdOrElseThrow(String id) {
        Optional<NonceEntity> optional = findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Nonce not found");
        }
        return optional.get();
    }
}