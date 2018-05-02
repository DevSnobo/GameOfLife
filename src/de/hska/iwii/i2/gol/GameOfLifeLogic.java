package de.hska.iwii.i2.gol;

/**
 * Die eigentliche Spielelogik. Das Spielfeld wird hier nicht
 * als zyklisch geschlossen betrachtet wird.
 *
 * @author Holger Vogelsang
 */
public class GameOfLifeLogic implements Logic {

    private boolean[][] currentGeneration;

    /**
     * Setzt die Startgeneration.
     *
     * @param generation übergebene Startgeneration
     */
    @Override
    public void setStartGeneration(boolean[][] generation) {
        assert generation != null;
        this.currentGeneration = new boolean[generation.length][generation[0].length];

        for (int i = 0; i < generation.length; i++) {
            System.arraycopy(generation[i], 0, this.currentGeneration[i], 0, generation[i].length);
        }
    }

    /**
     * Berechnet die jeweils nächste Generation.
     */
    @Override
    public void nextGeneration() {
        boolean[][] currentEvolution = new boolean[currentGeneration.length][currentGeneration[0].length];

        for (int col = 0; col < currentGeneration.length; col++) {
            for (int row = 0; row < currentGeneration[col].length; row++) {
                currentEvolution[col][row] = evaluateNextState(checkNeighbours(col, row), col, row);
            }
        }
        for (int i = 0; i < currentEvolution.length; i++) {
            System.arraycopy(currentEvolution[i], 0, this.currentGeneration[i], 0, currentEvolution[i].length);
        }
    }

    /**
     * Evaluiert den aktuellen Lebensstatus einer Zelle.
     *
     * @param x X Koordinate im 2D Array
     * @param y Y Koordinate im 2D Array
     * @return Gibt zurück, ob Zelle am Leben ist.
     */
    @Override
    public boolean isCellAlive(int x, int y) {
        return (x >= 0 && x < this.currentGeneration.length && y >= 0 && y < this.currentGeneration[0].length) && this.currentGeneration[x][y];
    }

    private boolean evaluateNextState(int number, int x, int y) {
        if (isCellAlive(x, y)) {
            return number >= 2 && number < 4;
        }
        return (number == 3);
    }

    private int checkNeighbours(int col, int row) {
        int neighbourCounter = 0;

        for (int loopCount = 1; loopCount < 9; loopCount++) {
            switch (loopCount) {
                case 1:
                    neighbourCounter += addOne(col - 1, row - 1);
                    break;
                case 2:
                    neighbourCounter += addOne(col - 1, row);
                    break;
                case 3:
                    neighbourCounter += addOne(col - 1, row + 1);
                    break;
                case 4:
                    neighbourCounter += addOne(col, row + 1);
                    break;
                case 5:
                    neighbourCounter += addOne(col + 1, row + 1);
                    break;
                case 6:
                    neighbourCounter += addOne(col + 1, row);
                    break;
                case 7:
                    neighbourCounter += addOne(col + 1, row - 1);
                    break;
                case 8:
                    neighbourCounter += addOne(col, row - 1);
                    break;
                default:
                    break;
            }
        }
        return neighbourCounter;
    }

    private int addOne(int col, int row) {
        return (isCellAlive(col, row)) ? 1 : 0;
    }
}