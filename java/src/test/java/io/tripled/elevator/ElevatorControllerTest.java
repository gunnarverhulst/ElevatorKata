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
        List<ElevatorCall> callList = new ArrayList<>();
        callList.add(new ElevatorCall(3,-1));
        elevatorController.handleCommandWithMultipleCallsPart3(callList);
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandle2Calls1To2And3To5(){
        List<ElevatorCall> calls = new ArrayList<>();
        calls.add(new ElevatorCall(1,2));
        calls.add(new ElevatorCall(3,5));
        elevatorController.handleCommandWithMultipleCallsPart3(calls);

        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCalls1To4And2To3(){
        List<ElevatorCall> calls = new ArrayList<>();
        calls.add(new ElevatorCall(1,4));
        calls.add(new ElevatorCall(2,3));
        elevatorController.handleCommandWithMultipleCallsPart3(calls);

        assertEquals(4, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCalls1To3And2To4(){
        List<ElevatorCall> calls = new ArrayList<>();
        calls.add(new ElevatorCall(1,3));
        calls.add(new ElevatorCall(2,4));
        elevatorController.handleCommandWithMultipleCallsPart3(calls);

        assertEquals(4, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCalls3To0And0Tob(){
        List<ElevatorCall> calls = new ArrayList<>();
        calls.add(new ElevatorCall(3,0));
        calls.add(new ElevatorCall(0,-1));
        elevatorController.handleCommandWithMultipleCallsPart3(calls);

        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCallsWhileMovingToOrigin(){
        List<ElevatorCall> calls = new ArrayList<>();
        calls.add(new ElevatorCall(3,5));
        calls.add(new ElevatorCall(1,2));
        elevatorController.handleCommandWithMultipleCallsPart3(calls);

        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCallsWhileMovingToOriginPart3Complex(){
        List<ElevatorCall> calls = new ArrayList<>();
        calls.add(new ElevatorCall(1,3));
        calls.add(new ElevatorCall(2,4));
        calls.add(new ElevatorCall(0,2));
        calls.add(new ElevatorCall(1,2));
        calls.add(new ElevatorCall(3,5));
        calls.add(new ElevatorCall(3,-1));
        calls.add(new ElevatorCall(5,0));
        calls.add(new ElevatorCall(4,2));
        calls.add(new ElevatorCall(2,1));
        calls.add(new ElevatorCall(-1,1));
        elevatorController.handleCommandWithMultipleCallsPart3(calls);

        assertEquals(0, elevatorController.getCurrentElevatorFloor());
    }

}
