package io.tripled.elevator;

public enum FloorParser {
    FLOOR_PARSER;

    public int toNumber(String value) {
        if (value.equalsIgnoreCase("b")) return -1;
        if (value.equalsIgnoreCase("g")) return 0;
        return Integer.parseInt(value);
    }

    public String toText(int currentElevatorFloor) {
        return switch (currentElevatorFloor) {
            case -1 -> "basement";
            case 0 -> "ground floor";
            default -> currentElevatorFloor + " floor";
        };
    }
}
