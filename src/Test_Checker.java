public class Test_Checker {
    byte[][] test_field_1 = new byte[8][8];
    byte[][] test_field_2 = new byte[8][8];
    byte[][] test_field_3 = new byte[8][8];
    byte[][] test_field_4 = new byte[8][8];
    byte[][] test_field_5 = new byte[8][8];
    byte[] place_of_chop_1 = new byte[2] ;
    byte[] place_of_chop_2 = new byte[2] ;
    byte[] place_of_chop_3 = new byte[2] ;
    byte[] place_of_chop_4 = new byte[2] ;
    byte[] place_of_chop_5 = new byte[2] ;
    byte[] place_of_checker_1 = new byte[2] ;
    byte[] place_of_checker_2 = new byte[2] ;
    byte[] place_of_checker_3 = new byte[2] ;
    byte[] place_of_checker_4 = new byte[2] ;
    byte[] place_of_checker_5 = new byte[2] ;
    boolean [] result =  new boolean[5];
    public boolean Test (Checker checker, byte[][] field, byte[] chop,byte[] place_of_checker,boolean result){
        return checker.chop(field,chop) == result;
    }
    //поговорить о ток как будет это выглядеть (выбираем шашку и что дальше)

}
