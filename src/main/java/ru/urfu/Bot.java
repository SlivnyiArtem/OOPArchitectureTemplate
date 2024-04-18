package ru.urfu;

/**
 * Bot
 */
public interface Bot {
    /**
     * Может стартовать
     */
    void start();

    /**
     * реагирует на входящие сообщения
     * @param chatId - id чата
     * @param inputMessage - входящее сообщение
     */
    void handle(String chatId, String inputMessage)
}
