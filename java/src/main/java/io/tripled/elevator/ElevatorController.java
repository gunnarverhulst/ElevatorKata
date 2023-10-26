package io.tripled.elevator;

public class ElevatorController {

    private int currentElevatorFloor = 0;
    private final int topFloorBoundary = 5;

    private final int bottomFloorBoundary = -1;

    public void handleCall(ElevatorCall call) {
        //TODO
        moveElevatorToTarget(call.callOrigin());
        moveElevatorToTarget(call.callDestination());
    }

    private void moveElevatorToTarget(int targetFloor) {
        while(currentElevatorFloor != targetFloor){
            moveElevatorOneFloor(getElevatorAction(targetFloor));
        }
        printElevatorState(ElevatorAction.OPEN_DOORS);
    }

    private ElevatorAction getElevatorAction(int targetFloor) {
        if(currentElevatorFloor < targetFloor){
            return ElevatorAction.UP;
        } else if(currentElevatorFloor > targetFloor){
            return ElevatorAction.DOWN;
        } else {
            return ElevatorAction.OPEN_DOORS;
        }
    }


    public int getCurrentElevatorFloor() {
        //TODO
        return currentElevatorFloor;
    }

    public void moveElevatorOneFloor(ElevatorAction elevatorAction) {
        if(elevatorAction == ElevatorAction.UP && isElevatorWithinBoundaryFloors()){
            currentElevatorFloor++;
        } else if(elevatorAction == ElevatorAction.DOWN && isElevatorWithinBoundaryFloors()){
            currentElevatorFloor--;
        } else {
            return;
        }
        printElevatorState(elevatorAction);
    }

    public boolean isElevatorWithinBoundaryFloors(){
        return (currentElevatorFloor < topFloorBoundary && currentElevatorFloor > bottomFloorBoundary) ? true : false;
    }



    private void printElevatorState(ElevatorAction elevatorAction) {
        System.out.println(ElevatorStateParser.ELEVATOR_STATE_PARSER.printElevatorState(elevatorAction, currentElevatorFloor));
    }
}
