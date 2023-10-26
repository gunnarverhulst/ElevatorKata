package io.tripled.elevator;

public class ElevatorController {


    private int currentElevatorFloor = 0;
    private final int topFloor = 5;

    private final int bottomFloor = -1;

    public void handleCall(ElevatorCall call) {
        //TODO
        while(currentElevatorFloor != call.callOrigin()){
            if(currentElevatorFloor < call.callOrigin()){
                moveElevatorOneFloor(Direction.UP);
            } else if(currentElevatorFloor > call.callOrigin()){
                moveElevatorOneFloor(Direction.DOWN);
            }
        }
        System.out.println("<DING> - door open at " + FloorParser.FLOOR_PARSER.toText(currentElevatorFloor));

        while(currentElevatorFloor != call.callDestination()){
            if(currentElevatorFloor < call.callDestination()){
                moveElevatorOneFloor(Direction.UP);
            } else if(currentElevatorFloor > call.callDestination()){
                moveElevatorOneFloor(Direction.DOWN);
            }
        }
        System.out.println("<DING> - door open at " + FloorParser.FLOOR_PARSER.toText(currentElevatorFloor));

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
        System.out.println("Elevator at "+ FloorParser.FLOOR_PARSER.toText(currentElevatorFloor));
    }
}
