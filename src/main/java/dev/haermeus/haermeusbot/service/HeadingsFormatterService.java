package dev.haermeus.haermeusbot.service;

public class HeadingsFormatterService {

    private static final String DIRECTORY_EMOJI = "\uD83D\uDCC2"; //📂
    private static final String OPEN_BOOK_EMOJI = "\uD83D\uDCD6"; //📖
    private static final String BACK_ARROW_EMOJI = "\uD83D\uDD19"; //🔙
    private static final String FORMATTED_TEMPLATE = "%s %s";

    private HeadingsFormatterService() {

    }

    public static String formatResourceTitle(String title) {
        return FORMATTED_TEMPLATE.formatted(OPEN_BOOK_EMOJI, title);
    }

    public static String formatSectionTitle(String title) {
        return FORMATTED_TEMPLATE.formatted(DIRECTORY_EMOJI, title);
    }

    public static String formatBackTitle(String title) {
        return FORMATTED_TEMPLATE.formatted(BACK_ARROW_EMOJI, title);
    }

}
