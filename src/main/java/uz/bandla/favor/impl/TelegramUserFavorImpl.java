package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.TelegramUserEntity;
import uz.bandla.exp.auth.TelegramLoginException;
import uz.bandla.favor.TelegramUserFavor;
import uz.bandla.repository.TelegramUserRepository;

import java.util.Optional;

@Favor
public class TelegramUserFavorImpl implements TelegramUserFavor {

    private final TelegramUserRepository repository;

    public TelegramUserFavorImpl(TelegramUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TelegramUserEntity> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public TelegramUserEntity findByIdOrElseTrow(long id) {
        return repository.findById(id)
                .orElseThrow(TelegramLoginException::new);
    }

    @Override
    public void save(TelegramUserEntity telegramUser) {
        repository.save(telegramUser);
    }
}
