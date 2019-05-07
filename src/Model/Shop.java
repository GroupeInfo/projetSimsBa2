package Model;
import View.ShopMenu;
import Controller.Mouse;

public class Shop extends GameObject implements Activable, Changeable {
	private Game g;
	private boolean used;
	
	
	public Shop(int X, int Y,  int width, int height, Game g) {
		super(X, Y, 0, width, height);
		this.g = g;
		
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isUsed() {
		return used;
	}

	@Override
	public void activate() {
		ShopMenu shop = new ShopMenu(g);
	}

	@Override
	public boolean isObstacle() {
		return true;
	}
	
	
}
