package com.example.telegramtestbot.command;

import com.example.telegramtestbot.controller.TelegramBotController;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command{
    private final TelegramBotController telegramBotController;
    private final static String UNKNOWN_MESSAGE = "Не знаю такой команды.";

    public UnknownCommand(TelegramBotController telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(Update update) {
        telegramBotController.sendMessage(update.getMessage().getChatId(), UNKNOWN_MESSAGE);
    }
}
