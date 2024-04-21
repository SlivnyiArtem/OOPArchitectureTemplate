package ru.urfu;

/**
 * Параметры поведения бота
 */
public class BotBehaviour {
    /**
     * Как бот формирует ответное сообщение
     * @param chatId - id чата
     * @param inputMessage - входящее сообщения
     */
    public String makeAnswer(String chatId, String inputMessage){
        return "\"Ваше сообщение: '%s'\"".formatted(inputMessage);
    }

}
