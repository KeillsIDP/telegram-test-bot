package com.example.telegramtestbot.command;

import com.example.telegramtestbot.controller.TelegramBotController;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command{
    private final TelegramBotController telegramBotController;

    private final static String STOP_MESSAGE = "Прощай!";
    public StopCommand(TelegramBotController telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(Update update) {
        telegramBotController.sendMessage(update.getMessage().getChatId(), STOP_MESSAGE);
    }
}
