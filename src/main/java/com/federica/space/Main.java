package com.federica.space;

import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;
import com.federica.space.planet.Planet;
import com.federica.space.vehicle.Vehicle;
import com.federica.space.vehicle.VehicleFactory;

public final class Main 
{

    private Main() {
        // no-op
    }

    public static void main(final String[] args)
    {
        try {
            System.out.println("\n\nInitializing Mars Rover communication...\n");

            VerifyParams.verifyParams(args);

            System.out.print("Initialize Mars signal... ");
            final Planet planet = VerifyParams.getPlanet(args[0], args[1]);
            System.out.println("Reached!");

            final Coordinates startingPoint = VerifyParams.getStartingPoint(planet, args[2]);
            final Direction startingDirection = VerifyParams.getFacingDirection(args[3]);
            final Vehicle vehicle = VehicleFactory.createVehicleFromPlanet(planet.getPlanetType(), startingPoint, startingDirection);
            System.out.println(vehicle.getHumanPosition());
            
            System.out.println("Initialization success! Please input command sequence...");
            System.out.println("Use 'f' to move forward, 'b' to move backward, 'l' to turn left, 'r' to turn right.");
            System.out.println("Use 'state' to plot current state on planet, use 'bye' to close connection.");
            
            Executor.runLoop(planet, vehicle);
            System.out.println("Mars Rover communication closed.\n");

        } catch (final Exception e) {
            System.out.println("Mars Rover communication interrupted with error.\n" + e.getMessage());
        }
    }
}
