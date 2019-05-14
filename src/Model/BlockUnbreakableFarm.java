package Model;
public class BlockUnbreakableFarm extends FarmGameObjects {

    public BlockUnbreakableFarm(int X, int Y, int color, int width, int height) {
		super(X, Y, color, width, height);
	}

	@Override
    public boolean isObstacle() {
        return true;
    }
}
