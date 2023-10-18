package com.example.telegramtestbot.action;

import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoAction extends Action {

    public NoAction(ChatStateService chatStateService, TelegramBotController telegramBotController){
        super(chatStateService,telegramBotController);
    }

    @Override
    public void execute(Update update) {
        telegramBotController.sendMessage(update.getMessage().getChatId(),"Ну я не знаю что с этим делать.");
    }
}
