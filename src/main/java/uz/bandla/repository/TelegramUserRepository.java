package uz.bandla.repository;

import uz.bandla.entity.TelegramUserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUserEntity, Long> {

}
