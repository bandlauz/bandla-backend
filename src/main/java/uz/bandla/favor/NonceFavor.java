package uz.bandla.favor;

import uz.bandla.entity.NonceEntity;

public interface NonceFavor {

    String create();

    NonceEntity findByIdOrElseTrow(String nonce);

    void save(NonceEntity nonceEntity);
}