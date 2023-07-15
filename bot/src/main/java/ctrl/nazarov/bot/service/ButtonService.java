package ctrl.nazarov.bot.service;

import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.entity.RegionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ctrl.nazarov.bot.enums.ButtonKey;
import ctrl.nazarov.bot.util.ButtonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
@RequiredArgsConstructor
public class ButtonService {

    private final RegionService regionService;
    private final SentenceService sentenceService;

    public ReplyKeyboardMarkup getMenu(String languageCode) {
        List<KeyboardRow> rowList = ButtonUtil.rowList(
                ButtonUtil.row(ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.ORDER, languageCode)),
                        ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.PROFILE, languageCode))));

        return ButtonUtil.markup(rowList);
    }


    public ReplyKeyboardMarkup getAdminMenu(Long chatId, String languageCode) {
        List<KeyboardRow> rowList = ButtonUtil.rowList(
                ButtonUtil.row(
                        ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.STATISTICS, languageCode)),
                        ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.POST_CREATE, languageCode))),
                ButtonUtil.row(ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.REGIONS, languageCode)),
                        ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.REGION_ADD, languageCode))));

        return ButtonUtil.markup(rowList);

    }


    public InlineKeyboardMarkup getLanguagesButton(String languageCode) {


        InlineKeyboardButton buttonUz = new InlineKeyboardButton();
        buttonUz.setText("Uz \uD83C\uDDFA\uD83C\uDDFF ");
        buttonUz.setCallbackData("lang/uz");

        InlineKeyboardButton buttonRu = new InlineKeyboardButton();
        buttonRu.setText("Ru \uD83C\uDDF7\uD83C\uDDFA ");
        buttonRu.setCallbackData("lang/ru");

        InlineKeyboardButton buttonEN = new InlineKeyboardButton();
        buttonEN.setText("En \uD83C\uDDEC\uD83C\uDDE7");
        buttonEN.setCallbackData("lang/en");

        List<InlineKeyboardButton> row = new ArrayList<>();


        if (!languageCode.equals("uz")) {
            row.add(buttonUz);
        }
        if (!languageCode.equals("ru")) {
            row.add(buttonRu);
        }
        if (!languageCode.equals("en")) {
            row.add(buttonEN);
        }

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);


        return markup;
    }

    public ReplyKeyboardMarkup getBackAndHomeMarkup(String languageCode) {
        KeyboardRow backAndHomeRow = getBackAndHomeRow(languageCode);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(backAndHomeRow);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(keyboard);
        markup.setResizeKeyboard(true);
        return markup;
    }

    private KeyboardRow getBackAndHomeRow(String languageCode) {

        KeyboardButton back = new KeyboardButton();
        back.setText(sentenceService.getButtonSentence(ButtonKey.BACK, languageCode));

        KeyboardButton home = new KeyboardButton();
        home.setText(sentenceService.getButtonSentence(ButtonKey.HOME, languageCode));

        KeyboardRow row = new KeyboardRow();
        row.add(back);
        row.add(home);

        return row;
    }

    public ReplyKeyboardMarkup getEnterContactButton(String languageCode) {
        KeyboardButton contactButton = new KeyboardButton(sentenceService.getButtonSentence(ButtonKey.CONTACT, languageCode));
        contactButton.setRequestContact(true);
        KeyboardRow row = new KeyboardRow();
        row.add(contactButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public ReplyKeyboardMarkup getRequestContactButton(String languageCode) {
        KeyboardButton contactButton = new KeyboardButton(sentenceService.getButtonSentence(ButtonKey.CONTACT, languageCode));
        contactButton.setRequestContact(true);
        KeyboardRow row = new KeyboardRow();
        row.add(contactButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        keyboard.add(getBackAndHomeRow(languageCode));
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public ReplyKeyboardMarkup getConfirmMarkup(String languageCode) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        keyboard.add(ButtonUtil.row(ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.CONFIRM, languageCode)),
                ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.CANCEL, languageCode))));


        KeyboardRow backAndHomeRow = getBackAndHomeRow(languageCode);
        keyboard.add(backAndHomeRow);
        return ButtonUtil.markup(keyboard);

    }


    public ReplyKeyboardMarkup getEditProfileMarkup(String languageCode) {
        return ButtonUtil.markup(ButtonUtil.rowList(
                        ButtonUtil.row(
                                ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.CHANGE_NAME, languageCode)),
                                ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.CHANGE_PHONE, languageCode))
                        ),
                        getHomeRow(languageCode)
                )
        );
    }

    public ReplyKeyboardMarkup getHomeMarkup(String languageCode) {
        return ButtonUtil.markup(
                ButtonUtil.rowList(
                        ButtonUtil.row(ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.HOME, languageCode))))
        );

    }


    public KeyboardRow getHomeRow(String languageCode) {
        return ButtonUtil.row(ButtonUtil.button(sentenceService.getButtonSentence(ButtonKey.HOME, languageCode)));
    }

    public ReplyKeyboard getOrderMarkup() {
        Stack<RegionEntity> regionList = regionService.getList(true);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        markup.setKeyboard(keyboard);

        for (int i = 0; i + 1 < regionList.size(); i += 2) {
            KeyboardRow row = new KeyboardRow();
            row.add(regionList.pop().getName());
            row.add(regionList.pop().getName());
            keyboard.add(row);
        }

        if (!regionList.isEmpty()) {
            KeyboardRow row = new KeyboardRow();
            row.add(regionList.pop().getName());
            keyboard.add(row);
        }

        return markup;

    }
}
