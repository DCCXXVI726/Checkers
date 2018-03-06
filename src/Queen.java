public class Queen {
    public byte color;
    public byte[] position;

    Queen(byte[] position, byte color) {
        this.position = position;
        this.color = color;
    }

    public void chop(byte[][] field, byte[] placeOfChop, byte[] finalPosition) {

        int xStep = (finalPosition[0] - position[0]);
        xStep = xStep / Math.abs(xStep);
        int yStep = (finalPosition[1] - position[1]);
        yStep = yStep / Math.abs(yStep);

        int numOfVictims = 0;

        for (int i = 0; i < finalPosition[0]; i += xStep) {
            for (int k = 0; k < finalPosition[1]; k += yStep) {
                if (field[i][k] == color * (-1)) numOfVictims++;
            }
        }

        if (numOfVictims > 1) return;
        else if (numOfVictims == 1 && field[finalPosition[0]][finalPosition[1]] == 0 && field[placeOfChop[0]][placeOfChop[1]] == color * (-1)){
            field[placeOfChop[0]][placeOfChop[1]] = 0;
            field[finalPosition[0]][finalPosition[1]] = color;
            field[position[0]][position[1]] = 0;
        }
    }
}
