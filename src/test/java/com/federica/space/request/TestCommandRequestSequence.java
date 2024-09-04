package com.federica.space.request;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.federica.space.TestUtil;
import com.federica.space.command.Movement;
import com.federica.space.command.Turn;
import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;
import com.federica.space.planet.Mars;
import com.federica.space.response.CommandResponse;
import com.federica.space.response.CommandResponseStatus;
import com.federica.space.vehicle.MarsRover;

public class TestCommandRequestSequence {

    static final String BASE_PATH = "src/test/resources/planet/";
    
    @Test
    public void testCommandSequence1() throws Exception {

        final CommandResponse expectedResponse = new CommandResponse(CommandResponseStatus.DONE, false, null);
        final Coordinates expectedCoordinates = new Coordinates(2,4);
        final Direction expectedDirection = Direction.S;

        final Mars planet = Mars.createPlanetFromFile(BASE_PATH + "mars_command_sequence_1.txt");
        final Coordinates coordinates = new Coordinates(3, 1);
        final Direction direction = Direction.E;
        final MarsRover marsRover = new MarsRover(coordinates, direction);

        final CommandRequestSequence request = new CommandRequestSequence(List.of(Turn.RIGHT, Turn.RIGHT, Movement.FORWARD, Turn.LEFT, Movement.BACKWARD, Movement.BACKWARD));
        final CommandResponse found = request.execute(planet, marsRover);

        Assertions.assertEquals(expectedResponse.getExecuted(), found.getExecuted());
        Assertions.assertEquals(expectedResponse.getShouldAbort(), found.getShouldAbort());
        Assertions.assertNull(found.getMessage());
        Assertions.assertEquals(expectedCoordinates, marsRover.getCoordinates());
        Assertions.assertEquals(expectedDirection, marsRover.getDirection());

        TestUtil.plotPosition(planet, marsRover.getCoordinates(), marsRover.getDirection());
    }

    @Test
    public void testCommandSequence2() throws Exception {

        final CommandResponse expected = new CommandResponse(CommandResponseStatus.PARTIAL, false, "Found an obstacle while moving to next step!");
        final Coordinates expectedCoordinates = new Coordinates(1,2);
        final Direction expectedDirection = Direction.E;

        final Mars planet = Mars.createPlanetFromFile(BASE_PATH + "mars_command_sequence_2.txt");
        final Coordinates coordinates = new Coordinates(4, 2);
        final Direction direction = Direction.E;
        final MarsRover marsRover = new MarsRover(coordinates, direction);

        final CommandRequestSequence request = new CommandRequestSequence(List.of(Movement.FORWARD,  Movement.FORWARD,  Movement.FORWARD,  Movement.FORWARD));
        
        final CommandResponse found = request.execute(planet, marsRover);
        Assertions.assertEquals(expected.getExecuted(), found.getExecuted());
        Assertions.assertEquals(expected.getShouldAbort(), found.getShouldAbort());
        Assertions.assertEquals(expected.getMessage(), found.getMessage());
        Assertions.assertEquals(expectedCoordinates, marsRover.getCoordinates());
        Assertions.assertEquals(expectedDirection, marsRover.getDirection());

        TestUtil.plotPosition(planet, marsRover.getCoordinates(), marsRover.getDirection());
    }
}
