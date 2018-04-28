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
    List<Queen> checkers = new ArrayList<Queen>();
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
    boolean onChop= false;
    Queen checker_on_chop;
    byte turn = 1;
    private Queen search(byte[] first_position){
        for (Queen i:checkers){
            if ((i.position[0] == first_position[0])&&(i.position[1]==first_position[1])) {
                return i;
            }
        }
        return null;
    }
    Game(){
        for(int i=0;i<12;i++){
            checkers.add(new Checker(start_positions[i],white));
        }
        for(int i=12;i<24;i++){
            checkers.add(new Checker(start_positions[i],black));
        }
    }
    private void checker_to_queen(Queen a)
    {if (a.position[1]==3.5-3.5*a.color)
    {
        checkers.remove(a);
        byte[] queen_position=new byte[2];
        queen_position[0]=a.position[0];
        queen_position[1]=a.position[1];
        byte queen_color = (byte)(a.color*2);
        checkers.add(new Queen(queen_position,queen_color));
        field[queen_position[0]][queen_position[1]]=queen_color;
        a=search(queen_position);
    }};
    public void handler(byte[] first_position,byte[] final_position ){
        Queen a=search(first_position);

        if(onChop) {
            checker_on_chop.chop(field,final_position);
            byte[] place_of_chopp ={checker_on_chop.position_of_chop[0],checker_on_chop.position_of_chop[1]};
            Queen b = search(place_of_chopp);
            checker_to_queen(checker_on_chop);
            if(checker_on_chop.check_possible_chop(field)){
            }else {
                onChop=false;
                turn*=-1;
            }
        }else{
            if (a != null) {
                if (a.color*turn>0) {
                    if (!a.move(field,final_position)){
                        if(a.chop(field,final_position)){
                            byte[] place_of_chopp ={a.position_of_chop[0],a.position_of_chop[1]};
                            Queen b = search(place_of_chopp);
                            checker_to_queen(a);
                            if(a.check_possible_chop(field)){
                                onChop=true;
                                checker_on_chop=a;
                            }else{
                                turn*=-1;
                            }
                        }
                    }else{
                        checker_to_queen(checker_on_chop);
                        turn*=-1;
                    }
                }
            }
        }
    }

}
