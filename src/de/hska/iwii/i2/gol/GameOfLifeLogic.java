package de.hska.iwii.i2.gol;

/**
 * Die eigentliche Spielelogik. Das Spielfeld wird hier nicht
 * als zyklisch geschlossen betrachtet wird.
 *
 * @author Holger Vogelsang
 */
public class GameOfLifeLogic {

    private boolean[][] currentGeneration;
    private boolean[][] nextGeneration;


    public void setStartGeneration(boolean[][] generation) {
        this.currentGeneration = new boolean[generation.length][generation[0].length];

        for (int i = 0; i < generation.length; i++) {
            System.arraycopy(generation[i], 0, this.currentGeneration[i], 0, generation[i].length);
        }

        this.nextGeneration = new boolean[currentGeneration.length][currentGeneration[0].length];
    }

    public void nextGeneration() {
        boolean[][] currentEvolution = new boolean[currentGeneration.length][currentGeneration[0].length];

        for (int row = 0; row < currentGeneration.length; row++) {
            for (int col = 0; col < currentGeneration[row].length; col++) {
                currentEvolution[row][col] = evaluateNextState(checkNeighbours(row, col));
            }
        }

        for (int i = 0; i < currentEvolution.length; i++) {
            System.arraycopy(currentEvolution[i], 0, this.nextGeneration[i], 0, currentEvolution[i].length);
        }
    }

    public boolean isCellAlive(int x, int y) {
        return (!(x < 0 || x > this.currentGeneration.length - 1 || y < 0 || y > this.currentGeneration[0].length - 1))
                && this.currentGeneration[x][y];
    }

    private boolean evaluateNextState(int number) {
        return !(number < 2 || number > 4);
    }

    private int checkNeighbours(int row, int col) {
        int neighbourCounter = 0;

        if (!isCellAlive(row, col)) {
            return neighbourCounter;
        }

        for (int loopCount = 1; loopCount < 9; loopCount++) {
            switch (loopCount) {
                case 1:
                    neighbourCounter += addOne(row - 1, col - 1);
                    break;
                case 2:
                    neighbourCounter += addOne(row - 1, col);
                    break;
                case 3:
                    neighbourCounter += addOne(row - 1, col + 1);
                    break;
                case 4:
                    neighbourCounter += addOne(row, col + 1);
                    break;
                case 5:
                    neighbourCounter += addOne(row + 1, col + 1);
                    break;
                case 6:
                    neighbourCounter += addOne(row + 1, col);
                    break;
                case 7:
                    neighbourCounter += addOne(row + 1, col - 1);
                    break;
                case 8:
                    neighbourCounter += addOne(row, col - 1);
                    break;
                default:
                    break;
            }
        }
        return neighbourCounter;
    }

    private int addOne(int row, int col) {
        return (isCellAlive(row, col)) ? 1 : 0;
    }

    public boolean[][] getNextGeneration() {
        return nextGeneration;
    }
}