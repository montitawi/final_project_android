package kmitl.finalproject.montita58070114.bingo.Game;

public class BlockNumber {

    private int number, pos;
    private BlockState state = BlockState.EMPTY;

    public BlockNumber(int pos) {
        this.number = -1;
        this.pos = pos;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.state = BlockState.NOT_MATCH;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(BlockState state) {
        this.state = state;
    }

    public boolean isValidBlock() {
        return this.number >= 0 && this.number <= 99;
    }
}
