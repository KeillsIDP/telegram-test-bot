package com.example.telegramtestbot.command;

import com.example.telegramtestbot.action.BotState;
import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class YouTubeCommand implements Command{

    private final ChatStateService chatStateService;
    private final TelegramBotController telegramBotController;

    private final static String YOUTUBE_GET_QUERY_MESSAGE = "Окей, скажи как называется видео!";

    public YouTubeCommand(ChatStateService chatStateService, TelegramBotController telegramBotController) {
        this.chatStateService = chatStateService;
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        chatStateService.setState(chatId, BotState.AWAITS_YT_QUERY);
        telegramBotController.sendMessage(chatId, YOUTUBE_GET_QUERY_MESSAGE);
    }
}
