package Model;

import java.util.ArrayList;

public class Apple extends GameObject implements Attachable, Activable, Deletable{
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private int AppleEnergy = 10;
	private int AppleSatisfaction = 10;
	private Player p;
	public Apple(int X, int Y, Player player) {
		super(X,Y,6,1,1);
		this.p = player;
		
	}
	 public void activate() {
		 p.addEnergy(this.AppleEnergy);
		 p.addSatisfaction(this.AppleSatisfaction);
	 }
	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraEnergy() {
		 return(AppleEnergy);
	 }
	 
	 public void notifyDeletableObserver() {
	        int i = 0;
	        for (DeletableObserver o : observers) {
	            i++;
	            o.delete(this, null);
	        }
	 }
	 
	 public void attachDeletable(DeletableObserver poo) {
		 observers.add(poo);
		 
	 }
}



