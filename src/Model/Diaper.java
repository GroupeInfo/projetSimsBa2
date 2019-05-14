package Model;

public class Diaper implements Attachable, Usable{
	private int hygiene = 50;
	private static final int price = 30;
	private Baby b;
	
	 public void use() {
		 b.addHygiene(this.hygiene);
		 
	 }
	 public static int getPrice() {
		return price;
	}

	@Override
	public void assignPlayer(Player p) {
		if(p instanceof Baby) {
			this.b = ((Baby) p);
		}	
	}
	 

}
