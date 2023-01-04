package dev.haermeus.haermeusbot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {
        // TODO добавить создание телеграм бота
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new HaermeusBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    }
}
