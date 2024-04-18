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
    private final ReplyBuilder replyBuilder;

    private GatewayDiscordClient client;

    public DiscordBot(String token, ReplyBuilder replyBuilder) {
        this.token = token;
        this.replyBuilder = replyBuilder;
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

                        handle(chanelID, messageFromUser);
                    }
                });
        System.out.println("Discord бот запущен");
        client.onDisconnect().block();
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
        Snowflake channelId = Snowflake.of(chatId);
        MessageChannel channel = client.getChannelById(channelId).ofType(MessageChannel.class).block();
        if (channel != null) {
            channel.createMessage(reply).block();
        } else {
            System.err.println("Канал не найден");
        }
    }
}
