package ru.urfu;

/**
 * генератор ответа бота - генерирует ответ по входящему тексту
 */
public interface ReplyBuilder {
    /**
     * генерация ответа
     * @param inputText - входящий текст
     * @return сгенерированный ответ
     */
    String generateReply(String inputText);
}
