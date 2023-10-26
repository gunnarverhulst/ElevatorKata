# Elevator Kata

### Description

We will implement a elevator controller. Where an elevator responds to calls containing a source floor and a destination. Whereupon the elevator 
picks up and delivers the passengers to requested floors. 

The goal of the kata is to complete the logic inside the ElevatorController. The rest of the code present is a simple CLI to illustrate how the code should work and to have a convenient CLI to use the logic, run other simulations. Feel free to modify the internals of the CLI in any way you see fit, change the api etc...

Inspired by [Lift Kata](https://kata-log.rocks/lift-kata) and [Agile Technical Practices Distilled](https://www.amazon.com/Agile-Technical-Practices-Distilled-principles-ebook)

### Getting started

While the main logic of the kata needs to be implemented, there is a basic CLI in place to use the "application" from the command line. So if you checked out the code, you should have a running application.
Before you start the exercise make sure that the launch command works. The launch command is just a small wrapper around maven that compiles the code, runs the tests and starts the application.

This is what you should see on windows

```batch
>elevator.bat
**************************
**    Elevator Kata     **
**************************
```

This is what you should see on mac/linux

```batch
>./elevator
**************************
**    Elevator Kata     **
**************************
```

### The kata
#### Part I

Implement a lift controller for a building that has 5 total floors including basement and ground.
The elevator can be called at any floor only when it is not in use via one call button.

```gherkin
Scenario: Part One Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor3 to go to basement
    And there is a call from ground to go to basement
    And there is a call from floor2 to go to basement
    And there is a call from floor1 to go to floor 3
    Then the doors should open at floor3, basement, ground, basement, floor2, basement, floor1 and floor3 in this order
```

When the elevator is to a floor, no new call can be registered. 

Running the above scenario through the CLI this might look something like this:

```text
**************************
**    Elevator Kata     **
**************************
> p
The elevator is currently at ground floor

> 3-b

Elevator at 1 floor
Elevator at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
Elevator at 1 floor
Elevator at ground floor
Elevator at basement
<DING> - door open at basement

> 0-1

Elevator at ground floor
<DING> - door open at ground floor
Elevator at 1 floor
<DING> - door open at 1 floor

> 2-b

Elevator at 1 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
Elevator at ground floor
Elevator at basement
<DING> - door open at basement

> 1-3

Elevator at ground floor
Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor


>p
The elevator is currently at 3 floor
```


#### Part II

The elevator is not fast enough so as an experiment to speed it up the idea is to allow the elevator to queue the requests and optimize the trips.

The elevator can now queue the stop requests from the floors and collect people along the way, while executing a call, but cannot change direction once started until all calls in the same direction have been fulfilled.

```gherkin
Scenario: Basic Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor 2 to go to floor 3    
    And there is a call from floor 1 to go to floor 2
    Then the doors should open at floor 2, floor 3 , floor1 and floor2 in this order
```

Even though call 1 to 2 is en route, we first start working on the first call. A larger example:

```gherkin
Scenario: Large Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor3 to go to basement
    And there is a call from ground to go to basement
    And there is a call from floor2 to go to basement
    And there is a call from floor1 to go to floor 3
    Then the doors should open at floor3, floor2, ground, basement, floor1 and floor3 in this order
    for a total of 6 stops
```
Assume all calls to the elevator happen in the sequence given but in less than the time it takes to travel one floor. So before the elevator reaches the ground floor, all the commands were received.
Running the above scenario using the CLI this would look like

```text
**************************
**    Elevator Kata     **
**************************

> 3-b 0-b 2-b 1-3

Elevator at floor 1
Elevator at floor 2
Elevator at floor 3
<DING> - door open at 3 floor
Elevator at floor 2
<DING> - door open at 2 floor
Elevator at floor 1
Elevator at floor 0
<DING> - door open at ground floor
Elevator at floor -1
<DING> - door open at basement
Elevator at floor 0
Elevator at floor 1
<DING> - door open at 1 floor
Elevator at floor 2
Elevator at floor 3
<DING> - door open at 3 floor
```

An even more complex scenario, following the same logic, is the following

```gherkin
Scenario: Complex Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor 1 to go to floor 3
    And there is a call from floor 2 to go to floor 4
    And there is a call from ground to go to floor 2
    And there is a call from floor 1 to go to floor 2
    And there is a call from floor 3 to go to floor 5
    And there is a call from floor 3 to go to basement
    And there is a call from floor 5 to go to ground
    And there is a call from floor 4 to go to floor 2
    And there is a call from floor 2 to go to floor 1
    And there is a call from basement to go to floor 1

    Then the doors should open at 
    floor 1, floor 2, floor 3, floor 2, floor 4, ground, floor 2, floor 3,
     floor 5, floor 3, floor 2, floor 1, basement, floor 5, floor 4, floor 2, ground, basement and floor 1 in this order
    for a total of 19 stops
```

Given the complex scenario, the elevator starts executing the first call: pickup on floor 1 to deliver on floor 3. So the elevator starts going up.
Upon arriving at floor 1, it picks up the passenger for the first call but also the passenger for the call floor 1 to floor 2.
The elevator then stops on floor two to complete the call pickup floor 1 to deliver on floor 2. 
Finally arriving at floor 3 it completes the first call 1 to 3.

It has completed the first call in its queue, along the ay also completing the fourth call. It now starts on the second call: Pickup at floor 2 and delivery at flour 4. So it starts moving down.
Arriving at floor 2 it picks up the passenger and starts executing the call 2 to 4, going up.

Running the complex scenario using the CLI would look like

```text
**************************
**    Elevator Kata     **
**************************
> 1-3 2-4 g-2 1-2 3-5 3-b 5-g 4-2 2-1 b-1

Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 3 floor
Elevator at 4 floor
<DING> - door open at 4 floor
Elevator at 3 floor
Elevator at 2 floor
Elevator at 1 floor
Elevator at ground floor
<DING> - door open at ground floor
Elevator at 1 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 4 floor
Elevator at 5 floor
<DING> - door open at 5 floor
Elevator at 4 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at ground floor
Elevator at basement
<DING> - door open at basement
Elevator at ground floor
Elevator at 1 floor
Elevator at 2 floor
Elevator at 3 floor
Elevator at 4 floor
Elevator at 5 floor
<DING> - door open at 5 floor
Elevator at 4 floor
<DING> - door open at 4 floor
Elevator at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
Elevator at ground floor
<DING> - door open at ground floor
Elevator at basement
<DING> - door open at basement
Elevator at ground floor
Elevator at 1 floor
<DING> - door open at 1 floor
```

### Part III


We would like to optimize the elevator Controller a bit further. There is no need to wait until a call is in progress to start executing a call that we can do while we are on the way. So while we are en route to pick someone up, we could start handling calls. Looking back to our previous examples. the logic now changes:

```gherkin
Scenario: Basic Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor 2 to go to floor 3    
    And there is a call from floor1 to go to floor 2
    Then the doors should open at floor 1, floor 2 and floor 3 in this order
    for a total of 3 stops
```

Before we even started working on the first call, we have already completed the second call. It was on our way.

The larger example would now look like:
```gherkin
Scenario: Large Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor 3 to go to basement
    And there is a call from ground to go to basement
    And there is a call from floor 2 to go to basement
    And there is a call from floor 1 to go to floor 3
    Then the doors should open at floor 1, floor 3, floor 2, ground, basement in this order
    for a total of 5 stops
```

Still assume all calls to the elevator happen in the sequence given but in less than the time it takes to travel one floor. So before the elevator reaches the ground floor, all the commands were received.

The complex scenario, following the same logic, would now look like this
```gherkin
Scenario: Complex Scenario
    Given the elevator is positioned on the ground floor
    When there is a call from floor 1 to go to floor 3
    And there is a call from floor 2 to go to floor 4
    And there is a call from ground to go to floor 2
    And there is a call from floor 1 to go to floor 2
    And there is a call from floor 3 to go to floor 5
    And there is a call from floor 3 to go to basement
    And there is a call from floor 5 to go to ground
    And there is a call from floor 4 to go to floor 2
    And there is a call from floor 2 to go to floor 1
    And there is a call from basement to go to floor 1
    Then the doors should open at floor 1, floor 2, floor 3, floor 2, floor 4, floor 2, floor 1,
     ground, floor 2, floor 3, floor 5, floor 3, basement, floor 5, floor 4, floor 2, ground, basement and floor 1 in this order
    for a total of 19 stops
  ```


The large scenario in the cli looks like

```text
**************************
**    Elevator Kata     **
**************************

> 3-b 0-b 2-b 1-3

Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
Elevator at ground floor
<DING> - door open at ground floor
Elevator at basement
<DING> - door open at basement
```

The complex scenario in the cli looks like

```text
**************************
**    Elevator Kata     **
**************************

> 1-3 2-4 g-2 1-2 3-5 3-b 5-g 4-2 2-1 b-1

Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 3 floor
Elevator at 4 floor
<DING> - door open at 4 floor
Elevator at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at ground floor
<DING> - door open at ground floor
Elevator at 1 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 4 floor
Elevator at 5 floor
<DING> - door open at 5 floor
Elevator at 4 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
Elevator at 1 floor
Elevator at ground floor
Elevator at basement
<DING> - door open at basement
Elevator at ground floor
Elevator at 1 floor
Elevator at 2 floor
Elevator at 3 floor
Elevator at 4 floor
Elevator at 5 floor
<DING> - door open at 5 floor
Elevator at 4 floor
<DING> - door open at 4 floor
Elevator at 3 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
Elevator at ground floor
<DING> - door open at ground floor
Elevator at basement
<DING> - door open at basement
Elevator at ground floor
Elevator at 1 floor
<DING> - door open at 1 floor
```