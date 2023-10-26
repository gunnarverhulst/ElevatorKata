package io.tripled.elevator;

public class ElevatorController {


    private int currentElevatorFloor = 0;
    private final int topFloor = 5;

    private final int bottomFloor = -1;

    public void handleCall(ElevatorCall call) {
        //TODO
        moveElevatorToTarget(call.callOrigin());
        moveElevatorToTarget(call.callDestination());
    }

    private void moveElevatorToTarget(int targetFloor) {
        while(currentElevatorFloor != targetFloor){
            if(currentElevatorFloor < targetFloor){
                moveElevatorOneFloor(ElevatorAction.UP);
            } else if(currentElevatorFloor > targetFloor){
                moveElevatorOneFloor(ElevatorAction.DOWN);
            }
        }
        System.out.println(ElevatorStateParser.ELEVATOR_STATE_PARSER.printElevatorState(ElevatorAction.OPEN_DOORS, currentElevatorFloor));
    }

    public int getCurrentElevatorFloor() {
        //TODO
        return currentElevatorFloor;
    }

    public void moveElevatorOneFloor(ElevatorAction elevatorAction) {
        if(elevatorAction == ElevatorAction.UP && currentElevatorFloor < topFloor){
            currentElevatorFloor++;
        } else if(elevatorAction == ElevatorAction.DOWN && currentElevatorFloor > bottomFloor){
            currentElevatorFloor--;
        } else {
            return;
        }
        System.out.println(ElevatorStateParser.ELEVATOR_STATE_PARSER.printElevatorState(elevatorAction, currentElevatorFloor));
    }
}
