package com.federica.space;

import java.util.Arrays;

import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;
import com.federica.space.planet.Planet;
import com.federica.space.planet.PlanetFactory;
import com.federica.space.planet.PlanetType;

public final class VerifyParams {

    private static final String INSTRUCTIONS = "Please specify runtime params: {planetName} {sourcePlanet} {startingPoint.x,startingPoint.y} {direction}\nExample: ./run.sh mars resources/mars.txt 1,1 E";
    private VerifyParams() {
        // no-op
    }
    
    public static void verifyParams(final String[] args) throws Exception {
        if (args == null) {
            throw new Exception(INSTRUCTIONS);
        }

        if (args.length < 1) {
            throw new Exception("Missing planet name!\n" + INSTRUCTIONS);
        }

        if (args.length < 2) {
            throw new Exception("Missing source planet!\n" + INSTRUCTIONS);
        }

        if (args.length < 3) {
            throw new Exception("Missing starting point!\n" + INSTRUCTIONS);
        }

        if (args.length < 4) {
            throw new Exception("Missing facing direction!\n" + INSTRUCTIONS);
        }
    }
    
    public static Planet getPlanet(final String planetName, final String filePath) throws Exception {
        final PlanetType planetType = PlanetType.fromValue(planetName);
        return PlanetFactory.createPlanetFromFile(planetType, filePath);
    }

    public static Coordinates getStartingPoint(final Planet planet, final String startingPoint) throws Exception {
        final String[] coords = startingPoint.split(",");
        if (coords.length != 2) {
            throw new Exception("Bad starting point!");
        }

        final int x; 
        try {
            x = Integer.parseInt(coords[0]);
        } catch (NumberFormatException e) {
            throw new Exception("Bad 'x' starting point!");
        }

        final int y; 
        try {
            y = Integer.parseInt(coords[1]);
        } catch (NumberFormatException e) {
            throw new Exception("Bad 'y' starting point!");
        }
        final Coordinates coordinates = new Coordinates(x, y);
        if (planet.detectCollision(coordinates)) {
            throw new Exception("Mars Rover is in a prohibited position. Abort!");
        } else {
            return coordinates;
        }
    }

    public static Direction getFacingDirection(final String facingDirection) throws Exception {
        try {
            return Direction.valueOf(facingDirection);
        } catch (final Exception e) {
            throw new Exception("Wrong facing direction value: '" + facingDirection + "'. Available: " + Arrays.toString(Direction.values()));
        }
    }
}
