package io.tripled.elevator;

public class ElevatorController {


    private int currentElevatorFloor = 0;
    private final int topFloor = 5;

    private final int bottomFloor = -1;

    public void handleCall(ElevatorCall call) {
        //TODO
    }

    public int getCurrentElevatorFloor() {
        //TODO
        return currentElevatorFloor;
    }

    public void moveElevatorOneFloor(Direction direction) {

        if(direction == Direction.UP && currentElevatorFloor < topFloor){
            currentElevatorFloor++;
        } else if(direction == Direction.DOWN && currentElevatorFloor > bottomFloor){
            currentElevatorFloor--;
        }

    }
}
