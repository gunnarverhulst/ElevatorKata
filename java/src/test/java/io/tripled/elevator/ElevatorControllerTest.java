package io.tripled.elevator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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


}
