package com.federica.space.request;

import java.util.ArrayList;
import java.util.List;

import com.federica.space.command.Command;
import com.federica.space.command.Movement;
import com.federica.space.command.Turn;
import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;
import com.federica.space.planet.Planet;
import com.federica.space.response.CommandResponse;
import com.federica.space.response.CommandResponseStatus;
import com.federica.space.vehicle.Vehicle;

public final class CommandRequestSequence implements CommandRequest {

    private final List<Command> sequence;
    public CommandRequestSequence(final List<Command> sequence) {
        this.sequence = sequence;
    }

    public static CommandRequestSequence createCommandRequest(final String sequence) throws Exception {
        final String[] seq = sequence.split(",");
        final List<Command> currentSequence = new ArrayList<>();
        for (final String s : seq) {

            if (s.trim().isEmpty()) {
                continue;
            }
            
            final Movement m = Movement.fromValue(s);
            if (m != null) {
                currentSequence.add(m);
                continue;
            }
            final Turn t = Turn.fromValue(s);
            if (t != null) {
                currentSequence.add(t);
                continue;
            }
            throw new Exception("Bad command in sequence '" + s + "'. Abort.");
        }
        return new CommandRequestSequence(currentSequence);
    }

    @Override
    public CommandResponse execute(final Planet planet, final Vehicle vehicle) throws Exception {
        
        CommandResponse response = new CommandResponse(CommandResponseStatus.DONE, false, null);
        
        for (final Command command : this.sequence) {
            switch (command) {
                case Movement movement -> {
                    final Coordinates nextStepCoordinates = planet.nextStepCoordinates(vehicle.getCoordinates(), vehicle.getDirection(), movement);
                    if (nextStepCoordinates == null) {
                        response.setExecuted(CommandResponseStatus.PARTIAL);
                        response.setMessage("Found an obstacle while moving to next step!");
                        return response;
                    }
                    vehicle.move(nextStepCoordinates);
                }
                case Turn turn -> {
                    final Direction newDirection = switch (turn) {
                        case Turn.LEFT -> vehicle.getDirection().getLeft();
                        case Turn.RIGHT -> vehicle.getDirection().getRight();
                        default -> null;
                    };
                    if (newDirection == null) {
                        response.setExecuted(CommandResponseStatus.PARTIAL);
                        response.setMessage("Error while evaluating next turn!");
                        return response;
                    }
                    vehicle.turn(newDirection);
                }
                default -> {
                    response.setExecuted(CommandResponseStatus.PARTIAL);
                    response.setMessage("Error while evaluating next command!");
                    return response;
                }
            }
        }
        return response;
    }

    public List<Command> getSequence() {
        return this.sequence;
    }
}