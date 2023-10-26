package io.tripled.elevator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ElevatorControllerTest {

    private final ElevatorController elevatorController = new ElevatorController();

    @Test
    public void displayInitialFloorIsGround(){
        assertEquals(0, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFloorAfterMoveElevatorOneFloorUp(){
        elevatorController.moveElevatorOneFloor(ElevatorAction.UP);
        assertEquals(1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFloorAfterMoveElevatorOneFloorDown(){
        elevatorController.moveElevatorOneFloor(ElevatorAction.DOWN);
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void checkFloorBoundaryUp5(){
        for(int i = 0; i < 6; i++){
            elevatorController.moveElevatorOneFloor(ElevatorAction.UP);
        }
        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void checkFloorBoundaryDownBasement(){
        for(int i = 0; i > -2; i--){
            elevatorController.moveElevatorOneFloor(ElevatorAction.DOWN);
        }
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCall3Tob(){
        elevatorController.handleCall(new ElevatorCall(3,-1));
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void handleInputWith2DistinctCalls1To2And3To5(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-2 3-5");
        List<ElevatorCall> expectedCalls = new ArrayList<>();
        expectedCalls.add(new ElevatorCall(1,2));
        expectedCalls.add(new ElevatorCall(3,5));

        assertEquals(Optional.of(expectedCalls), parsedInputCalls);
    }

    @Test
    public void displayFinalFloorHandle2Calls1To2And3To5(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-2 3-5");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCalls);

        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCalls1To4And2To3(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-4 2-3");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCalls);

        assertEquals(4, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCalls1To3And2To4(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-3 2-4");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCalls);

        assertEquals(4, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCalls3To0And0Tob(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("3-0 0-b");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCalls);

        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCallsPart2ComplexExample(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-3 2-4 g-2 1-2 3-5 3-b 5-g 4-2 2-1 b-1");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCalls);

        assertEquals(1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCallsWhileMovingToOrigin(){
        ElevatorController elevatorController = new ElevatorController();
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("3-5 1-2");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCallsWithoutWaiting);

        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCallsWhileMovingToOriginPart3Complex(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-3 2-4 g-2 1-2 3-5 3-b 5-g 4-2 2-1 b-1");
        parsedInputCalls.ifPresent(elevatorController::handleCommandWithMultipleCallsWithoutWaiting);

        assertEquals(0, elevatorController.getCurrentElevatorFloor());
    }
}
