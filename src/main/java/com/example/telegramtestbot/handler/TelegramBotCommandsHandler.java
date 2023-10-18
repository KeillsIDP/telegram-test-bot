package com.example.telegramtestbot.handler;

import com.example.telegramtestbot.command.*;
import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.ChatStateService;

import java.util.HashMap;
import java.util.Map;

import static com.example.telegramtestbot.command.CommandName.*;

public class TelegramBotCommandsHandler {
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public TelegramBotCommandsHandler(TelegramBotController telegramBotController) {

        commandMap = new HashMap<String, Command>();
        commandMap.put(START.getCommandName(), new StartCommand(telegramBotController.getChatStateService(), telegramBotController));
        commandMap.put(STOP.getCommandName(), new StopCommand(telegramBotController));
        commandMap.put(HELP.getCommandName(), new HelpCommand(telegramBotController));
        commandMap.put(YT.getCommandName(), new YouTubeCommand(telegramBotController.getChatStateService(),telegramBotController));
        commandMap.put(WEATHER.getCommandName(), new WeatherCommand(telegramBotController.getChatStateService(),telegramBotController));

        unknownCommand = new UnknownCommand(telegramBotController);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
