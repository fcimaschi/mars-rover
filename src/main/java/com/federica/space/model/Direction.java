package com.federica.space.model;

public enum Direction { 
    N, E, S, W;

    public String getLabel() {
        return switch (this) {
            case N -> "North";
            case E -> "East";
            case S -> "South";
            case W -> "West";
            default -> null;
        };
    }

    public Direction getLeft() {
        return switch (this) {
            case N -> W;
            case E -> N;
            case S -> E;
            case W ->  S;
        };
    }

    public Direction getRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}
