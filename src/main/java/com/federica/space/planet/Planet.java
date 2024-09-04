package com.federica.space.planet;

import com.federica.space.command.Movement;
import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;

public interface Planet {
    PlanetType getPlanetType();
    boolean detectCollision(final Coordinates coordinates);
    Coordinates nextStepCoordinates(final Coordinates coordinates, final Direction direction, final Movement command) throws Exception;
    String plotPosition(final Coordinates coordinates, final Direction direction);
}
