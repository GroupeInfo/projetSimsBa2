package Model;

public class FarmTeleportation {
	private int posX;
	private int posY;
	
	public FarmTeleportation(int x, int y, Player player) {
		posX = x;
		posY = y;
		}
	
	public boolean isAtPosition(int x, int y) {
	        return posX == x && posY == y;
	    }
	  
	public int getPosX() {
			return posX;
		}

	public int getPosY() {
			return posY;
		}
	
}