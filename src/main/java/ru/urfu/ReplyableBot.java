package ru.urfu;

/**
 * Бот, отвечающий на сообщения>
 */

public interface ReplyableBot extends Bot{
    /**
     * Отправляет ответ на входящее сообщение
     * @param chatId - id чата
     * @param inputMessage - входящее сообщение
     */
    void sendReplyToMessage(String chatId, String inputMessage);


}
