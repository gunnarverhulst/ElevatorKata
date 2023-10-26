package io.tripled.elevator;

import java.util.Optional;

import static io.tripled.elevator.FloorParser.FLOOR_PARSER;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum CallParser {
    CALL_PARSER;


    public Optional<ElevatorCall> parse(String input) {
        final String[] splitTokens = input.split("-");
        if (hasTwoPositions(splitTokens)) {
            return of(createCall(splitTokens));
        } else
            return empty();
    }

    private static ElevatorCall createCall(String[] splitTokens) {
        final String origin = splitTokens[0];
        final String destination = splitTokens[1];
        return new ElevatorCall(FLOOR_PARSER.toNumber(origin), FLOOR_PARSER.toNumber(destination));
    }



    private boolean hasTwoPositions(String[] splitTokens) {
        return splitTokens.length == 2;
    }

}
