package com.federica.space.command;

public enum Turn implements Command { 
    LEFT, RIGHT;

    public static Turn fromValue(final String value) {
        return switch (value.trim().toLowerCase()) {
            case "l" -> LEFT;
            case "r" -> RIGHT;
            default -> null;
        };
    }
}
