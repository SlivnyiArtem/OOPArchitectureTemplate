package ru.urfu;

public class YourMessageReplyBuilder implements ReplyBuilder{

    /**
     * генерация ответа
     *
     * @param inputText - входящий текст
     * @return сгенерированный ответ
     */
    @Override
    public String generateReply(String inputText) {
        return "\"Ваше сообщение: '" + inputText + "'";
    }
}
