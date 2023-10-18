package com.example.telegramtestbot.service;

import com.example.telegramtestbot.model.Chat;
import com.example.telegramtestbot.repository.ChatRepo;
import com.example.telegramtestbot.action.BotState;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class ChatStateServiceImpl implements ChatStateService {
    @Autowired
    private ChatRepo chatRepository;

    @Override
    public void registration(Long chatId) {
        if(chatRepository.findByChatId(chatId.toString()).isPresent())
            return;

        log.debug("Registred "+ chatId);
        Chat chat = Chat.builder().chatId(chatId.toString()).state(BotState.IDLE).build();
        chatRepository.save(chat);
    }

    @Override
    public void setState(Long chatId, BotState state) {
        Chat chat = chatRepository.findByChatId(chatId.toString()).get();
        if(chat==null)
            return;
        chat.setState(state);
        chatRepository.save(chat);
    }

    @Override
    public BotState getState(Long chatId){
        Chat chat = chatRepository.findByChatId(chatId.toString()).get();
        if(chat==null)
            return BotState.IDLE;
        return chat.getState();
    }

}
