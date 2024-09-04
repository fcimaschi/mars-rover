package com.federica.space.request;

import com.federica.space.planet.Planet;
import com.federica.space.response.CommandResponse;
import com.federica.space.response.CommandResponseStatus;
import com.federica.space.vehicle.Vehicle;

public final class CommandRequestState implements CommandRequest {

    private static final CommandRequestState instance = new CommandRequestState();

    private CommandRequestState() {
        // no-op
    }

    public static CommandRequestState getInstance() {
        return instance;
    }

    @Override
    public CommandResponse execute(final Planet planet, final Vehicle vehicle) throws Exception {
        final String currentPosition = planet.plotPosition(vehicle.getCoordinates(), vehicle.getDirection());
        return new CommandResponse(CommandResponseStatus.DONE, false, currentPosition);
    }
}
