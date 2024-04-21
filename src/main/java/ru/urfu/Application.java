package ru.urfu;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска приложения
 */
public class Application {

    public static void main(String[] args) {
        BotBehaviour botBehaviour = new BotBehaviour();



        List<Bot> bots = null;
        for (Bot bot: bots){
            bot.start();
        }
    }

}
