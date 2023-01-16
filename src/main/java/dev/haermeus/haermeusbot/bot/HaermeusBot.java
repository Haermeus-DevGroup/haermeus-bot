package dev.haermeus.haermeusbot.bot;

import dev.haermeus.haermeusbot.api.ApiCenter;
import dev.haermeus.haermeusbot.api.ResourcesApi;
import dev.haermeus.haermeusbot.api.SectionsApi;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j(topic = "[bot]")
public class HaermeusBot extends TelegramLongPollingBot {

    private final ResourcesApi resourcesApi = ApiCenter.getResourcesApi();
    private final SectionsApi sectionsApi = ApiCenter.getSectionsApi();

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Got update {}", update);
        if (update.hasMessage()) {
            processMessage(update);
        }
        else if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
        }
        else {
            log.error("Unknown update type {}", update);
        }
    }

    @Override
    public String getBotUsername() {
        return "haermeusbot";
    }

    @Override
    public String getBotToken() {
        return null;
    }

    private void processMessage(Update update) {

    }

    private void processCallbackQuery(Update update) {

    }

}
