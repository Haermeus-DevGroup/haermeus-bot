package dev.haermeus.haermeusbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HaermeusBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // TODO
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "haermeusbot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return null;
    }
}
