package com.federica.space;

import com.federica.space.planet.Planet;
import com.federica.space.request.CommandRequest;
import com.federica.space.request.CommandRequestFactory;
import com.federica.space.response.CommandResponse;
import com.federica.space.vehicle.Vehicle;

public final class Executor {

    private Executor() {
        // no-op
    }

    public static void runLoop(final Planet planet, final Vehicle vehicle) {
        boolean receiveCommands = true;
        while (receiveCommands) {
            System.out.println("Please input command sequence...");
            final String commands = System.console().readLine();
            try {
                final CommandRequest commandRequest = CommandRequestFactory.createCommandRequest(commands);
                final CommandResponse commandResponse = commandRequest.execute(planet, vehicle);
                if (commandResponse.getShouldAbort()) {
                    receiveCommands = false;
                }
                System.out.println(commandResponse);
            } catch (Exception e) {
                System.out.println("Error! " + e.getMessage());
                System.out.println("Abort current message!");
            }
        }
    }
}
