import java.util.ArrayList;
import java.util.List;

public class Game {
    byte white = 1;
    byte black = -1;
    byte[][] white_start_position= {
            {0,7},
            {2,7},
            {4,7},
            {6,7},
            {1,6},
            {3,6},
            {5,6},
            {7,6},
            {0,5},
            {2,5},
            {4,5},
            {6,5},
    };
    byte[][] black_start_position= {
            {1,2},
            {3,2},
            {5,2},
            {7,2},
            {0,1},
            {2,1},
            {4,1},
            {6,1},
            {1,0},
            {3,0},
            {5,0},
            {7,0},

    };
    byte turn;
    private Checker
    Game(){
        List<Checker> white_checker= new ArrayList<Checker>();
        for(int i=0;i<12;i++){
            white_checker.add(new Checker(white_start_position[i],white));
        }
        List<Checker> black_checker=new ArrayList<Checker>();
        for(int i=0;i<12;i++){
            black_checker.add(new Checker(black_start_position[i],black));
        }
        List<Queen> white_queen = new ArrayList<Queen>();
        List<Queen> black_queen = new ArrayList<Queen>();

    }
    public void Handler(byte[] start_position,byte[] final_position ){

    }
}
