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

}
