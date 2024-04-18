package ru.urfu;

public class YourMessageReplyBuilder implements ReplyBuilder{
    @Override
    public String generateReply(String inputText) {
        return "\"Ваше сообщение: '" + inputText + "'";
    }
}
