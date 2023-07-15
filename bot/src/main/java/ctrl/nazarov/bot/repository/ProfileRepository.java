package ctrl.nazarov.bot.repository;

import ctrl.nazarov.bot.entity.ProfileEntity;
import ctrl.nazarov.bot.enums.ProfileRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ctrl.nazarov.bot.enums.Step;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    boolean existsByUserId(Long userId);


    @Query("SELECT languageCode FROM ProfileEntity " +
            "WHERE userId=?1")
    String getLanguageCode(Long chatId);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity SET languageCode=?2 " +
            "WHERE userId=?1")
    void changeLanguageByUserId(Long userId, String languageCode);

    @Query("select isRegistered from ProfileEntity " +
            "where userId=?1")
    boolean isRegistered(Long chatId);

    @Query("SELECT id FROM ProfileEntity  " +
            "WHERE userId=?1")
    Integer getIdByUserId(Long userId);

    @Query("SELECT step FROM ProfileEntity " +
            "WHERE userId=?1")
    Step getStepByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity " +
            "SET step=?2 " +
            "WHERE userId=?1")
    void changeStep(Long chatId, Step step);


    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity " +
            "SET visible=?2 " +
            "WHERE  userId=?1 ")
    void changeVisibleByUserId(Long userId, boolean visible);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity " +
            "SET name=?2 " +
            "WHERE  userId=?1")
    void changeNameByChatId(Long userId, String name);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity " +
            "SET isRegistered=?2 " +
            "WHERE userId=?1")
    void changeRegisteredField(Long chatId, boolean isRegistered);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ProfileEntity " +
            "SET phone=?2 " +
            "WHERE userId=?1")
    void changePhoneNumber(Long userId, String phoneNumber);

    ProfileEntity findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity " +
            "SET role=?2 " +
            "WHERE userId=?1")
    void changeRole(Long userId, ProfileRole admin);

    @Query("select count(p) from ProfileEntity as p " +
            "where p.visible=true ")
    int getUserCount();


    List<ProfileEntity> findByVisible(Boolean visible);
}
