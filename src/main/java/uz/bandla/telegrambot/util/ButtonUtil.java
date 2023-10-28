package uz.bandla.telegrambot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonUtil {

    public static ReplyKeyboardMarkup getSendContactMarKup() {
        KeyboardButton button = new KeyboardButton();
        button.setText("Telefon raqamni jo'natish");
        button.setRequestContact(true);
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setKeyboard(keyboard);
        return markup;
    }

    public static ReplyKeyboardRemove getKeyboardRemove() {
        ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
        keyboardRemove.setRemoveKeyboard(true);
        return keyboardRemove;
    }
}