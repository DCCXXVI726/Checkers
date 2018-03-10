public class Checker {
    public byte color;
    public byte[] position= new byte[2];
    Checker(byte[] position1,byte color1){
        color = color1;
        position[0] = position1[0];
        position[1] = position1[1];
    }
    public byte[][] chop(byte[][] field ,byte[] place_of_chop){
        if (Math.abs(place_of_chop[0]-position[0])==2
                &&Math.abs(place_of_chop[1]-position[1])==2
                &&field[(place_of_chop[0]-position[0])/2+position[0]][(place_of_chop[1]-position[1])/2+position[1]]==(color*-1)
                &&field[place_of_chop[0]][place_of_chop[1]]==0){
            field[place_of_chop[0]][place_of_chop[1]]=color;
            field[(place_of_chop[0]-position[0])/2+position[0]][(place_of_chop[1]-position[1])/2+position[1]]=0;
            field[position[0]][position[1]]=0;
            position[0]=place_of_chop[0];
            position[1]=place_of_chop[1];
        }
        return field;
    }

}
