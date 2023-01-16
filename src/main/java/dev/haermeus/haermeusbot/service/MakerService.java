package dev.haermeus.haermeusbot.service;

import dev.haermeus.haermeusbot.dto.resource.PlainResourceDTO;
import dev.haermeus.haermeusbot.dto.section.PlainSectionDTO;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class MakerService {

    private MakerService() {
    }

    public static InlineKeyboardMarkup makeSectionInlineKeyboardMarkup(PlainSectionDTO section, List<PlainSectionDTO> childSections, List<PlainResourceDTO> childResources) {
        return null;
    }

    public static List<List<InlineKeyboardButton>> makeInlineKeyboardFromSections(PlainSectionDTO section, List<PlainSectionDTO> childSections) {
        return null;
    }

    public static InlineKeyboardButton makeInlineKeyboardButton(PlainSectionDTO section) {
        return null;
    }

    public static List<List<InlineKeyboardButton>> makeInlineKeyboardFromResources(PlainSectionDTO section, List<PlainResourceDTO> childResources) {
        return null;
    }

    public static InlineKeyboardButton makeInlineKeyboardButton(PlainResourceDTO resource) {
        return null;
    }

    public static InlineKeyboardMarkup makeBackButtonInlineKeyboardMarkup(String callbackData) {
        return null;
    }

    public static InlineKeyboardButton makeBackButton(String callbackData) {
        return InlineKeyboardButton.builder()
                .text("\uD83D\uDD19 Вернуться")
                .callbackData(callbackData)
                .build();
    }

    public static InlineKeyboardMarkup makeRootsInlineKeyboardMarkup(List<PlainSectionDTO> plainSections) {
        return null;
    }
}
