package dev.haermeus.haermeusbot.bot;

import dev.haermeus.haermeusbot.api.ApiCenter;
import dev.haermeus.haermeusbot.api.ResourcesApi;
import dev.haermeus.haermeusbot.api.SectionsApi;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        log.info("Start processing message {}", update.getMessage());
        try {
            var response = SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text("Корневой каталог")
                    .replyMarkup(makeRootsInlineKeyboardMarkup(sectionsApi.getRootSections()))
                    .build();
            execute(response);
        }
        catch (TelegramApiException e) {
            log.error("Cannot process message {}", update, e);
        }
    }

    private void processCallbackQuery(Update update) {
        log.info("Start processing callback query {}", update.getCallbackQuery());
        CallbackQuery callback = update.getCallbackQuery();
        if (callback.getData().startsWith("section")) {
            processSectionCallback(callback);
        }
        else if (callback.getData().startsWith("resource")) {
            processResourceCallback(callback);
        }
        else {
            log.error("Unknown callback {}", callback);
        }
    }

}
