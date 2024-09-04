package com.federica.space.vehicle;

import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;

public abstract class Vehicle {;
    private Coordinates coordinates;
    private Direction direction;

    protected Vehicle(final Coordinates coordinates, final Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void move(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void turn(final Direction direction) {
        this.direction = direction;
    }

    public String getHumanPosition() {
        return "Vehicle is located at {" + coordinates.x() + "," + coordinates.y() + "} facing " + direction.getLabel();
    }
}
