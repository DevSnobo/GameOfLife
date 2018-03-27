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
        this.currentGeneration = generation;
        this.nextGeneration = new boolean[currentGeneration.length][currentGeneration[0].length];
    }

    public void nextGeneration() {
        boolean[][] currentEvolution = new boolean[currentGeneration.length][currentGeneration[0].length];

        for (int row = 0; row < currentGeneration.length; row++) {
            for (int col = 0; col < currentGeneration[row].length - 1; col++) {

                currentEvolution[row][col] = evaluateNextState(checkNeighbours(row, col));

            }
        }

        this.nextGeneration = currentEvolution;
    }

    public boolean isCellAlive(int x, int y) {
        /**try {
            boolean temp = this.currentGeneration[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Error, combination of index x: [" + x + "] and index y: [" + y
                    + "] is not part of the game board!");
        }*/
        return this.currentGeneration[x][y];
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
            try {
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
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Test2");
            }
        }
        return neighbourCounter;
    }

    private int addOne(int row, int col) {
        return (isCellAlive(row, col)) ? 1 : 0;
    }
}