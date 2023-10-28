package uz.bandla.telegrambot.handler;

public interface Handler<T> {
    void handle(T message);
}