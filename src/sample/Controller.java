package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private Game game;
    public Canvas graphicField;
    public Button startButton;
    public Label initialCoords;
    public Label finalCoords;
    public Label currentMove;
    public TextArea gameConsole;
    public ComboBox<String> regimeComboBox;
    public TextArea ipServerTextArea;

    private int sideWidth;
    private int diameter;
    private byte initialX;
    private byte initialY;
    private byte finalX;
    private byte finalY;
    Client client;

    @FXML
    public void initialize() {
        regimeComboBox.getItems().addAll("SinglePlayer", "MultiPlayer");
        printToConsole("Choose the regime");
        startButton.setDisable(true);
        gameConsole.setEditable(false);
        ipServerTextArea.setDisable(true);
    }

    public void regimeChoosed(ActionEvent event) {
        startButton.setDisable(false);
        if (regimeComboBox.getValue().equals("MultiPlayer"))
            ipServerTextArea.setDisable(false);
        else
            ipServerTextArea.setDisable(true);
    }

    public void mouseClicked(MouseEvent event) {
        initialX = (byte) (event.getX() / sideWidth);
        initialY = (byte) (event.getY() / sideWidth);
    }

    public void mouseReleased(MouseEvent event) {
        finalX = (byte) (event.getX() / sideWidth);
        finalY = (byte) (event.getY() / sideWidth);
        processTheMovement();
    }

    private void processTheMovement() {
        byte startPos[] = {initialY, initialX};
        byte finalPos[] = {finalY, finalX};
        if (regimeComboBox.getValue().equals("SinglePlayer")) {
            game.handler(startPos, finalPos);
            printField(game.field);
            if(game.endGame()){
                //добавить конец игры
            }
            if (game.turn == 1) currentMove.setText("White turn");
            else currentMove.setText("Black turn");
        } else {
            client.sendPositions(startPos, finalPos);
        }
    }

    private void printStar(double centerx, double centery, double height, GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        double x[] = {centerx - 0.2974 * height,
                centerx + 0.2359 * height,
                centerx + 0.4077 * height,
                centerx + 0.1077 * height,
                centerx - 0.0026 * height,
                centerx - 0.1564 * height,
                centerx - 0.4308 * height};
        double y[] = {centery + 0.4892 * height,
                centery + 0.4892 * height,
                centery - 0.4993 * height,
                centery - 0.08 * height,
                centery - 0.4993 * height,
                centery - 0.0493 * height,
                centery - 0.4993 * height};
        gc.strokePolygon(x, y, x.length);
    }

    private void printToConsole(String str) {
        gameConsole.appendText(str + "\n");
    }

    public void printField(byte[][] field) {
        GraphicsContext gc = graphicField.getGraphicsContext2D();
        sideWidth = (int) gc.getCanvas().getHeight() / 8;
        diameter = (int) (sideWidth * 0.9);
        for (int height = 0; height < 8; height++) {
            for (int width = 0; width < 8; width++) {

                /*Rectangles mapping*/
                if ((height + width) % 2 != 0) gc.setFill(Color.BROWN);
                else gc.setFill(Color.WHITE);
                gc.fillRect(width * sideWidth, height * sideWidth, sideWidth, sideWidth);

                /*Checker mapping*/
                if (field[height][width] < 0) gc.setFill(Color.BLACK);
                else if (field[height][width] == 0) gc.setFill(Color.TRANSPARENT);
                else gc.setFill(Color.GRAY);
                gc.fillOval(width * sideWidth + (sideWidth - diameter) / 2, height * sideWidth + (sideWidth - diameter) / 2, diameter, diameter);

                /**/
                if (Math.abs(field[height][width]) > 1)
                    printStar(width * sideWidth + sideWidth / 2.0, height * sideWidth + sideWidth / 2.0, sideWidth / 2.0, gc);
            }
        }

    }

    public void start(MouseEvent event) {
        if (regimeComboBox.getValue().equals("SinglePlayer")) {
            game = new Game();
            printField(game.field);
        } else {
            client = new Client(this);
            String[] ipPort = ipServerTextArea.getText().split(":");
            if (ipPort.length < 2 || client.connect(ipPort[0], Integer.parseInt(ipPort[1])) > 0) {
                printToConsole("Couldn't connect");
                return;
            }
            client.start();
        }
        startButton.setDisable(true);
        ipServerTextArea.setDisable(true);
        regimeComboBox.setDisable(true);
        printToConsole("The game has started");
    }

    private void endGame() {
        printToConsole("The game has finished");
        startButton.setDisable(false);
        ipServerTextArea.setDisable(false);
        regimeComboBox.setDisable(false);
    }
}
