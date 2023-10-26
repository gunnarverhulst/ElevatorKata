package io.tripled.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.tripled.elevator.FloorParser.FLOOR_PARSER;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum CallParser {
    CALL_PARSER;



    public Optional<List<ElevatorCall>> parse(String input) {
        List<ElevatorCall> elevatorCalls = new ArrayList<>();
        final String[] splitCalls = input.split(" ");

        for (String splitCall : splitCalls) {
            if (parseSingleCall(splitCall).isPresent()){
                elevatorCalls.add(parseSingleCall(splitCall).get());
            }
        }

        if(elevatorCalls.isEmpty()){
            return empty();
        } else {
            return Optional.of(elevatorCalls);
        }
    }

    public Optional<ElevatorCall> parseSingleCall(String input) {
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
