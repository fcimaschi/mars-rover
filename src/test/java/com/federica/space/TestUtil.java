package com.federica.space;

import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;
import com.federica.space.planet.Planet;

public class TestUtil {

    static final boolean PRINT_DEBUG = true;
    
    public static void plotPosition(final Planet planet, final Coordinates coordinates, final Direction direction) {
        if (PRINT_DEBUG) {
            System.out.println("Plotting " + coordinates.toString() + " - Direction " + direction.toString());
            final String plot = planet.plotPosition(coordinates, direction);
            System.out.println(plot);
        }
    }
}
