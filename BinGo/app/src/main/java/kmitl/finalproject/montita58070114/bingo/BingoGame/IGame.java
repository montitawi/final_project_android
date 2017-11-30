package kmitl.finalproject.montita58070114.bingo.BingoGame;

import java.util.ArrayList;

public interface IGame {

    void start();
    void reset();
    boolean checkNext();
    int getRound();
    int[] getWinPattern();
    int getLastRandNum();
    ArrayList<Integer> getRandomNumbers();
    GameState getState();

}
