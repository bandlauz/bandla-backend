package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.NonceEntity;
import uz.bandla.favor.NonceFavor;
import uz.bandla.repository.NonceRepository;

import java.util.UUID;

@Favor
public class NonceFavorImpl implements NonceFavor {
    private final NonceRepository repository;

    public NonceFavorImpl(NonceRepository repository) {
        this.repository = repository;
    }

    @Override
    public String create() {
        NonceEntity entity = repository.save(new NonceEntity(UUID.randomUUID()));
        return entity.getId().toString();
    }
}