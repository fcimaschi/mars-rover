package com.federica.space.planet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.federica.space.TestUtil;
import com.federica.space.command.Movement;
import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;

public class TestMars {

    static final String BASE_PATH = "src/test/resources/planet/";

    @Test
    public void testCreatePlanet() throws Exception {
        final char[][] grid = new char[][] {
            new char[] {'-', '-', 'x', '-', '-'},
            new char[] {'-', '-', '-', '-', 'x'},
            new char[] {'x', '-', '-', '-', '-'},
            new char[] {'-', 'x', '-', '-', '-'},
            new char[] {'-', '-', '-', 'x', '-'}
        };
        final Mars expected = new Mars(grid);
        final Mars found = Mars.createPlanetFromFile(BASE_PATH + "mars_create_planet.txt");
        Assertions.assertArrayEquals(expected.getGrid(), found.getGrid());
    }

    @Test
    public void testCreatePlanetException() throws Exception {
        try {
            Mars.createPlanetFromFile(BASE_PATH + "mars_create_planet_exception.txt");
            Assertions.fail("Should throw Exception!");
        } catch (Exception e) {
            Assertions.assertEquals("Planet is not a grid!", e.getMessage());
        }
    }

    @Test
    public void testNextStepCoordinates() throws Exception {
        final Coordinates expected = new Coordinates(2, 0);
        final Mars planet = Mars.createPlanetFromFile(BASE_PATH + "mars_next_step_coordinates.txt");
        
        final Coordinates startigPoint = new Coordinates(1,0);
        final Direction direction = Direction.E;
        TestUtil.plotPosition(planet, startigPoint, direction);

        final Coordinates found = planet.nextStepCoordinates(startigPoint, direction, Movement.FORWARD);
        TestUtil.plotPosition(planet, found, direction);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(expected.x(), found.x());
        Assertions.assertEquals(expected.y(), found.y());
    }

    @Test
    public void testNextStepCoordinateCollisionAtStartup() throws Exception {
        final Mars planet = Mars.createPlanetFromFile(BASE_PATH + "mars_next_step_coordinates_collision_at_startup.txt");
        final Coordinates startigPoint = new Coordinates(1,0);
        final Direction direction = Direction.E;
        try {
            final Coordinates found = planet.nextStepCoordinates(startigPoint, direction, Movement.FORWARD);
            Assertions.assertNull(found);
        } catch (final Exception e) {
            Assertions.assertEquals("Mars Rover is in a prohibited position. Abort!", e.getMessage());
        }
    }

    @Test
    public void testNextStepCoordinateCollision() throws Exception {
        final Mars planet = Mars.createPlanetFromFile(BASE_PATH + "mars_next_step_coordinates_collision.txt");
        
        final Coordinates startigPoint = new Coordinates(0,3);
        final Direction direction = Direction.E;
        TestUtil.plotPosition(planet, startigPoint, direction);

        final Coordinates found = planet.nextStepCoordinates(startigPoint, direction, Movement.FORWARD);
        Assertions.assertNull(found);
    }

    @Test
    public void testNextStepCoordinatesWrapping() throws Exception {
        final Coordinates expected = new Coordinates(2, 4);
        final Mars planet = Mars.createPlanetFromFile(BASE_PATH + "mars_next_step_coordinates_wrapping.txt");

        final Coordinates startigPoint = new Coordinates(2,0);
        final Direction direction = Direction.S;
        TestUtil.plotPosition(planet, startigPoint, direction);

        final Coordinates found = planet.nextStepCoordinates(startigPoint, direction, Movement.BACKWARD);
        TestUtil.plotPosition(planet, found, direction);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(expected.x(), found.x());
        Assertions.assertEquals(expected.y(), found.y());
    }
}
