package com.example.telegramtestbot.command;

import com.example.telegramtestbot.controller.TelegramBotController;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.telegramtestbot.command.CommandName.*;


public class HelpCommand implements Command{
    private final TelegramBotController telegramBotController;

    public static final String HELP_MESSAGE = String.format("✨Доступные команды✨\n\n"

                    + "Начать\\закончить работу с ботом\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n\n"
                    + "%s - получить помощь в работе со мной\n"
                    + "%s - видео с YouTube по запросу \n"
                    + "%s - погода по вашим координатам \n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(), YT.getCommandName(), WEATHER.getCommandName());

    public HelpCommand(TelegramBotController telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(Update update) {
        telegramBotController.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}
