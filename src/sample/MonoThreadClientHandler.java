package sample;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {
    boolean endGame = false;
    MultiThreadServer server;
    DataOutputStream out;
    DataInputStream in;
    private static Socket clientDialog;
    public void send(byte[][] field){
        StringBuffer s = new StringBuffer();
        for (int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                s.append(field[i][j]);
            }
        }
        try{
        out.writeUTF(s.toString());}
        catch (IOException e){

        }

    };

    public MonoThreadClientHandler(Socket client,MultiThreadServer server,int color) {
        MonoThreadClientHandler.clientDialog = client;
        this.server = server;
    }

    @Override
    public void run() {

        try {
            // инициируем каналы общения в сокете, для сервера

            // канал записи в сокет следует инициализировать сначала канал чтения для избежания блокировки выполнения программы на ожидании заголовка в сокете
            out = new DataOutputStream(clientDialog.getOutputStream());

// канал чтения из сокета
            in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("DataInputStream created");

            System.out.println("DataOutputStream  created");
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // начинаем диалог с подключенным клиентом в цикле, пока сокет не
            // закрыт клиентом
            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");

                // серверная нить ждёт в канале чтения (inputstream) получения
                // данных клиента после получения данных считывает их
                String entry = in.readUTF();
                String[] e = entry.split(" ");
                byte[] first_position={Byte.parseByte(e[0]),Byte.parseByte(e[1])};
                byte[] final_position={Byte.parseByte(e[2]),Byte.parseByte(e[3])};
                server.handler(first_position,final_position);
                // инициализация проверки условия продолжения работы с клиентом
                // по этому сокету по кодовому слову - quit в любом регистре
                if (endGame) {

                    // если кодовое слово получено то инициализируется закрытие
                    // серверной нити
                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - " + entry + " - OK");
                    Thread.sleep(3000);
                    break;
                }

            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сокет общения с клиентом в нити моносервера
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
