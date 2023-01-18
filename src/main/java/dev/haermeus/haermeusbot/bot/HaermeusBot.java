package dev.haermeus.haermeusbot.bot;

import dev.haermeus.haermeusbot.api.ApiCenter;
import dev.haermeus.haermeusbot.api.ResourcesApi;
import dev.haermeus.haermeusbot.api.SectionsApi;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static dev.haermeus.haermeusbot.service.MakerService.*;

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

    private void processSectionCallback(CallbackQuery callback) {
        var sectionId = Long.parseLong(callback.getData().split(" ")[1]);
        EditMessageText response;
        if (sectionId == -1) {
            response = EditMessageText.builder()
                    .chatId(callback.getMessage().getChatId())
                    .messageId(callback.getMessage().getMessageId())
                    .inlineMessageId(callback.getInlineMessageId())
                    .text("Корневой каталог")
                    .replyMarkup(makeRootsInlineKeyboardMarkup(sectionsApi.getRootSections()))
                    .build();
        }
        else {
            var section = sectionsApi.getPlainSection(sectionId);
            var childSections = sectionsApi.getChildrenSections(sectionId);
            var childResources = sectionsApi.getChildrenResources(sectionId);
            response = EditMessageText.builder()
                    .chatId(callback.getMessage().getChatId())
                    .messageId(callback.getMessage().getMessageId())
                    .inlineMessageId(callback.getInlineMessageId())
                    .text(section.getTitle())
                    .replyMarkup(makeSectionInlineKeyboardMarkup(section, childSections, childResources))
                    .build();
        }
        try {
            execute(response);
            execute(new AnswerCallbackQuery(callback.getId()));
        } catch (TelegramApiException e) {
            log.error("Cannot process section callback {}", callback, e);
        }
    }

    private void processResourceCallback(CallbackQuery callback) {
        var resourceId = Long.parseLong(callback.getData().split(" ")[1]);
        var resource = resourcesApi.getFullResource(resourceId);
        var response = EditMessageText.builder()
                .chatId(callback.getMessage().getChatId())
                .messageId(callback.getMessage().getMessageId())
                .inlineMessageId(callback.getInlineMessageId())
                .text(resource.getContent())
                .replyMarkup(makeBackButtonInlineKeyboardMarkup("section " + resource.getParentId().toString()))
                .build();
        try {
            execute(response);
            execute(new AnswerCallbackQuery(callback.getId()));
        } catch (TelegramApiException e) {
            log.error("Cannot process resource callback {}", callback, e);
        }
    }

}
