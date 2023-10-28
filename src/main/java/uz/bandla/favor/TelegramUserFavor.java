package uz.bandla.favor;

import uz.bandla.entity.TelegramUserEntity;

import java.util.Optional;

public interface TelegramUserFavor {


    Optional<TelegramUserEntity> findById(long id);

    TelegramUserEntity findByIdOrElseTrow(long id);

    void save(TelegramUserEntity telegramUser);

}
