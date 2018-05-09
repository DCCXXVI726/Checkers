package sample;

public class Checker {
    public byte color;
    public byte[] position = new byte[2];

    Checker(byte[] position1, byte color1) {
        color = color1;
        position[0] = position1[0];
        position[1] = position1[1];
    }

    public boolean chop(byte[][] field, byte[] place_of_chop) {
        if (Math.abs(place_of_chop[0] - position[0]) == 2
                && Math.abs(place_of_chop[1] - position[1]) == 2
                && field[(place_of_chop[0] - position[0]) / 2 + position[0]][(place_of_chop[1] - position[1]) / 2 + position[1]] * (color) < 0
                && field[place_of_chop[0]][place_of_chop[1]] == 0) {
            field[place_of_chop[0]][place_of_chop[1]] = color;
            field[(place_of_chop[0] - position[0]) / 2 + position[0]][(place_of_chop[1] - position[1]) / 2 + position[1]] = 0;
            field[position[0]][position[1]] = 0;
            position[0] = place_of_chop[0];
            position[1] = place_of_chop[1];
            return true;
        } else {
            return false;
        }
    }

    public boolean check_chop(byte[][] field, byte[] place_of_chop) {
        if (Math.abs(place_of_chop[0] - position[0]) == 2
                && Math.abs(place_of_chop[1] - position[1]) == 2
                && field[(place_of_chop[0] - position[0]) / 2 + position[0]][(place_of_chop[1] - position[1]) / 2 + position[1]] * (color) < 0
                && field[place_of_chop[0]][place_of_chop[1]] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean check_possible_chop(byte[][] field) {
        boolean possible = false;
        byte[][] possible_place_of_chop = {
                {(byte) (position[0] + 2), (byte) (position[1] + 2)},
                {(byte) (position[0] + 2), (byte) (position[1] - 2)},
                {(byte) (position[0] - 2), (byte) (position[1] + 2)},
                {(byte) (position[0] - 2), (byte) (position[1] - 2)}
        };
        for (byte[] i : possible_place_of_chop) {
            if ((i[0] > 0 && i[0] < 8) && (i[1] > 0 && i[1] < 8) && (check_chop(field, i))) {
                possible = true;
            }
            ;
        }
        return possible;
    }

    public boolean move(byte[][] field, byte[] place_of_move) {
        if (Math.abs(place_of_move[0] - position[0]) == 1
                && Math.abs(place_of_move[1] - position[1]) == 1
                && field[place_of_move[0]][place_of_move[1]] == 0) {
            field[place_of_move[0]][place_of_move[1]] = color;
            field[position[0]][position[1]] = 0;
            position[0] = place_of_move[0];
            position[1] = place_of_move[1];
            return true;
        }
        return false;
    }

}
