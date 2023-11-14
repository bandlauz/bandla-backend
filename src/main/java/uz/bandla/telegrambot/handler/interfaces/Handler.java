package uz.bandla.telegrambot.handler.interfaces;

public interface Handler<T> {
    void handle(T message);
}