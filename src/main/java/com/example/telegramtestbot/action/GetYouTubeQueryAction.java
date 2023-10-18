package com.example.telegramtestbot.action;

import com.example.telegramtestbot.controller.TelegramBotController;
import com.example.telegramtestbot.service.BotUtilServices;
import com.example.telegramtestbot.service.ChatStateService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GetYouTubeQueryAction extends Action {
    private final BotUtilServices search;
    public GetYouTubeQueryAction(BotUtilServices search, ChatStateService chatStateService, TelegramBotController telegramBotController){
        super(chatStateService,telegramBotController);
        this.search = search;
    }

    @Override
    public void execute(Update update) {
        String query = update.getMessage().getText().trim();
        String links = search.getVideoLink(query,3);

        Long chatId = update.getMessage().getChatId();
        telegramBotController.sendMessage(chatId,links);
        chatStateService.setState(chatId,BotState.IDLE);
    }
}
