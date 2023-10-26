package io.tripled.elevator;

public enum ApplicationCommand {
    QUIT, POSITION, MOVE, UNKNOWN;

    public static ApplicationCommand parse(String input) {
        if (isQuitCommand(input)) return QUIT;
        if (isPositionCommand(input)) return POSITION;
        if (isMoveCommand(input)) return MOVE;
        return UNKNOWN;
    }

    private static boolean isMoveCommand(String input) {
        return input.contains("-");
    }

    private static boolean isQuitCommand(String input) {
        return "q".equalsIgnoreCase(input);
    }

    private static boolean isPositionCommand(String input) {
        return input.equalsIgnoreCase("p");
    }

}

