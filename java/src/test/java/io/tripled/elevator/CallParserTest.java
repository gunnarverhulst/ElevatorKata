package io.tripled.elevator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallParserTest {
    @Test
    public void handleInputWith2DistinctCalls1To2And3To5(){
        Optional<List<ElevatorCall>> parsedInputCalls = CallParser.CALL_PARSER.parse("1-2 3-5");
        List<ElevatorCall> expectedCalls = new ArrayList<>();
        expectedCalls.add(new ElevatorCall(1,2));
        expectedCalls.add(new ElevatorCall(3,5));

        assertEquals(Optional.of(expectedCalls), parsedInputCalls);
    }

}
