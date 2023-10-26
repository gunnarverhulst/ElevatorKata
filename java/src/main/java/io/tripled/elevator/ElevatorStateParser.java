package io.tripled.elevator;

public enum ElevatorStateParser {
    ELEVATOR_STATE_PARSER;

    public String printElevatorState(ElevatorAction elevatorAction, int currentElevatorFloor){
        return switch(elevatorAction){
            case UP, DOWN -> "Elevator at "+ FloorParser.FLOOR_PARSER.toText(currentElevatorFloor);
            case OPEN_DOORS -> "<DING> - door open at "+ FloorParser.FLOOR_PARSER.toText(currentElevatorFloor);
            default -> "Invalid elevator action";
        };
    }
}
