package ru.urfu;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * Дискорд бот
 */
public class DiscordBot implements Bot{

    private final String token;
    private final BotBehaviour botBehaviour;

    private GatewayDiscordClient client;

    public DiscordBot(String token, BotBehaviour botBehaviour) {
        this.token = token;
        this.botBehaviour = botBehaviour;
    }

    public void start() {
        client = DiscordClient.create(token).login().block();
        if (client == null) {
            throw new RuntimeException("Ошибка при входе в Discord");
        }
        client.on(MessageCreateEvent.class)
                .doOnError(throwable -> {
                    throw new RuntimeException("Ошибка при работе Discord бота", throwable);
                })
                .subscribe(event -> {
                    Message eventMessage = event.getMessage();
                    if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
                        String chanelID = eventMessage.getChannelId().asString();
                        String messageFromUser = eventMessage.getContent();

                        sendAnswer(chanelID, botBehaviour.makeAnswer(chanelID, messageFromUser));
                    }
                });
        System.out.println("Discord бот запущен");
        client.onDisconnect().block();
    }

    /**
     * отсылает ответ
     * @param chatId - Id чата
     * @param answerText текст ответа
     */
    public void sendAnswer(String chatId, String answerText){
        Snowflake channelId = Snowflake.of(chatId);
        MessageChannel channel = client.getChannelById(channelId).ofType(MessageChannel.class).block();
        if (channel != null) {
            channel.createMessage(answerText).block();
        } else {
            System.err.println("Канал не найден");
        }
    }
}
