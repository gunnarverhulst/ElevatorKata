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
}
