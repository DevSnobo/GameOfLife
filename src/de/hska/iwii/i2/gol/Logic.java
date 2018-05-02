package de.hska.iwii.i2.gol;

public interface Logic {
    void setStartGeneration(boolean[][] generation);
    void nextGeneration();
    boolean isCellAlive(int x, int y);
}
