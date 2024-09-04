package com.federica.space.request;

import com.federica.space.planet.Planet;
import com.federica.space.response.CommandResponse;
import com.federica.space.vehicle.Vehicle;

public interface CommandRequest {
    CommandResponse execute(final Planet planet, final Vehicle vehicle) throws Exception;
}
