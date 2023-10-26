package io.tripled.elevator;


import java.util.Scanner;

import static io.tripled.elevator.CallParser.CALL_PARSER;

public class ElevatorApplication {

    public static final String QUIT_MESSAGE = "*********END*****************";
    private final ElevatorController controller = new ElevatorController();

    public static void main(String[] args) {
        System.out.println("**************************");
        System.out.println("**    Elevator Kata     **");
        System.out.println("**************************");
        readInput();
    }

    public static void readInput() {
        final ElevatorApplication application = new ElevatorApplication();
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                final String input = scanner.nextLine();
                final ApplicationCommand applicationCommand = ApplicationCommand.parse(input);
                final String outputMessage = application.handleCommand(applicationCommand, input);
                System.out.println(outputMessage);
                if (QUIT_MESSAGE.equalsIgnoreCase(outputMessage)) break;
            }
            while (true);
        }
    }

    private String handleCommand(ApplicationCommand applicationCommand, String input) {
        return switch (applicationCommand) {
            case QUIT -> QUIT_MESSAGE;
            case POSITION -> elevatorPositionMessage();
            case MOVE -> handleMoveCommand(input);
            case UNKNOWN -> this.apiMessage();
        };
    }

    private String handleMoveCommand(String input) {
        return CALL_PARSER
                .parse(input)
                .map(this::handleCall)
                .orElseGet(this::apiMessage);
    }


    private String elevatorPositionMessage() {
        final int currentElevatorFloor = controller.getCurrentElevatorFloor();
        return "The elevator is currently at " + FloorParser.FLOOR_PARSER.toText(currentElevatorFloor);
    }

    private String handleCall(ElevatorCall elevatorCall) {
        final String message = "A call was received from the floor [" + elevatorCall.callOrigin() + "] with destination [" + elevatorCall.callDestination() + "]";
        controller.handleCall(elevatorCall);
        return message;
    }

    private String apiMessage() {
        return "Press P to get the current position of the elevator" + System.lineSeparator()
               + "Press Q to quit the application" + System.lineSeparator()
               + "To give the elevator a Call the format must be " + System.lineSeparator()
               + "  3-B : A call from the third floor to go to the basement " + System.lineSeparator()
               + "  G-5 : A call from the ground floor to go to the fifth floor " + System.lineSeparator()
               + "  4-2 : A call from the fourth floor to go to the second floor";
    }
}
