package com.federica.space.vehicle;

import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;
import com.federica.space.planet.PlanetType;

public final class VehicleFactory {

    private VehicleFactory() {
        // no-op
    }
    
    public static Vehicle createVehicleFromPlanet(final PlanetType planetType, final Coordinates startingCoordinates, final Direction startingDirection) throws Exception {
        return switch (planetType) {
            case MARS -> new MarsRover(startingCoordinates, startingDirection);
            default -> null;
        };
    }
}
