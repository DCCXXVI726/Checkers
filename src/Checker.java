public class Checker {
    public byte color;
    public byte[] position;
    Checker(byte[] position1,byte color1){
        color = color1;
        position = position1;
    }
    public byte[][] chop(byte[][] field ,byte[] place_of_chop){
        if ((place_of_chop[0]==0)||(place_of_chop[1]==0)||(place_of_chop[0]==7)||(place_of_chop[1]==7)||(field[place_of_chop[0]][place_of_chop[1]]==color*(+1))){
        }else {
            if (field [place_of_chop[0]*2-position[0]][place_of_chop[1]*2-position[1]]==0){
                field[place_of_chop[0]][place_of_chop[1]]=0;
                field[position[0]][position[1]]=0;
                field [place_of_chop[0]*2-position[0]][place_of_chop[1]*2-position[1]]=color;
            }
        }
        return field;
    }

}
