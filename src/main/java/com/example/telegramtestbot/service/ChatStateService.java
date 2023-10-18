package com.example.telegramtestbot.service;

import com.example.telegramtestbot.action.BotState;

public interface ChatStateService {

    void registration(Long chatId);

    void setState(Long chatId, BotState state);

    BotState getState(Long chatId);
}
