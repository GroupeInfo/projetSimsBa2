package Model;

import java.util.ArrayList;

public class Herb extends GameObject implements Activable, Deletable{
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private int HerbLife = 50;
	private Player p;
	private int money = 30;
	public Herb(int X, int Y, Player player) {
		super(X,Y,6,0,0);
		this.p = player;
		
	}
	 public void activate() {
		 
	 }
	 public boolean isObstacle(){
		 return(false);
	 }
	 
	 public int getMoney() {
		 return(money);
	 }
	 
	 public void addMoneyToFamily() {
		p.addMoney(this.money);
	 }
	 
	 public void notifyDeletableObserver() {
	        int i = 0;
	        for (DeletableObserver o : observers) {
	            i++;
	            o.delete(this, null);
	        }
	 }
	 public void attachDeletable(DeletableObserver pooo) {
		 observers.add(pooo);
		 
	 }
}
