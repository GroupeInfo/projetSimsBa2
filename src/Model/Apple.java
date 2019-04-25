package Model;

import java.util.ArrayList;

public class Apple extends GameObject implements Attachable, Deletable, Activable{
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private int AppleSatisfaction = 10;
	private int AppleEnergy = 10;
	private Player p;
	public Apple(int X, int Y, Player p) {
		super(X,Y,6,0,0);
	}
	
	public void activate() {
		 addEnergyToPlayer();
		 addSatisfactionToPlayer();
	 }
	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraEnergy() {
		 return(AppleEnergy);
	 }
	 
	 public int getExtraSatisfaction() {
		 return(AppleSatisfaction);
	 }
	 
	 
	 public void addEnergyToPlayer() {
		p.addEnergy(this.AppleEnergy);
	 }
	 
	 public void addSatisfactionToPlayer() {
		 p.addSatisfaction(this.AppleSatisfaction);
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


