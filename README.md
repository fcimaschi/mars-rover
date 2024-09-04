# Mars Rover

You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop an API that translates the commands sent from earth to instructions that are understood by the rover.

## Requirements
- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

# Prerequisites
JAVA 21 installed in the classpath (JRE to execute, JDK to compile and tests).

# Build&Run
To run, use the 'run.sh' and specify:

```./run.sh {planetName} {sourcePlanet} {startingPoint.x,startingPoint.y} {direction}```

- available 'planetName': "mars"
- 'sourcePlanet': path to the desired planet
- 'startingPoint': coordinates of the starting point in this format (x,y)
- 'direction': "N", "E", "S", "W"

For example:

```./run.sh mars resources/mars.txt 0,2 S```

## Compile with tests

```mvn clean package install```

## Compile and skip tests

```mvn clean package install -DskipTests```

# Interactive shell available commands:
- 'bye': close communication
- 'state': plot current state on planet
- sequence of char separated by ',' to move and turn the vehicle
('f' to move forward, 'b' to move backward, 'l' to turn left, 'r' to turn right). For example "f,f,b,l,l".


# Shortcuts used in the exercise

## Error management
Simple "Exception" is used, in production code an object like the following should be used.

```{ "incidentId" : "a1b2C3", "status": "READABLE_STATUS", "message": "Detailed message", params: {...}}```

## Input and output
Input and output is done through Java standard System.in and System.out, in this case the shell prompt is used.
In real environment it will be better if the system will be pluggable.
