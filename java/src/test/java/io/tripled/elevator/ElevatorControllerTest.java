package io.tripled.elevator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ElevatorControllerTest {

    private ElevatorController elevatorController;

    @Test
    public void displayInitialFloorIsGround(){
        elevatorController = new ElevatorController();
        assertEquals(0, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFloorAfterMoveElevatorOneFloorUp(){
        elevatorController = new ElevatorController();
        elevatorController.moveElevatorOneFloor(Direction.UP);
        assertEquals(1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFloorAfterMoveElevatorOneFloorDown(){
        elevatorController = new ElevatorController();
        elevatorController.moveElevatorOneFloor(Direction.DOWN);
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void checkFloorBoundaryUp5(){
        elevatorController = new ElevatorController();
        for(int i = 0; i < 6; i++){
            elevatorController.moveElevatorOneFloor(Direction.UP);
        }
        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void checkFloorBoundaryDownBasement(){
        elevatorController = new ElevatorController();
        for(int i = 0; i > -2; i--){
            elevatorController.moveElevatorOneFloor(Direction.DOWN);
        }
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFinalFloorHandleCall3Tob(){
        elevatorController = new ElevatorController();
        elevatorController.handleCall(new ElevatorCall(3,-1));
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }
}
