package ru.urfu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Телеграм бот
 */
public class TelegramBot extends TelegramLongPollingBot implements Bot {

    private final String telegramBotName;
    private final ReplyBuilder replyBuilder;

    public TelegramBot(String telegramBotName, String token, ReplyBuilder replyBuilder) {
        super(token);
        this.telegramBotName = telegramBotName;
        this.replyBuilder = replyBuilder;
    }

    public void start() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
            System.out.println("Telegram бот запущен");
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось запустить телеграм бота", e);
        }
    }

    /**
     * реагирует на входящие сообщения
     *
     * @param chatId       - id чата
     * @param inputMessage - входящее сообщение
     */
    @Override
    public void handle(String chatId, String inputMessage) {
        String reply = replyBuilder.generateReply(inputMessage);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(reply);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("Не удалось отправить сообщение. " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message updateMessage = update.getMessage();
            Long chatId = updateMessage.getChatId();
            String messageFromUser = updateMessage.getText();

            handle(String.valueOf(chatId), messageFromUser);
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotName;
    }
}
