public class Queen extends Checker {

    Queen(byte[] position1, byte color1) {
        super(position1, color1);
    }

    public byte[][] chop(byte[][] field, byte[] place_of_chop) {
        if (Math.abs(place_of_chop[0] - position[0]) != Math.abs(place_of_chop[1] - position[1])) return field;

        byte[] enemies = countEnemies(field, place_of_chop);

        if (enemies[0] > 1 || enemies[1] != 1) return field;
        else if (field[place_of_chop[0]][place_of_chop[1]] == 0) {
            field[enemies[2]][enemies[3]] = 0;
            field[place_of_chop[0]][place_of_chop[1]] = color;
            field[position[0]][position[1]] = 0;
            position[0] = place_of_chop[0];
            position[1] = place_of_chop[1];
        }
        return field;
    }

    public byte[][] move(byte[][] field, byte[] place_of_move) {
        if (Math.abs(place_of_move[0] - position[0]) != Math.abs(place_of_move[1] - position[1])) return field;

        byte[] enemies = countEnemies(field, place_of_move);

        if (enemies[0] > 0) return field;
        else if (field[place_of_move[0]][place_of_move[1]] == 0) {
            field[place_of_move[0]][place_of_move[1]] = color;
            field[position[0]][position[1]] = 0;
            position[0] = place_of_move[0];
            position[1] = place_of_move[1];
        }
        return field;
    }

    private byte[] countEnemies(byte[][] field, byte[] finalPosition) {
        int xStep = finalPosition[0] - position[0];
        xStep = xStep / Math.abs(xStep);
        int yStep = finalPosition[1] - position[1];
        yStep = yStep / Math.abs(yStep);
        byte xVictimPos = 0, yVictimPos = 0;
        byte numOfVictims = 0, numOfCheckers = 0;

        for (int b = 1; finalPosition[0] - b * xStep - position[0] != 0; b++) {
            int x = position[0] + b * xStep;
            int y = position[1] + b * yStep;
            if (field[x][y] != 0) {
                numOfCheckers += 1;
                if (field[x][y] == color * (-1)) {
                    numOfVictims++;
                    xVictimPos = (byte) x;
                    yVictimPos = (byte) y;
                }
            }
        }

        byte[] result = {numOfCheckers, numOfVictims, xVictimPos, yVictimPos};
        return result;
    }
}
