package sample;

import java.util.ArrayList;
import java.util.List;

public class Game {
    byte white = 1;
    byte black = -1;
    private byte numberOfWhite = 12;
    private byte numberOfBlack = 12;

    public boolean endGame(){
        if (numberOfWhite==0||numberOfBlack==0){
            return true;
        }
        return false;
    }
    List<Checker> checkers = new ArrayList<Checker>();
    public byte[][] field = {
            {0, -1, 0, -1, 0, -1, 0, -1},
            {-1, 0, -1, 0, -1, 0, -1, 0},
            {0, -1, 0, -1, 0, -1, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0}
    };
    boolean onChop = false;
    Checker checker_on_chop;
    byte turn = 1;

    private Checker search(byte[] first_position) {
        for (Checker i : checkers) {
            if ((i.position[0] == first_position[0]) && (i.position[1] == first_position[1])) {
                return i;
            }
        }
        return null;
    }

    private void delete(byte[] position) {
        for (Checker i : checkers) {
            if ((i.position[0] == position[0]) && (i.position[1] == position[1])) {
                checkers.remove(i);
                break;
            }
        }
    }

    private Checker change(Checker changed, byte[][] field) {
        if (changed.position[0] == 3.5 - (changed.color) * 3.5) {
            byte icolor = changed.color;
            byte[] iposition = new byte[2];
            iposition[0] = changed.position[0];
            iposition[1] = changed.position[1];
            Queen s = new Queen(iposition, icolor);
            for (Checker i : checkers) {
                if ((i.position[0] == changed.position[0]) && (i.position[1] == changed.position[1])) {
                    checkers.remove(i);
                    checkers.add(s);
                    field[iposition[0]][iposition[1]] = (byte) (2 * icolor / Math.abs(icolor));
                    break;
                }
            }
            return s;
        } else {
            return changed;
        }
    }

    Game() {
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                byte color = field[i][k];
                switch (color) {
                    case 1:
                    case -1:
                        checkers.add(new Checker(new byte[]{(byte) i, (byte) k}, color));
                        break;
                    case 2:
                    case -2:
                        checkers.add(new Queen(new byte[]{(byte) i, (byte) k}, color));
                        break;
                }
            }
        }
    }

    public void handler(byte[] first_position, byte[] final_position) {
        Checker a = search(first_position);
        if (onChop) {
            checker_on_chop.chop(field, final_position);
            delete(checker_on_chop.chopped);

            checker_on_chop = change(checker_on_chop,field);
            if (a.color==1){
                numberOfBlack-=1;
            }
            if (a.color==-1){
                numberOfWhite-=1;
            }

            checker_on_chop = change(checker_on_chop, field);

            checker_on_chop = change(checker_on_chop, field);
            checker_on_chop = change(checker_on_chop, field);

            if (checker_on_chop.check_possible_chop(field)) {
            } else {
                onChop = false;
                turn *= -1;
            }
        } else {
            if (a != null) {
                if (a.color * turn > 0) {
                    if (!a.move(field, final_position)) {
                        if (a.chop(field, final_position)) {
                            if (a.color==1){
                                numberOfBlack-=1;
                            }
                            if (a.color==-1){
                                numberOfWhite-=1;
                            }
                            delete(a.chopped);
                            a = change(a, field);
                            if (a.check_possible_chop(field)) {
                                onChop = true;
                                checker_on_chop = a;
                            } else {
                                turn *= -1;
                            }
                        }
                    } else {
                        change(a, field);
                        turn *= -1;
                    }
                }
            }
        }
    }
}

