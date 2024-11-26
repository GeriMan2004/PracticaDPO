package src.Presentation;

public enum TeamMenuCases {
    CREATE_TEAM(1),
    LIST_TEAMS(2),
    DELETE_TEAM(3),
    EXIT_TEAMS(4);

    private final int value;

    private TeamMenuCases(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TeamMenuCases fromValue(int value) {
        for (TeamMenuCases option : values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return null;
    }
}
