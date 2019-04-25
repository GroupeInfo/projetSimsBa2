package Model;

import java.util.ArrayList;

public class EnergyCoin extends GameObject implements Attachable, Activable, Deletable{
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private int energy = 20;
	private Player p;
	public EnergyCoin(int X, int Y, Player player) {
		super(X,Y,6,0,0);
		this.p = player;
		
	}
	 public void activate() {
		 
	 }
	 public boolean isObstacle(){
		 return(true);
	 }
	 
	 public int getExtraEnergy() {
		 return(energy);
	 }
	 
	 public void addEnergyToPlayer() {
		p.addEnergy(this.energy);
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
