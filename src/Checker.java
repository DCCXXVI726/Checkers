public class Checker {
    public int color;
    public byte[] position;
    Checker(byte[] position1,int color1){
        color = color1;
        position = position1;
    }
    public int[][] chop(int[][]field ,byte[] place_of_chop){
        if ((place_of_chop[0]==0)||(place_of_chop[1]==0)||(place_of_chop[0]==7)||(place_of_chop[1]==7)||(field[place_of_chop[0]][place_of_chop[1]]==color*(-1))){
        }else {
            if (field [place_of_chop[0]*2-position[0]][place_of_chop[1]*2-position[1]]==0){
                field[place_of_chop[0]][place_of_chop[1]]=0;
                field [place_of_chop[0]*2-position[0]][place_of_chop[1]*2-position[1]]=color;
            }
        }
        return field;
    }

}
