package Model;

public class FarmTeleportation  {
	private Player player = null;
	private int posX;
	private int posY;
	
	public FarmTeleportation(int x, int y, Player player) {
		this.posX = x;
		this.posY = y;
		this.player = player;
	}
	
	  public boolean isAtPosition(int x, int y) {
	        return this.posX == x && this.posY == y;
	    }
	
}