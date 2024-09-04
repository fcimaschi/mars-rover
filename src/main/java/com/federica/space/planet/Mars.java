package com.federica.space.planet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.List;

import com.federica.space.command.Movement;
import com.federica.space.model.Coordinates;
import com.federica.space.model.Direction;

public final class Mars implements Planet {
    
    private final char[][] grid;

    private static final EnumMap<Direction, int[]> MOVEMENTS;
    static {
        MOVEMENTS = new EnumMap<>(Direction.class);
        MOVEMENTS.put(Direction.N, new int[] {-1, 0});
        MOVEMENTS.put(Direction.E, new int[] {0, 1});
        MOVEMENTS.put(Direction.S, new int[] {1, 0});
        MOVEMENTS.put(Direction.W, new int[] {0, -1});
    }
    
    public Mars(final char[][] grid) {
        this.grid = grid;
    }

    public static Mars createPlanetFromFile(final String planet) throws Exception {
        final Path path = Path.of(planet);
        final List<String> allLines;
        try {
            allLines = Files.readAllLines(path);
        } catch (final IOException e) {
            throw new Exception("Error reading file: '" + path.toAbsolutePath() + "'.");
        }
        if (allLines.isEmpty()) {
            throw new Exception("Planet not mapped!");
        }
        final char[][] chars = new char[allLines.size()][allLines.get(0).length()];
        final int lineLength = allLines.get(0).length();

        for (int i = 0; i < allLines.size(); i++) {
            final String line = allLines.get(i);
            if (line.length() != lineLength) {
                throw new Exception("Planet is not a grid!");
            }
            final char[] lineChars = line.toCharArray();
            for (int j = 0; j < lineChars.length; j++) {
                chars[i][j] = lineChars[j];
            }
        }
        return new Mars(chars);
    }

    public char[][] getGrid() {
        return grid;
    }

    @Override
    public PlanetType getPlanetType() {
        return PlanetType.MARS;
    }

    @Override
    public boolean detectCollision(final Coordinates coordinates) {
        return grid[coordinates.y()][coordinates.x()] == 'x';
    }

    @Override
    public Coordinates nextStepCoordinates(final Coordinates coordinates, final Direction direction, final Movement movement) throws Exception {

        if (detectCollision(coordinates)) {
            throw new Exception("Mars Rover is in a prohibited position. Abort!");
        }
        final int multiplier = (movement == Movement.FORWARD) ? 1 : -1;
        final int[] mvs = MOVEMENTS.get(direction);
        int i = coordinates.y() + (mvs[0] * multiplier);
        if (i >= grid.length) {
            i = 0;
        } else if (i < 0) {
            i = grid.length-1;
        }

        int j = coordinates.x() + (mvs[1] * multiplier);
        if (j >= grid[0].length) {
            j = 0;
        } else if (j < 0) {
            j = grid[0].length-1;
        }

        final Coordinates newCoordinates = new Coordinates(j, i);
        if (detectCollision(newCoordinates)) {
            return null;
        } else {
            return newCoordinates;
        }
    }

    @Override
    public String plotPosition(final Coordinates coordinates, final Direction direction) {
        
        final StringBuilder buffer = new StringBuilder("\n");
        for (int i = 0; i < grid.length; i++) {
            final char[] chars = grid[i];
            for (int j = 0; j < chars.length; j++) {
                final char c = chars[j];
                if (i == coordinates.y() && j == coordinates.x()) {
                    switch (direction) {
                        case N -> buffer.append("^");
                        case E -> buffer.append(">");
                        case S -> buffer.append("v");
                        case W -> buffer.append("<");
                    }
                } else {
                    buffer.append(c);
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
