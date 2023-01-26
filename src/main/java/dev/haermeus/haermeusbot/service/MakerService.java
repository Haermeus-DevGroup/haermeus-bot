package dev.haermeus.haermeusbot.service;

import dev.haermeus.haermeusbot.dto.resource.PlainResourceDTO;
import dev.haermeus.haermeusbot.dto.section.PlainSectionDTO;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;
import static dev.haermeus.haermeusbot.service.HeadingsFormatterService.*;

public class MakerService {

    private MakerService() {
    }

    public static InlineKeyboardMarkup makeSectionInlineKeyboardMarkup(PlainSectionDTO section, List<PlainSectionDTO> childSections, List<PlainResourceDTO> childResources) {
        if (!childResources.isEmpty()) {
            return InlineKeyboardMarkup.builder()
                    .keyboard(makeInlineKeyboardFromResources(section, childResources))
                    .build();
        } else if (!childSections.isEmpty()) {
            return InlineKeyboardMarkup.builder()
                    .keyboard(makeInlineKeyboardFromSections(section, childSections))
                    .build();
        } else {
            return makeBackButtonInlineKeyboardMarkup("section " + section.getParentId().toString());
        }
    }

    public static List<List<InlineKeyboardButton>> makeInlineKeyboardFromSections(PlainSectionDTO section, List<PlainSectionDTO> childSections) {
        var keyboard = childSections.stream()
                .map(sectionDTO -> List.of(makeInlineKeyboardButton(sectionDTO)))
                .collect(Collectors.toList());
        keyboard.add(List.of(makeBackButton("section " + section.getParentId().toString())));
        return keyboard;
    }

    public static InlineKeyboardButton makeInlineKeyboardButton(PlainSectionDTO section) {
        return InlineKeyboardButton.builder()
                .text(formatSectionTitle(section.getTitle()))
                .callbackData("section " + section.getId())
                .build();
    }

    public static List<List<InlineKeyboardButton>> makeInlineKeyboardFromResources(PlainSectionDTO section, List<PlainResourceDTO> childResources) {
        var keyboard = childResources.stream()
                .map(resourceDTO -> List.of(makeInlineKeyboardButton(resourceDTO)))
                .collect(Collectors.toList());
        keyboard.add(List.of(makeBackButton("section " + section.getParentId().toString())));
        return keyboard;
    }

    public static InlineKeyboardButton makeInlineKeyboardButton(PlainResourceDTO resource) {
        return InlineKeyboardButton.builder()
                .text(formatResourceTitle(resource.getTitle()))
                .callbackData("resource " + resource.getId())
                .build();
    }

    public static InlineKeyboardMarkup makeBackButtonInlineKeyboardMarkup(String callbackData) {
        return InlineKeyboardMarkup.builder()
                .keyboardRow(
                        List.of(makeBackButton(callbackData))
                )
                .build();
    }

    public static InlineKeyboardButton makeBackButton(String callbackData) {
        return InlineKeyboardButton.builder()
                .text(formatBackTitle("Вернуться"))
                .callbackData(callbackData)
                .build();
    }

    public static InlineKeyboardMarkup makeRootsInlineKeyboardMarkup(List<PlainSectionDTO> plainSections) {
        var keyboardBuilder = InlineKeyboardMarkup.builder();
        plainSections.forEach(
                section -> keyboardBuilder.keyboardRow(List.of(
                        InlineKeyboardButton.builder()
                                .text(formatSectionTitle(section.getTitle()))
                                .callbackData("section " + section.getId())
                                .build()
                ))
        );
        return keyboardBuilder.build();
    }
}
