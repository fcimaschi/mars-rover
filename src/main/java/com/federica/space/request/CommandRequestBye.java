package com.federica.space.request;

import com.federica.space.planet.Planet;
import com.federica.space.response.CommandResponse;
import com.federica.space.response.CommandResponseStatus;
import com.federica.space.vehicle.Vehicle;

public final class CommandRequestBye implements CommandRequest {

    private static final CommandRequestBye instance = new CommandRequestBye();

    private CommandRequestBye() {
        // no-op
    }

    public static CommandRequestBye getInstance() {
        return instance;
    }

    @Override
    public CommandResponse execute(final Planet planet, final Vehicle vehicle) throws Exception {
        return new CommandResponse(CommandResponseStatus.DONE, true, null);
    }
}
