package ru.urfu;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска приложения
 */
public class Application {

    public static void main(String[] args) {
        BotBehaviour botBehaviour = new BotBehaviour();

        List<Bot> bots = List.of(new DiscordBot(System.getenv("discord_token"), botBehaviour),
                new TelegramBot(System.getenv("telegram_botName"), System.getenv("telegram_token"), botBehaviour));
        for (Bot bot: bots){
            bot.start();
        }
    }

}
