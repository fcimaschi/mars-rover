package com.federica.space.planet;

public enum PlanetType {
    MARS;

     public static PlanetType fromValue(final String value) {
        return switch (value.trim().toLowerCase()) {
            case "mars" -> MARS;
            default -> null;
        };
    }
}
