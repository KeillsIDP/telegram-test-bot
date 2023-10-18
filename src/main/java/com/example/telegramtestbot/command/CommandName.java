package com.example.telegramtestbot.command;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    YT("/yt"),
    WEATHER("/weather"),
    NO("nocommand");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}