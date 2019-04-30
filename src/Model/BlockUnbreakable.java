package Model;
public class BlockUnbreakable extends GameObject {

    public BlockUnbreakable(int X, int Y) {
        super(X, Y, 0, 0, 0);
    }

    @Override
    public boolean isObstacle() {
        return true;
    }
}
