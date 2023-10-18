package com.example.telegramtestbot.action;

import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.BotUtilServices;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GetLocationAction extends Action {

    private final BotUtilServices utils;

    public GetLocationAction(BotUtilServices utils, ChatStateService chatStateService, TelegramBotController telegramBotController){
        super(chatStateService,telegramBotController);
        this.utils = utils;
    }

    @Override
    public void execute(Update update) {
        Location location = update.getMessage().getLocation();
        String weather;
        if(location==null)
            weather = utils.getWeather(0,0);
        else
            weather = utils.getWeather(location.getLongitude(), location.getLatitude());
        Long chatId = update.getMessage().getChatId();
        telegramBotController.sendMessage(chatId,weather);
        chatStateService.setState(chatId,BotState.IDLE);
    }
}
