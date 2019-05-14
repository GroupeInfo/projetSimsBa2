package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Trash extends GameObject implements Usable, Attachable, Deletable {
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private boolean taken = false;
	private int count = 0;
	private final int X10 = 25;
	private final int Y10 = 10;
	private final int money = 20;
	private Game g;
	private Player p;
	
	public Trash(int X, int Y, int color, int width, int height, Game g) {
		super(X, Y, color, width, height);
		this.g = g;
		deleteIfUntaken();
	}


	@Override
	public void use() {
		int X1 = p.getFrontX();
		int Y1 = p.getFrontY();
		if(X1 == X10 && Y1 == Y10){
			p.addMoney(money);
		}
	}
	
	public void deleteIfUntaken() {
		 Timer timer = new Timer();
		 TimerTask task = new TimerTask() {
				public void run() {
					count++;
					if(count ==  20 || taken == true) {
						timer.cancel();
						count = 0;
						notifyDeletableObserver();
						createNewTrash();
					}
					
				}
				
			      };
			timer.schedule(task,0,1000);  
	}
	@Override
	public boolean isObstacle() {
		return true;
	}


	public void attachDeletable(DeletableObserver po) {
		observers.add(po);
	}


	public void notifyDeletableObserver() {
		for (DeletableObserver object: observers) {
			object.delete(this, null);
		}
	}
	
	
	private void createNewTrash() {
		 Timer timer1 = new Timer();
		 TimerTask task1 = new TimerTask() {
				public void run() {
					count++;
					if(count ==  60) {
						timer1.cancel();
						count = 0;
						taken = false;
						addtoGame();
						deleteIfUntaken();
					}
					
				}

				
			      };
			timer1.schedule(task1,0,1000);  
		
	}


	@Override
	public void assignPlayer(Player p) {
		this.p = p;
		taken = true;
		
	}
	
	private void addtoGame() {
		g.addObject(this);
		
	}

	public int getLightingCoordinateX() {
		return X10;
	}
	public int getLightingCoordinateY() {
		return Y10;
	}
	
	
}
