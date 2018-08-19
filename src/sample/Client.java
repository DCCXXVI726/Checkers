package sample;

public class Client extends Thread{
    /**
     * Method that connects to the server
     * @return 0 if everything is fine and 1 otherwise
     */
    public int connect(){
        return 0;
    }
    /**
     * Method that receive the field from the server
     * @return 0 if everything is fine and 1 otherwise
     */
    public int receiveField(){
        return 0;
    }
    /**
     * Method that send start and final positions
     * of a movement that user wants to perform
     * @return 0 if everything is fine and 1 otherwise
     */
    public int sendPositions(){
        return 0;
    }
}