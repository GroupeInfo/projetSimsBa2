package Model;

public class EnergyCoin extends GameObject implements Attachable, Usable {
	private int energy = 20;
	private Player p;
	public EnergyCoin(int X, int Y) {
		super(X,Y,6,1,1);
		
	}
	 public void use() {
		 p.addEnergy(this.energy);
		 
	 }
	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraEnergy() {
		 return(energy);
	 }
	@Override
	public void assignPlayer(Player p) {
		this.p = p;
		
	}
	 
	 
}
