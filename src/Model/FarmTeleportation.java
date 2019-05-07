package Model;

public class FarmTeleportation  {
	private Player player = null;
	private int posX;
	private int posY;
	
	public FarmTeleportation(int x, int y, Player player) {
		posX = x;
		posY = y;
		this.player = player;
	}
	
	  public boolean isAtPosition(int x, int y) {
	        return posX == x && posY == y;
	    }
	
}