package com.federica.space.planet;

public final class PlanetFactory {

    private PlanetFactory() {
        // no-op
    }
    
    public static Planet createPlanetFromFile(final PlanetType planetType, final String path) throws Exception {
        return switch (planetType) {
            case MARS -> Mars.createPlanetFromFile(path);
            default -> null;
        };
    }
}
