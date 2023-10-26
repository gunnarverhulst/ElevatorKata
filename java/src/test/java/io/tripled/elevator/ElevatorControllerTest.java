package io.tripled.elevator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ElevatorControllerTest {

    @Test
    public void displayInitialFloorIsGround(){
        ElevatorController elevatorController = new ElevatorController();
        assertEquals(0, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFloorAfterMoveElevatorOneFloorUp(){
        ElevatorController elevatorController = new ElevatorController();
        elevatorController.moveElevatorOneFloor(Direction.UP);
        assertEquals(1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void displayFloorAfterMoveElevatorOneFloorDown(){
        ElevatorController elevatorController = new ElevatorController();
        elevatorController.moveElevatorOneFloor(Direction.DOWN);
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void checkFloorBoundaryUp5(){
        ElevatorController elevatorController = new ElevatorController();
        for(int i = 0; i < 6; i++){
            elevatorController.moveElevatorOneFloor(Direction.UP);
        }
        assertEquals(5, elevatorController.getCurrentElevatorFloor());
    }

    @Test
    public void checkFloorBoundaryDownBasement(){
        ElevatorController elevatorController = new ElevatorController();
        for(int i = 0; i > -2; i--){
            elevatorController.moveElevatorOneFloor(Direction.DOWN);
        }
        assertEquals(-1, elevatorController.getCurrentElevatorFloor());
    }
}
