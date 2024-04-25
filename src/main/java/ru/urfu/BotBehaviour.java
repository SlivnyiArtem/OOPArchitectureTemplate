package ru.urfu;

/**
 * Параметры поведения бота
 */
public class BotBehaviour {
    /**
     * Как бот формирует ответное сообщение
     * @param inputMessage - входящее сообщения
     */
    public String makeAnswer(String inputMessage){
        return "\"Ваше сообщение: '%s'\"".formatted(inputMessage);
    }

}
