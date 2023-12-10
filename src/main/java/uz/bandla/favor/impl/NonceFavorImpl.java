package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.NonceEntity;
import uz.bandla.exp.NotFoundException;
import uz.bandla.favor.NonceFavor;
import uz.bandla.repository.NonceRepository;

import java.util.Optional;
import java.util.UUID;

@Favor
public class NonceFavorImpl implements NonceFavor {
    private final NonceRepository repository;

    public NonceFavorImpl(NonceRepository repository) {
        this.repository = repository;
    }

    @Override
    public String create() {
        String id = UUID.randomUUID().toString();
        NonceEntity entity = repository.save(new NonceEntity(id));
        return entity.getId();
    }

    @Override
    public NonceEntity findByIdOrElseTrow(String nonce) {
        Optional<NonceEntity> optional = repository.findById(nonce);
        if (optional.isEmpty()) {
            throw new NotFoundException("Nonce not found");
        }
        return optional.get();
    }

    @Override
    public void save(NonceEntity nonceEntity) {
        repository.save(nonceEntity);
    }
}