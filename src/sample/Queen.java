package sample;

public class Queen extends Checker {

    Queen(byte[] position1, byte color1) {
        super(position1, color1);
    }

    public boolean chop(byte[][] field, byte[] place_of_chop) {
        if (Math.abs(place_of_chop[0] - position[0]) != Math.abs(place_of_chop[1] - position[1])) return false;
        if (place_of_chop[0] - position[0] == 0) return false;

        byte[] enemies = countEnemies(field, place_of_chop);

        if (enemies[0] > 1 || enemies[1] != 1) return false;
        else if (field[place_of_chop[0]][place_of_chop[1]] == 0) {
            field[enemies[2]][enemies[3]] = 0;
            field[place_of_chop[0]][place_of_chop[1]] = (byte) (color * 2);
            field[position[0]][position[1]] = 0;

            position[0] = place_of_chop[0];
            position[1] = place_of_chop[1];

            chopped[0] = enemies[2];
            chopped[1] = enemies[3];
            return true;
        }
        return false;
    }

    public boolean check_chop(byte[][] field, byte[] place_of_chop) {
        if (Math.abs(place_of_chop[0] - position[0]) != Math.abs(place_of_chop[1] - position[1])) return false;
        if (place_of_chop[0] - position[0] == 0) return false;

        byte[] enemies = countEnemies(field, place_of_chop);

        if (enemies[0] > 1 || enemies[1] != 1) return false;
        else if (field[place_of_chop[0]][place_of_chop[1]] == 0) return true;

        return false;
    }

    public boolean check_possible_chop(byte[][] field) {

        boolean diag = false;
        for (int h = this.position[0] - 7; h < 8; h++) {
            for (int w = this.position[1] - 7; w < 8; w++) {
                if ((w > 0) && (h > 0) && (w != this.position[1])) {
                    byte position[] = {(byte) h, (byte) w};
                    diag = check_chop(field, position);
                    if (diag) return true;
                }
            }
        }
        for (int h = this.position[0] - 7; h < 8; h++) {
            for (int w = this.position[1] + 7; w > 0; w--) {
                if ((w < 8) && (h > 0) && (w != this.position[1])) {
                    byte position[] = {(byte) h, (byte) w};
                    diag = check_chop(field, position);
                    if (diag) return true;
                }
            }
        }
        return diag;
    }

    public boolean move(byte[][] field, byte[] place_of_move) {
        if (Math.abs(place_of_move[0] - position[0]) != Math.abs(place_of_move[1] - position[1])) return false;
        if (place_of_move[0] - position[0] == 0) return false;

        byte[] enemies = countEnemies(field, place_of_move);

        if (enemies[0] > 0) return false;
        else if (field[place_of_move[0]][place_of_move[1]] == 0) {
            field[place_of_move[0]][place_of_move[1]] = (byte) (color * 2);
            field[position[0]][position[1]] = 0;
            position[0] = place_of_move[0];
            position[1] = place_of_move[1];
            return true;
        }
        return false;
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
                if (field[x][y] * color <= (-1)) {
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
