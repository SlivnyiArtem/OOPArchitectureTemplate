package ru.urfu;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска приложения
 */
public class Application {

    public static void main(String[] args) {
        ReplyBuilder replyBuilder = new YourMessageReplyBuilder();

        ArrayList<Bot> bots = new ArrayList<>(List.of(
                new TelegramBot(System.getenv("telegram_botName"), System.getenv("telegram_token"), replyBuilder),
                new DiscordBot(System.getenv("discord_token"), replyBuilder))
        );
        for (Bot bot: bots){
            bot.start();
        }
        // сколько угодно чат платформ и все должны работать одинаково
    }

}
