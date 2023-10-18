package com.example.telegramtestbot.action;

import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Action {

    protected ChatStateService chatStateService;
    protected TelegramBotController telegramBotController;

    public Action(ChatStateService chatStateService, TelegramBotController telegramBotController){
        this.chatStateService = chatStateService;
        this.telegramBotController = telegramBotController;
    }

    public void execute(Update update){

    }
}
