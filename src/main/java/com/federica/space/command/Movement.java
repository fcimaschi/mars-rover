package com.federica.space.command;

public enum Movement implements Command {
    FORWARD, BACKWARD;

    public static Movement fromValue(final String value) {
        return switch (value.trim().toLowerCase()) {
            case "f" -> FORWARD;
            case "b" -> BACKWARD;
            default -> null;
        };
    }
}
