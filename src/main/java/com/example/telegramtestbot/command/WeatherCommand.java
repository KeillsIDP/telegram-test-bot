package com.example.telegramtestbot.command;

import com.example.telegramtestbot.action.BotState;
import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WeatherCommand implements Command{

    private final ChatStateService chatStateService;
    private final TelegramBotController telegramBotController;

    private final static String WEATHER_GET_LOCATION_REPLY = "Мне нужно узнать в каком месте требуются данные о погоде!\n"
            + "Отправь геопозицию!";

    public WeatherCommand(ChatStateService chatStateService, TelegramBotController telegramBotController) {
        this.chatStateService = chatStateService;
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        chatStateService.setState(chatId, BotState.AWAITS_WEATHER_INFO);
        telegramBotController.sendMessage(chatId, WEATHER_GET_LOCATION_REPLY);
    }
}
