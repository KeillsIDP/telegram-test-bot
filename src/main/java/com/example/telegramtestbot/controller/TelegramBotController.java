package com.example.telegramtestbot.controller;

import com.example.telegramtestbot.handler.TelegramBotCommandsHandler;
import com.example.telegramtestbot.handler.TelegramBotStatesHandler;
import com.example.telegramtestbot.service.BotUtilServices;
import com.example.telegramtestbot.service.ChatStateService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j
@Controller
public class TelegramBotController extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    private final ChatStateService chatStateService;
    private final BotUtilServices botUtilServices;
    private final TelegramBotCommandsHandler commandsHandler;
    private final TelegramBotStatesHandler statesHandler;

    public TelegramBotController(@Value("${bot.token}")String botToken, ChatStateService chatStateService, BotUtilServices botUtilServices){
        super(botToken);
        this.chatStateService = chatStateService;
        this.botUtilServices = botUtilServices;
        this.commandsHandler = new TelegramBotCommandsHandler(this);
        this.statesHandler = new TelegramBotStatesHandler(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        message = message==null?"":message.trim();
        Long chatId = update.getMessage().getChatId();

        if (message.startsWith("/")) {
            String commandIdentifier = message.split(" ")[0].toLowerCase();
            commandsHandler.retrieveCommand(commandIdentifier).execute(update);
        } else {
            statesHandler.retrieveAction(chatStateService.getState(chatId)).execute(update);
        }

    }

    public void sendMessage(Long chatId,String text){
        String chatIdStr = chatId.toString();
        SendMessage sendMessage = new SendMessage(chatIdStr,text);
        try{
            log.debug("Sending message: " + text);
            execute(sendMessage);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    public ChatStateService getChatStateService() { return this.chatStateService; }

    public BotUtilServices getBotUtilServices() { return botUtilServices; }
}
