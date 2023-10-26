package io.tripled.elevator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ElevatorControllerTest {

    private ElevatorController elevatorController = new ElevatorController();

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
}
