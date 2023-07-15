package ctrl.nazarov.bot.bot.sentence;

import ctrl.nazarov.bot.enums.ButtonKey;
import ctrl.nazarov.bot.enums.SentenceKey;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class SentenceService {

    private final Map<SentenceKey, SentenceDTO> sentenceMap = new HashMap<>();

    private final Map<ButtonKey, SentenceDTO> buttonMap = new HashMap<>();

    public String getSentence(SentenceKey key, String languageCode) {
        return getText(sentenceMap.get(key), languageCode);
    }

    public ButtonKey getButtonKey(String text) {
        Set<Map.Entry<ButtonKey, SentenceDTO>> entries = buttonMap.entrySet();

        for (Map.Entry<ButtonKey, SentenceDTO> entry : entries) {
            SentenceDTO value = entry.getValue();
            if (value.getNameUz().equals(text) || value.getNameRu().equals(text) || value.getNameEn().equals(text)) {
                return entry.getKey();
            }
        }
        return null;
    }


    private String getText(SentenceDTO sentenceDTO, String languageCode) {

        if (languageCode.equals("uz")) {
            return sentenceDTO.getNameUz();
        }

        if (languageCode.equals("ru")) {
            return sentenceDTO.getNameRu();
        }

        if (languageCode.equals("en")) {
            return sentenceDTO.getNameEn();
        }

        return null;
    }

    public String getButtonSentence(ButtonKey buttonKey, String languageCode) {
        return getText(buttonMap.get(buttonKey), languageCode);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            initializeSentence();
            initializeButtonText();
        };
    }

    private void addSentence(SentenceKey key, String nameUz, String nameRu, String nameEn) {
        sentenceMap.put(key, new SentenceDTO(nameUz, nameRu, nameEn));
    }

    private void addButtonSentence(ButtonKey key, String nameUz, String nameRu, String nameEn) {
        buttonMap.put(key, new SentenceDTO(nameUz, nameRu, nameEn));
    }

    private void initializeSentence() {
        addSentence(SentenceKey.START, "Salom *%s* ! \nSiz bu bot orqali stadion band qilishingiz mumkin",
                "Привет *%s* ! \nВы можете забронировать стадион через этого бота",
                "Hello *%s* ! \nYou can book a stadium through this bot");

        addSentence(SentenceKey.RESTART, "Botni qayta ishga tushirganingizdan xursandmiz\uD83C\uDF89",
                "Рад, что вы перезапустили бота\uD83C\uDF89",
                "We're glad you've restarted the bot\uD83C\uDF89");

        addSentence(SentenceKey.HELP, "Bu bot Katkit jamoasiga tegishli \nBatafsil ma'lumot uchun @katkituz",
                "Этот бот принадлежит команде Katkit \nДля получения дополнительной информации @katkituz",
                "This bot belongs to the Katkit team\nFor more information @katkituz");

        addSentence(SentenceKey.LANGUAGE, "*Joriy til* _%s_ \nTilni o'zgartirish uchun \nquyidagilardan birini tanlang",
                "*Текущий язык* _%s_ \nВыберите один из следующих вариантов \nчтобы изменить язык",
                "*Current language* _%s_ \nSelect one of the following \nto change the language");

        addSentence(SentenceKey.CHECK, "Tekshirish ✅",
                "Проверять ✅",
                "Check ✅");

        addSentence(SentenceKey.SUBSCRIBE, "Quyidagi chatlarga obuna bo'ling",
                "Подпишитесь на чаты ниже",
                "Subscribe to the chats below");


        addSentence(SentenceKey.LANGUAGE_CHANGED, "Til muvaffaqiyatli o'zgartirildi \uD83C\uDDFA\uD83C\uDDFF",
                "Язык успешно изменен \uD83C\uDDF7\uD83C\uDDFA",
                "Language changed successfully \uD83C\uDDEC\uD83C\uDDE7");

        addSentence(SentenceKey.REQUEST_NAME, "Ism va familyangizni kiriting",
                "Введите свое имя и фамилию",
                "Enter your first and last name");

        addSentence(SentenceKey.HOME, "Bosh sahifa",
                "Главная",
                "Homepage");

        addSentence(SentenceKey.REQUEST_CONTACT,
                "Telefon raqamingizni yuboring",
                "Отправьте свой номер телефона",
                "Send your phone number");

        addSentence(SentenceKey.REQUEST_BIO, "*O'zingiz haqida qisqacha ma'lumot kiriting \n Masalan:* \n_Men Backend dasturchiman_",
                "*Напишите краткое описание себя \n Например:* \n_Я Backend-разработчик_",
                "*Enter a brief description of yourself \n Example:* \n_I am a Backend developer_");

        addSentence(SentenceKey.NAME,
                "Ism va Familya",
                "Имя и Фамилия",
                "First name and last name");

        addSentence(SentenceKey.PHONE_NUMBER, "Telefon raqam",
                "Номер телефона",
                "Phone number");

        addSentence(SentenceKey.INFORMATION, "O'zim haqimda",
                "О себе",
                "About myself");

        addSentence(SentenceKey.PROFILE_EDIT,
                "Siz quyidagi tugmalar orqali \nprofilingizni tahrirlashingiz mumkin ",
                "Вы с помощью следующих кнопок\nвы можете редактировать свой профиль",
                "You through the buttons below\nyou can edit your profile");

        addSentence(SentenceKey.REQUEST_PROFESSION,
                "Mutaxasisligingizni kiriting\n Namuna\n Backend devloper pyhton ",
                "Введите свою специальность\n Образец\n Бэкэнд-разработчик python",
                "Enter your specialty\nSample\nBackend developer python");


        addSentence(SentenceKey.NUMBER_CHANGED,
                "Telefon raqam o'zgartildi",
                "Номер телефона был изменен",
                "The phone number has been changed");

        addSentence(SentenceKey.NAME_CHANGED,
                "Ism va familya o'zgartirildi",
                "Имя и фамилия изменены",
                "Name and surname have been changed");

        addSentence(SentenceKey.BIO_CHANGED,
                "O'zim haqimdagi ma'lumot o'zgartirildi",
                "Моя информация была изменена",
                "My information has been changed");

        addSentence(SentenceKey.PROFESSION_CHANGED, "Mutaxasislik o'zgartirildi",
                "Специальность была изменена",
                "Specialty has been changed");

        addSentence(SentenceKey.PROFILE,
                "Profil sahifasi",
                "Профильная страница",
                "Profile page");


        addSentence(SentenceKey.SIGN_UP_CONFIRM,
                "Ro'yxatdan muvaffaqiyatli tarzda o'tildi",
                "Регистрация прошла успешно",
                "Registration completed successfully");

        addSentence(SentenceKey.SIGN_UP_CANCEL,
                "Ro'yxatdan o'tish bekor qilindi",
                "Регистрация была отменена",
                "Registration has been cancelled");

        addSentence(SentenceKey.START_ADMIN,
                "Salom  \nTugmalardan birini tanlang",
                "Привет  \nВыберите одну из кнопок",
                "Hello  \nSelect one of the buttons");

        addSentence(SentenceKey.STATISTIC,
                "*Jami Foydalanuvchilar soni:* _%s_",
                "*Общее количество пользователей:* _%s_",
                "*Total Number of Users:* _%s_");

        addSentence(SentenceKey.POST_REQUEST, "Postni jonating",
                "Отправить сообщение",
                "Send the post");

        addSentence(SentenceKey.INVALID_PHONE,
                "Telefon raqam yaroqsiz\nNamuna 998991234567",
                "Номер телефона недействителен\nОбразец 998991234567",
                "The phone number is invalid\nExample 998991234567");

        addSentence(SentenceKey.ORDER,
                "Hududni tanlang",
                "Выберите область",
                "Choose a region");

        addSentence(SentenceKey.REGION_NAME_REQUEST,
                "Hududni nomini kiriting",
                "Введите название",
                "Enter the name of the region");

        addSentence(SentenceKey.REGION_ADDED,
                "Hudud tizimga qo'shildi",
                "Район добавлен в систему",
                "The area has been added to the system");
    }


    private void initializeButtonText() {
        addButtonSentence(ButtonKey.ORDER, "\uD83D\uDC64 Order",
                "\uD83D\uDC64 Order",
                "\uD83D\uDC64 Order");

        addButtonSentence(ButtonKey.PROFILE, "\uD83D\uDC64 Profil",
                "\uD83D\uDC64 Профиль",
                "\uD83D\uDC64 Profile");

        addButtonSentence(ButtonKey.SIGN_UP,
                "\uD83D\uDC64 Ro'yxatdan o'tish",
                "\uD83D\uDC64 Зарегистрироваться",
                "\uD83D\uDC64 Registration");

        addButtonSentence(ButtonKey.BACK, "↪️Orqaga",
                "↪️Назад",
                "↪️Back");

        addButtonSentence(ButtonKey.HOME,
                "\uD83C\uDFD8 Bosh sahifaga",
                "\uD83C\uDFD8 На главную страницу",
                "\uD83C\uDFD8 To the home page");

        addButtonSentence(ButtonKey.CONTACT, "\uD83D\uDCF1 Telefon raqamni yuborish",
                "\uD83D\uDCF1 Отправить номер телефона",
                "\uD83D\uDCF1 Send phone number");


        addButtonSentence(ButtonKey.CONFIRM, "Tasdiqlash✅",
                "Подтверждение✅",
                "Confirm✅");

        addButtonSentence(ButtonKey.CHANGE_NAME, "\uD83D\uDDE3 Ism Familya \uD83D\uDD04",
                "\uD83D\uDDE3 Имя и фамилия \uD83D\uDD04",
                "\uD83D\uDDE3 Name and Surname \uD83D\uDD04");

        addButtonSentence(ButtonKey.CHANGE_PHONE, "\uD83D\uDCF1 Telefon raqam \uD83D\uDD04",
                "\uD83D\uDCF1 Номер телефона \uD83D\uDD04",
                "\uD83D\uDCF1 Phone number \uD83D\uDD04");

        addButtonSentence(ButtonKey.STATISTICS, "Statistika \uD83D\uDCCA",
                "Статистика \uD83D\uDCCA",
                "Statistics \uD83D\uDCCA");

        addButtonSentence(ButtonKey.POST_CREATE, "Post yaratish",
                "Создать сообщение",
                "Create a post");

        addButtonSentence(ButtonKey.REGIONS, "Hududlar",
                "Регионы",
                "Regions");

        addButtonSentence(ButtonKey.REGION_ADD, "Hudud qo'shish",
                "Добавить регион ",
                "Add region");
    }

}
