package io.tripled.elevator;

public class ElevatorController {


    private int currentElevatorFloor = 0;

    public void handleCall(ElevatorCall call) {
        //TODO
    }

    public int getCurrentElevatorFloor() {
        //TODO
        return currentElevatorFloor;
    }

    public void moveElevatorOneFloorUp() {
        currentElevatorFloor++;
    }
}
