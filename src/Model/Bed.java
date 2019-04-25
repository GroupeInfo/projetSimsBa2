package Model;

import java.util.ArrayList;
import View.Map;
import java.util.Timer;
import java.util.TimerTask;
public class Bed extends GameObject implements Attachable, Activable{
	private int energy = 40;
	private Player p;
	private Game g;
	private static int count = 0;
	private static int count1 = 0;
	public Bed(int X, int Y, Player player, int widthIntRatio, int heightIntRatio, Game g) {
		super(X,Y,6, widthIntRatio, heightIntRatio);
		this.p = player;
		this.g  = g;
		
	}
	
	 public void activate() {
		 
		 Timer timer = new Timer();
		 TimerTask task = new TimerTask() {
				public void run() {
				if(count1 >= 1) {
				timer.cancel();
				StartAndEnd();}
				g.removePlayerFromObjects(p);
				g.addPlayerToSleepingObjects(p);
				p.setSleepingState();
				count1++;
				
			}
				
			      };
			timer.schedule(task,0,1000);  
			
		}
	 
	 public int getBedEnergy() {
		 return(this.energy);
	 }
	 
	 public void StartAndEnd() {
		 Timer destimer = new Timer();
		 TimerTask desTask = new TimerTask() {
			 
			 public void run() {
				 if(count >=5) {
					 p.setAwakeState();
					 p.addEnergy(getBedEnergy());
					 g.removePlayerFromSleepingObjects(p);
					 g.addPlayerToObjects(p);
					 destimer.cancel();
					 count = 0;
				 }
				 else {
					 count++;	 
				 }
			 }
		 };
		 destimer.schedule(desTask,0,1000);
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
	 
	 public boolean IsInBedSpace(int x, int y) {
	    	boolean z = false;
	    	int k1 = this.getPosX(); 
	    	int k2  = this.getPosY() ;
	    	int k3 = this.getWidthRatio() ; 
	    	int k4 =  this.getHeightRatio();
	    	for(int i = k1; i < k1+k3 ;i++) {
				for(int j = k2; j<k2+k4; j++){
					int x1 = i;
					int y1 = j;
				if(x == x1 && y == y1){
					z = true;
				}
			}
		}
	    	return z;
	  }
}
