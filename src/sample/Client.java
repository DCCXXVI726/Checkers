package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {
    /**
     * Method that connects to the server
     *
     * @return 0 if everything is fine and 1 otherwise
     */
    private DataOutputStream streamToServer;
    private DataInputStream streamFromServer;
    private Socket socket;
    private Controller controller;

    public Client(Controller controller) {
        this.controller = controller;
    }

    public int connect(String address, int port) {
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            socket = new Socket(ipAddress, port);
            streamFromServer = new DataInputStream(socket.getInputStream());
            streamToServer = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ex) {
            return 1;
        }
        return 0;
    }

    /**
     * Method that receive the field from the server
     *
     * @return 0 if everything is fine and 1 otherwise
     */
    public int receiveField() {
        try {
            String field = streamFromServer.readUTF();
            if (field != null && !field.equals("")) {
                byte[][] byteField = new byte[8][8];
                String[] arrayField = field.split(" ");
                for (int i = 0; i < 8; i++) {
                    for (int k = 0; k < 8; k++) {
                        byteField[i][k] = Byte.parseByte(arrayField[i * 8 + k]);
                    }
                }
                controller.printField(byteField, Integer.parseInt(arrayField[64]));
                return 0;
            } else return 1;
        } catch (IOException ex) {
            return 1;
        }
    }

    /**
     * Method that send start and final positions
     * of a movement that user wants to perform
     *
     * @return 0 if everything is fine and 1 otherwise
     */
    public int sendPositions(byte[] initialPos, byte[] finalPos) {
        try {
            String positions = initialPos[0] + " " + initialPos[1] + " " + finalPos[0] + " " + finalPos[1];
            streamToServer.writeUTF(positions);
            return 0;
        } catch (IOException ex) {
            return 1;
        }
    }

    @Override
    public void run()    //Этот метод будет выполнен в побочном потоке
    {
        while (true) {
            if (socket != null) {
                receiveField();
            }
        }
    }
}