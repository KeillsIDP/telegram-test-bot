package com.example.telegramtestbot.handler;

import com.example.telegramtestbot.action.*;
import com.example.telegramtestbot.controller.TelegramBotController;

import java.util.HashMap;
import java.util.Map;

import static com.example.telegramtestbot.action.BotState.*;

public class TelegramBotStatesHandler {
    private final Map<BotState, Action> actionMap;

    public TelegramBotStatesHandler(TelegramBotController telegramBotController) {

        actionMap = new HashMap<BotState, Action>();
        actionMap.put(IDLE,  new NoAction(telegramBotController.getChatStateService(),telegramBotController));
        actionMap.put(AWAITS_YT_QUERY, new GetYouTubeQueryAction(telegramBotController.getBotUtilServices(),
                                                                 telegramBotController.getChatStateService(),
                                                                 telegramBotController));
        actionMap.put(AWAITS_WEATHER_INFO, new GetLocationAction(telegramBotController.getBotUtilServices(),
                                                                 telegramBotController.getChatStateService(),
                                                                 telegramBotController));
    }

    public Action retrieveAction(BotState state) {
        return actionMap.get(state);
    }
}
