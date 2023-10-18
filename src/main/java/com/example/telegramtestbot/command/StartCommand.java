package com.example.telegramtestbot.command;

import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {


    private final ChatStateService chatStateService;
    private final TelegramBotController telegramBotController;

    public final static String START_MESSAGE = "Привет, я простенький Telegram Bot. Я могу найти для тебя видео или посмотреть погоду.";

    public StartCommand(ChatStateService chatStateService, TelegramBotController telegramBotController) {
        this.chatStateService = chatStateService;
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(Update update) {
        chatStateService.registration(update.getMessage().getChatId());
        telegramBotController.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}