import java.util.ArrayList;
import java.util.List;

public class Game {
    byte white = 1;
    byte black = -1;
    byte[][] start_positions= {
            {7,0},
            {7,2},
            {7,4},
            {7,6},
            {6,1},
            {6,3},
            {6,5},
            {6,7},
            {5,0},
            {5,2},
            {5,4},
            {5,6},
            {2,1},
            {2,3},
            {2,5},
            {2,7},
            {1,0},
            {1,2},
            {1,4},
            {1,6},
            {0,1},
            {0,3},
            {0,5},
            {0,7},

    };
    List<Checker> checkers = new ArrayList<Checker>();
    byte field[][] = {
            {0, -1, 0, -1, 0, -1, 0, -1},
            {-1, 0, -1, 0, -1, 0, -1, 0},
            {0, -1, 0, -1, 0, -1, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0}
    };

    boolean onChop= false;
    byte turn = 1;

    private Checker search(byte[] first_position){
        for (Checker i:checkers){
            if (i.position ==first_position) {
                return i;
            }
        }
        return null;
    }
    Game(){
        for(int i=0;i<24;i++){
            checkers.add(new Checker(start_positions[i],white));
        }

    }
    public void handler(byte[] first_position,byte[] final_position ){
        Checker a=search(first_position);
        if (a!=null){
            if(a.color==turn){

            }
        }
    }
}
