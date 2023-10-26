package io.tripled.elevator;

import java.util.List;
import java.util.Optional;

public class ElevatorController {

    private int currentElevatorFloor = 0;
    private final int topFloorBoundary = 5;

    private final int bottomFloorBoundary = -1;

    public void handleCall(ElevatorCall call) {
        //TODO
        moveElevatorToTarget(call.callOrigin());
        moveElevatorToTarget(call.callDestination());
    }

    public void handleCommandWithMultipleCalls(List<ElevatorCall> parsedInputCalls) {
        List<ElevatorCall> elevatorCalls = parsedInputCalls;

        while(!elevatorCalls.isEmpty()){
            ElevatorCall firstElevatorCall = elevatorCalls.get(0);
            elevatorCalls.remove(0);
            handleCall(firstElevatorCall);
        }
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
        return (currentElevatorFloor < topFloorBoundary && currentElevatorFloor > bottomFloorBoundary);
    }

    private void printElevatorState(ElevatorAction elevatorAction) {
        System.out.println(ElevatorStateParser.ELEVATOR_STATE_PARSER.printElevatorState(elevatorAction, currentElevatorFloor));
    }


}
