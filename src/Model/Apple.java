package Model;


public class Apple extends GameObject implements Attachable, Usable{
	private int AppleEnergy = 10;
	private int AppleSatisfaction = 10;
	private Player p;
	
	public Apple(int X, int Y) {
		super(X,Y,6,1,1);
		
	}
	 public void use() {
		 p.addEnergy(AppleEnergy);
		 p.addSatisfaction(AppleSatisfaction);
	 }
	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraEnergy() {
		 return(AppleEnergy);
	 }
	@Override
	public void assignPlayer(Player p) {
		this.p = p;
	}
	 
	 
}



