package Model;

public class Door extends GameObject implements Activable {
	private boolean opened;
	private Player p;
	
	public Door(int X, int Y, int color) {
		super(X, Y, color, 0, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate() {
		if(this.opened  = false) {
			opened = true;
		}
		
		else {
			opened = false;
		}
		
	}

	@Override
	public boolean isObstacle() {
		boolean z;
		// TODO Auto-generated method stub
		if(this.opened == true) {
			z = false;
		}
		else {
			z = true;
		}
		return z;
	}

}
