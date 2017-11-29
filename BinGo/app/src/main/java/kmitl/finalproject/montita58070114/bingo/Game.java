package kmitl.finalproject.montita58070114.bingo;

import java.util.ArrayList;
import java.util.Random;

public class Game implements IGame {

    private Random randomGenerator = new Random();

    // Game State
    private GameState state = GameState.NOT_PLAYING;
    private ArrayList<BlockNumber> blocks;
    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private int round = 1;

    // Settings
    private int MAX_RAND_NUM = 99;

    public Game(ArrayList<BlockNumber> blocks) {
        this.blocks = blocks;
    }

    @Override
    public void start() {
        this.state = GameState.PLAYING;
        this.round = 1;

        checkNext();
    }

    @Override
    public void reset() {
        this.state = GameState.NOT_PLAYING;
        for(BlockNumber block : blocks) {
            block.setState(BlockState.NOT_MATCH);
        }
        this.randomNumbers.clear();
        this.round = 1;
    }

    private int lastRandNum;

    @Override
    public boolean checkNext() {
        int randNum = randomNumber();
        while(this.randomNumbers.contains(randNum)) {
            randNum = randomNumber();
        }

        lastRandNum = randNum;
        checkBlocks(randNum);
        this.randomNumbers.add(randNum);

        if(checkWin()) {
            return true;
        } else {
            if(this.round == 9) {
                this.state = GameState.LOSE;
                return true;
            }

            this.round++;
            return false;
        }
    }

    @Override
    public int getLastRandNum() {
        return lastRandNum;
    }

    @Override
    public int getRound() {
        return this.round;
    }

    /*
        Win Patterns:
            [x, x, x], [x, o, o], [x, o, o]
            [o, o, o], [x, o, o], [o, x, o]
            [o, o, o], [x, o, o], [o, o, x]
            -------------------------------
            [1, 2, 3], [1, 4, 7], [1, 5, 9]
            ===============================
            [o, o, o], [o, x, o], [o, o, x]
            [x, x, x], [o, x, o], [o, x, o]
            [o, o, o], [o, x, o], [x, o, o]
            -------------------------------
            [4, 5, 6], [2, 5, 8], [3, 5, 7]
            ===============================
            [o, o, o], [o, o, x],
            [o, o, o], [o, o, x],
            [x, x, x], [o, o, x],
            ---------------------
            [7, 8, 9], [3, 6, 9],
            =====================
     */

    private int[][] winPatterns = new int[][] {
            {1, 2, 3}, {1, 4, 7}, {1, 5, 9},
            {4, 5, 6}, {2, 5, 8}, {3, 5, 7},
            {7, 8, 9}, {3, 6, 9}
        };
    private int[] curWinPattern;

    private boolean checkWin() {
        ArrayList<BlockNumber> checkPatternBlocks = new ArrayList<>();
        int match = 0;

        for(int[] pattern : winPatterns) {
            for(int position: pattern) {
                checkPatternBlocks.add(getBlock(position));
            }

            for(BlockNumber checkBlock : checkPatternBlocks) {
                if(checkBlock.getState().equals(BlockState.MATCH)) {
                    match++;
                }
            }

            if(match == 3) {
                this.state = GameState.WIN;
                curWinPattern = pattern;
                return true;
            } else {
                checkPatternBlocks.clear();
                match = 0;
            }
        }

        return false;
    }

    @Override
    public int[] getWinPattern() {
        return curWinPattern;
    }

    private BlockNumber getBlock(int position) {
        for(BlockNumber block : blocks) {
            if(block.getPos() == position) {
                return block;
            }
        }
        return null;
    }

    private void checkBlocks(int randNum) {
        for(BlockNumber block : blocks) {
            if(block.getNumber() == randNum) {
                block.setState(BlockState.MATCH);
                break;
            }
        }
    }

    private int[] LOCKNUMBERFORCHEATING = {66,55,44,33,22,11,99,88,77};

    private int randomNumber() {
        return randomGenerator.nextInt(MAX_RAND_NUM + 1);
//        return LOCKNUMBERFORCHEATING[this.round];
    }

    @Override
    public GameState getState() {
        return this.state;
    }

    @Override
    public ArrayList<Integer> getRandomNumbers() {
        return this.randomNumbers;
    }


}
