package io.tripled.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ElevatorController {

    private int currentElevatorFloor = 0;
    private final int topFloorBoundary = 5;

    private final int bottomFloorBoundary = -1;
    private List<ElevatorCall> elevatorCalls = new ArrayList<>();
    private String message = "";

    private boolean isPart3 = false;

    public void handleCall(ElevatorCall call) {
        //TODO
        moveElevatorToTarget(call.callOrigin());
        moveElevatorToTarget(call.callDestination());
    }

    public String handleCommandWithMultipleCallsPart2(List<ElevatorCall> parsedInputCalls) {
        elevatorCalls.addAll(parsedInputCalls);

        handleAllElevatorCalls();
        return message;
    }

    public String handleCommandWithMultipleCallsPart3(List<ElevatorCall> parsedInputCalls) {
        isPart3 = true;
        elevatorCalls.addAll(parsedInputCalls);

        handleAllElevatorCalls();
        return message;
    }

    private void handleAllElevatorCalls() {
        while(!elevatorCalls.isEmpty()){
            ElevatorCall firstElevatorCall = createFirstCallToBeProcessed();
            List<ElevatorCall> processableCalls = createAllProcessableCalls(firstElevatorCall);
            handleAllSelectedFloorStopsFromSelectedCalls(processableCalls);
        }
    }
    private void handleAllSelectedFloorStopsFromSelectedCalls(List<ElevatorCall> processableCalls) {
        List<Integer> floorStopsInProcessableCalls = createFloorStopsInOrder(processableCalls);
        for(int floor : floorStopsInProcessableCalls){
            moveElevatorToTarget(floor);
        }
    }
    private List<ElevatorCall> createAllProcessableCalls(ElevatorCall firstElevatorCall) {
        List<ElevatorCall> processableCalls = new ArrayList<>();
        processableCalls.add(firstElevatorCall);
        processableCalls.addAll(buildProcessableCalls(firstElevatorCall));
        return processableCalls;
    }


    private ElevatorCall createFirstCallToBeProcessed() {
        ElevatorCall firstElevatorCall = getFirstElevatorCall();
        if(currentElevatorFloor != firstElevatorCall.callOrigin() && isPart3){
            firstElevatorCall = new ElevatorCall(currentElevatorFloor, firstElevatorCall.callOrigin());
        } else {
            removeCallFromElevatorCalls(0);
        }
        return firstElevatorCall;
    }



    private List<Integer> createFloorStopsInOrder(List<ElevatorCall> processableCalls) {
        List<Integer> floorStops = new ArrayList<>();
        Direction direction = getDirection(processableCalls.get(0));
        buildFloorStops(processableCalls, floorStops);
        sortFloorStops(direction, floorStops);
        return floorStops;
    }

    private static void buildFloorStops(List<ElevatorCall> processableCalls, List<Integer> floorStops) {
        for(ElevatorCall call: processableCalls){
            floorStops.add(call.callOrigin());
            floorStops.add(call.callDestination());
        }
    }

    private static void sortFloorStops(Direction direction, List<Integer> floorStops) {
        if(direction == Direction.UP){
            Collections.sort(floorStops);
        } else if(direction == Direction.DOWN){
            Collections.sort(floorStops, Collections.reverseOrder());
        }
    }

    private Direction getDirection(ElevatorCall elevatorCall) {
        if(elevatorCall.callOrigin() < elevatorCall.callDestination()){
            return Direction.UP;
        } else if(elevatorCall.callOrigin() > elevatorCall.callDestination()){
            return Direction.DOWN;
        }
        return Direction.NONE;
    }

    private List<ElevatorCall> buildProcessableCalls(ElevatorCall currentlyProcessedCall) {
        List<ElevatorCall> processableCalls = new ArrayList<>();
        if(!elevatorCalls.isEmpty()){
            processableCalls = addEligibleCalls(currentlyProcessedCall);
        }
        return processableCalls;
    }

    private  List<ElevatorCall> addEligibleCalls(ElevatorCall currentlyProcessedCall) {
        List<ElevatorCall> processableCalls = new ArrayList<>();
        int i = 0;
        while(i < elevatorCalls.size()){
            ElevatorCall elevatorCall = elevatorCalls.get(i);
            if(isOnRouteCall(currentlyProcessedCall, elevatorCall)){
                processableCalls.add(elevatorCall);
                removeCallFromElevatorCalls(i);
            } else {
                i++;
            }
        }
        return processableCalls;
    }

    private boolean isOnRouteCall(ElevatorCall currentlyProcessedCall, ElevatorCall callToBeChecked){
        Direction currentlyProcessedCallDirection = getDirection(currentlyProcessedCall);
        Direction callToBeCheckedDirection = getDirection(callToBeChecked);

        if( currentlyProcessedCallDirection == callToBeCheckedDirection && currentlyProcessedCallDirection == Direction.UP){
            return callToBeChecked.callOrigin() >= currentlyProcessedCall.callOrigin() && callToBeChecked.callDestination() <= currentlyProcessedCall.callDestination();
        } else if(currentlyProcessedCallDirection == callToBeCheckedDirection && currentlyProcessedCallDirection == Direction.DOWN){
            return callToBeChecked.callOrigin() <= currentlyProcessedCall.callOrigin() && callToBeChecked.callDestination() >= currentlyProcessedCall.callDestination();
        }

        return false;

    }

    private ElevatorCall getFirstElevatorCall() {
        return elevatorCalls.get(0);
    }

    private void removeCallFromElevatorCalls(int callIndex) {
        elevatorCalls.remove(callIndex);
    }

    private void moveElevatorToTarget(int targetFloor) {
        if(currentElevatorFloor != targetFloor){
            while(currentElevatorFloor != targetFloor){
                moveElevatorOneFloor(getElevatorAction(targetFloor));
            }
            printElevatorState(ElevatorAction.OPEN_DOORS);
        }

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
        if(elevatorAction == ElevatorAction.UP && currentElevatorFloor < topFloorBoundary ){
            currentElevatorFloor++;
        } else if(elevatorAction == ElevatorAction.DOWN && currentElevatorFloor > bottomFloorBoundary){
            currentElevatorFloor--;
        } else {
            return ;
        }
        printElevatorState(elevatorAction);
    }

    private void printElevatorState(ElevatorAction elevatorAction) {
        message += ElevatorStateParser.ELEVATOR_STATE_PARSER.printElevatorState(elevatorAction, currentElevatorFloor) + "\n";
    }


}
