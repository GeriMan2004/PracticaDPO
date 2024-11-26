package src.Presentation;

public enum MainMenuCases {
    LIST_CHARACTER(1),
    MANAGE_TEAM(2),
    LIST_ITEMS(3),
    COMBAT_SIMULATOR(4),
    EXIT_MAIN_MENU(5);

    private final int value;

    private MainMenuCases(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MainMenuCases fromValue(int value) {
        for (MainMenuCases option : values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return null;
    }
}
