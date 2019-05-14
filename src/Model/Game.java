package Model;
import View.Restart;

import View.Window;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import Model.FarmGameObjects;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game implements DeletableObserver, Sounds{
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Parent> parents = new ArrayList<Parent>();
    private ArrayList<Attachable> inventory = new ArrayList<Attachable>();
    private ArrayList<Player> SleepingObject = new ArrayList<Player>();
    private int level = 0;
    private Window window;
    private int[][] GraphicalArray;
    private FarmTeleportation teleport1;
    private static  int houseSize;
    private boolean endGame = false;
    private static boolean mapChanger = false;
    private Player active_player = null;    
    private ArrayList<FarmGameObjects> farmObjects = new ArrayList<FarmGameObjects>();
    
    public Game(Window window) { 
    	
        this.window = window;

        // Creating one Player at position (10,10)
        Parent p = new Parent(10, 10, this, "Male");
        Parent p2 = new Parent(12, 12,this, "Female");
        Baby b1 = new Baby(13,13,this,"Male");
        // apres 10 carreaux !!! ca ne veux pas!! dire à la coordonnées X de java...
        
        players.add(p); players.add(p2);  players.add(b1);
        parents.add(p); parents.add(p2);

        
        setActive_player(p);
        window.setPlayer(p); //forGUI
        
        //CreatingFirstChicken
        Chicken chicken = new Chicken(12, 12, this, parents);
    	chicken.attachDeletable(this);
    	
    	//GameEssentialObjects
    	Computer c = new Computer(1,13, 2,2,this);
        Bed b = new Bed(3,1,4,4,this);
        Shower s = new Shower(4,22,5,2,this);
        Kitchen k = new Kitchen(19,21,3,3,this);
        Shop shop = new Shop(27,2,1,1,this);
        MilkContainer m1 = new MilkContainer(1, 10 , 0, 2,2 , this);
        Door d1 = new Door(12,9,0); Door d2 = new Door(4,9,0); Door d3 = new Door(19,9,0); Door d4 = new Door(4,15,0); Door d5 = new Door(19,15,0);
        Trash t = new Trash(22,10,0,1,1, this);
        
        t.attachDeletable(this);
        
        objects.add(c);
        objects.add(b); 
        objects.add(d1); objects.add(d2) ; objects.add(d3); objects.add(d4); objects.add(d5);
        objects.add(s);
        objects.add(k);
        objects.add(shop);
        objects.add(m1);
        objects.add(t);
       
        
        for(int i = 0; i<5; i++) {
        createApples();
        createEnergyCoins();
        }
        
        this.teleport1 = new FarmTeleportation(23,23,getActive_player());
         
       // Map building
        
        
        //DrawingexternalWalls
        for (int i = 0; i < houseSize; i++) { //size == 25 ici
            objects.add(new BlockUnbreakable(i, 0));
            objects.add(new BlockUnbreakable(i, houseSize - 1));
            objects.add(new BlockUnbreakable(0, i));
            if(i != 11 & i!=12) {
            	
            	objects.add(new BlockUnbreakable(houseSize - 1, i));
            }
        }
        
      //Drawing External Walls For Farm  
        for (int i1 = 0; i1 < houseSize; i1++) { //size == 25 ici
        	farmObjects.add(new BlockUnbreakableFarm(i1, 0, 7, 1, 1));
            farmObjects.add(new BlockUnbreakableFarm(i1, houseSize - 1,7, 1, 1));
            farmObjects.add(new BlockUnbreakableFarm(0, i1,7 , 1, 1));
            farmObjects.add(new BlockUnbreakableFarm(houseSize - 1, i1,7, 1, 1));
            if(i1 != 13) {
            farmObjects.add(new BlockUnbreakableFarm(houseSize - 4, i1,7, 1, 1));
            }
        }
            
        //DrawingInternalUpperLongWall
        int j = 1;
        while( j <houseSize -1) {
        	if(j != 4 & j!=19 & j!= 12) {
        	objects.add(new BlockUnbreakable(j,9));
        	}
        	j++;
        	
        }
        
        //AddingGraphicalObjects
        int x[][] = {{9,4},{10,4},{11,4},{11,3},{11,2},{11,1},{13,1},{13,2},{13,3},{13,4},{14,4},{15,4},{19,1},{19,2},{20,1},{20,2},{21,1},
        			{21,2},{18,4},{18,5},{19,4},{19,5},{20,4},{20,5},{21,4},{21,5},{10,16},{11,16},{12,16},{13,16},{14,16},{2,16},{2,17},{3,16},
        			{3,17},{7,18},{7,19},{8,18},{8,19},{4,22},{4,23},{5,22},{5,23},{6,22},{6,23},{7,22},{7,23},{8,22},{8,23},{19,22},{19,23},
        			{20,22},{20,23},{21,22},{21,23},{19,21},{20,21},{21,21},{7,6},{7,7},{7,8},{9,6},{9,7},{9,8},{15,6},{15,7},{15,8},{16,17},
        			{17,17},{18,17},{18,18},{18,19},{17,19},{16,19},{22,16},{22,17},{22,18},{22,19},{23,19},{16,21},{17,21},{18,21},{18,22},{18,23}};
        
        this.GraphicalArray = x;
        

        //DrawingInternalLowerLongWall
        int k1 = 1;
        while(k1<19) {
        	if(k1<10 & k1 !=4) {
        		objects.add(new BlockUnbreakable(k1,15));
        	}
        	else if (k1>= 10 & k1!= 14) {
        		objects.add(new BlockUnbreakable(k1+5,15));
        	}
        	k1++;
        }
        
        for(int m = 8; m > 0; m--) {
        	  objects .add(new BlockUnbreakable(8, m));
          }
        
        for(int m = 15; m < houseSize -1; m++) {
      	  objects.add(new BlockUnbreakable(9, m));
        }
        
        for(int l = 8; l>0; l--) {
      	  objects.add(new BlockUnbreakable(16, l));
        }
        
        for(int l = 15; l<houseSize -1; l++){
      	  objects.add(new BlockUnbreakable(15, l));
        }
       
        
        window.setGameObjects(objects);
        window.setPlayers(players);
        window.setFarmGameObjects(farmObjects);
        notifyView();
        }
    
    public static  void setMapSize(int mapSize) {
   	 houseSize = mapSize;
   		
   	}

   public boolean ObstacleIsOversizedObject(OversizedObject o , int x, int y) {
    	boolean z = o.isInObjectSpace(x, y);
    	return z;
    }
    
    public boolean SleepingStateOfPlayer() {
    	boolean z = this.getActive_player().getSleepingState();
    	return z;
    	 }
    
	
    public void createEnergyCoins() {
    	boolean z = true;
    	while(z) {
    	Random rand = new Random();
        int x = rand.nextInt(houseSize-1);
    	int y = rand.nextInt(houseSize-1);
    	z = takenPositionHouse(x, y); 	
    	if(z == false) {
    		EnergyCoin EC = new EnergyCoin(x,y);
            this.objects.add(EC);
    	}
    }
}
    public void createApples() {
    	boolean z = true;
    	while(z) {
    	Random rand = new Random();
        int x = rand.nextInt(houseSize-1);
    	int y = rand.nextInt(houseSize-1);
    	z = takenPositionHouse(x, y); 	
    	if(z == false) {
    		Apple apple = new Apple(x,y);
        	this.objects.add(apple);
    	}
    }
}
    private boolean takenPositionHouse(int x, int y) {
    	boolean z = false;
    	ArrayList<GameObject> CurrentObjects = getGameObjects();
        
        for(GameObject object: CurrentObjects) {
        	if(object instanceof OversizedObject) {
          		OversizedObject o = ((OversizedObject) object);
        		z = ObstacleIsOversizedObject(o, x, y);
        		if(z) {
        			break;
        		}
        }
        	
        	else if(x == object.getPosX() && y == object.getPosY()){
        		z = true;
        		break;
        	}	
        }
        return(z);
    }
    

  
    
    public void createChickens() {
 	   for(int i = 0; i< level ; i++ ) {
 		   boolean z = true;
 	    	while(z) {
 	    	Random rand = new Random();
 	        int x = rand.nextInt(houseSize-1);
 	    	int y = rand.nextInt(houseSize-1);
 	    	z = takenPositionFarm(x, y); 	
 	    	if(z == false) {
 	    		Chicken chicken = new Chicken(x, y, this, parents);
 	    		chicken.attachDeletable(this);
 	    	}
 	    }
 		   
 	   }
    }
    
    private boolean takenPositionFarm(int x, int y) {
    	boolean z = false;
    	ArrayList<FarmGameObjects> CurrentObjects = getFarmGameObjects();
        
        for(FarmGameObjects object: CurrentObjects) {
        	if(object instanceof OversizedObject) {
          		OversizedObject o = ((OversizedObject) object);
        		z = ObstacleIsOversizedObject(o, x, y);
        		if(z) {
        			break;
        		}
        }
        	
        	else if(x == object.getPosX() && y == object.getPosY()){
        		z = true;
        		break;
        	}	
        }
        return(z);
	}

	public void useInventoryItem(int position) {
    	getActive_player().useInventoryItem(position);
    	notifyView();
    }
    
    public boolean onGraphicalObject(int X, int Y) {
    	boolean z = false;
    	int x[][] = GraphicalArray;
    	for(int row = 0; row<x.length; row++) {
    		if((x[row][0] == X) && (x[row][1] == Y)) {
    			z = true;
    			
    			}
    		}
    	return z;
    	}
    
    int i = 0;
    
    public void movePlayer(int x, int y) {
	        int nextX = active_player.getPosX() + x;
	        int nextY = active_player.getPosY() + y;
	        boolean bool = false;
	        active_player.rotate(x, y);
	        notifyView();
	        
	        if(!this.mapChanger) {
		        teleport(nextX, nextY, true);
		        boolean bool1 = playerIsObstacle(nextX, nextY, true);
		        boolean bool2 = objectIsObstacle(nextX, nextY);
		        boolean bool3 = onGraphicalObject(nextX, nextY);
		        if (bool2||bool3||bool1) {
		            bool = true;
		        }
	        }
	        
	        else {
	        	teleport(nextX, nextY, false);
	        	boolean bool1F = playerIsObstacle(nextX, nextY, false);
	        	boolean bool4 = farmObjectIsObstacle(nextX, nextY);
	        	if (bool1F||bool4) {
		            bool = true;
		        }	   
	       }
	        active_player.rotate(x, y); 
	        if (bool == false) {
	            active_player.move(x, y);
	            notifyView(); 
	        		} 
	        //makePlayerDirty();
	        //makePlayerHungry();
	        //makePlayerTired();
   }

	private void teleport(int nextX, int nextY, boolean b) {
		if (teleport1.isAtPosition(nextX, nextY)){
			if(b == true && !aPlayerInFarm()) {
				EnableFarm();
				active_player.setIsInFarm();
			}
			else {
				DisableFarm();
				active_player.setIsNotInFarm();
			}
			active_player.setPosX(22); active_player.setPosY(22);
		}
	}
	
	public boolean aPlayerInFarm() {
		boolean bool = false;
		for(Player player : players) {
			if(player.isInFarmState()) {
				System.out.println("lalalla");
				bool = true;
				break;
			}
		}
		return bool;
	}

	private boolean farmObjectIsObstacle(int nextX, int nextY) {
    	boolean bool = false;
    	for (FarmGameObjects farmObject : farmObjects) {
        	if (farmObject.isAtPosition(nextX, nextY)) {
        		bool = farmObject.isObstacle();
        	}
        
            if (bool == true) {
                break; 
            }  
    	}
    	
    	return bool;	
	}

	private boolean playerIsObstacle(int nextX, int nextY, boolean b) {
    	boolean bool = false;
        for(Player player: players) {
        	if(player != getActive_player()) {
        		if( (b && !player.isInFarmState()) || (!b && (player.isInFarmState())) ) {	
        			if(player.getPosX() == nextX && player.getPosY() == nextY) {
        				bool = true;
        			}
	        	}
        	}	
        }
		return bool;
	}

	private boolean objectIsObstacle(int nextX, int nextY) {
    	
    	boolean obstacle = false;
        for (GameObject object : objects) {
        	if (object instanceof OversizedObject) {
        		if(((OversizedObject) object).isInObjectSpace(nextX, nextY) == true){
        			obstacle = object.isObstacle();
        		}
        	}
        	
        	else if (object.isAtPosition(nextX, nextY)) {
                 obstacle = object.isObstacle();    	
        		}
            
            if (obstacle == true) {
                break; 
            }  
        }
        
        return obstacle;
		
	}

	public int getPlayerX() {
    	return this.getActive_player().getPosX();
    }
    
    public int getPlayerY() {
    	return this.getActive_player().getPosY();
    }
    
    public void makePlayerTired() {
    	getActive_player().tire();
    	notifyView();
    }
    
    public void makePlayerHungry() {
    	getActive_player().beHungry();
    	notifyView();
    }
    
    public void addToInventory(){
    	int X = getPlayerDirectionVector().get(0);
    	int Y = getPlayerDirectionVector().get(1);
    	
    	int FrontX = getActive_player().getPosX() + X;
    	int FrontY = getActive_player().getPosY() + Y;
   
    if(mapChanger == false) {
		for (GameObject object : objects) {
	        if (object.isAtPosition(FrontX, FrontY)) {
	        	if(object instanceof Attachable) {
	        			if(getActive_player().getinventory().size() < 4) {
		        			((Attachable) object).assignPlayer(getActive_player());
		            		getActive_player().addToInventory((Attachable) object);
		            		playSound("Resources/Sounds/zipper.wav");
		            		objects.remove(object);
		            		notifyView();
	                	break; }
	            	}
	        	}
	        }
   }
   else {
	   //TODO
   }
  }      
    
    public void action() {
	        Activable aimedObject = null;
	        if(mapChanger == false) {
			for(GameObject object : objects){
				
				if(object instanceof OversizedObject) {
					boolean z = ObstacleIsOversizedObject((OversizedObject) object,getActive_player().getFrontX() , getActive_player().getFrontY());
					if(z == true) {
						aimedObject = (Activable) object;
					}
				}
				else if(object.isAtPosition(getActive_player().getFrontX(),getActive_player().getFrontY())){
				    if(object instanceof Activable){
				        aimedObject = (Activable) object; 
				    }
				}
			}
	     }
	        
	        else {
	        	for(FarmGameObjects farmObject : farmObjects) {
	    			if(farmObject.isAtPosition(getActive_player().getFrontX(),getActive_player().getFrontY())) {
	    				if(farmObject instanceof Activable) {
	    					aimedObject = (Activable) farmObject;
	    				}
	    			}
	    		}
	        }
	        
			if(aimedObject != null){ 
			    aimedObject.activate();
	            notifyView();
			}
}
    
    
    
    public void notifyView() {
        window.update();
    }
    
    public void EnableFarm() {
    	mapChanger = true;
    }
    public void DisableFarm() {
    	mapChanger = false;
    }
    public ArrayList<GameObject> getGameObjects() { 
        return this.objects;
    }
    
    public ArrayList<FarmGameObjects> getFarmGameObjects() { 
        return this.farmObjects;
    } 
    
    public ArrayList<Player> getPlayers() { 
        return this.players;
    }
    
    public ArrayList<Attachable> getinventory(){
    	return this.inventory;
    }
    
    public Player getPlayer() {
    	return getActive_player();
    }
    public void addPlayerToSleepingObjects(Player p) {
    	this.SleepingObject.add(p);
    }
    
    public void removePlayerFromSleepingObjects(Player p) {
    	this.SleepingObject.remove(p);
    	notifyView();
    }
    
    public void addPlayerToPlayers(Player p) {
		this.players.add(p);
		notifyView();
		
	}
    
    public void killPlayer(Player p) {
    	players.remove(p);
		for(Player player: players) {
			if(!(player.isDead())){
				setActive_player(player);
				window.setPlayer(getActive_player());
				break;
			}
		}
		mapChanger = false;
		notifyView();
    }
    

	
    
    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
    	if(ps instanceof GameObject) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
    	}
    	else {
    		farmObjects.remove(ps);
    		notifyView();
    	}
    }


    public void playerPos() {
        System.out.println(getActive_player().getPosX() + ":" + getActive_player().getPosY());
        
    }

	public void stop() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}


	
	public ArrayList<Integer> getPlayerDirectionVector() {
		ArrayList<Integer> arl = new ArrayList<Integer>();		
		int intDirection = this.getActive_player().getDirection();
		if(intDirection == 0) {
			arl.add(1);
			arl.add(0);
		}
		else if(intDirection == 1) {
			arl.add(0);
			arl.add(-1);
		}
		else if(intDirection == 2) {
			arl.add(-1);
			arl.add(0);
		}
		else if (intDirection == 3){
			arl.add(0);
			arl.add(1);
		}
		return(arl);
	}

	public void setEndGame() {
		int chickenKilled = Chicken.getChickenKilled();
		Saver saver =  new Saver(chickenKilled);
		players = null;
		window.dispose();
		Restart restart = new Restart();
		endGame = true;
	}
	
	public boolean getEndGame() {
		return endGame;
	}

	public static void setDifficulity(int difficulity) {
		Chicken.setChickenDamage(difficulity);
	}
	
	public void removeMoneyFromPlayer(String buttonx) {
		getActive_player().buy(buttonx);
		notifyView();
		}

	public void setPlayerWeapon() {
		if(getActive_player() instanceof Parent) {
			((Parent) getActive_player()).changeWeapon();
		}
		notifyView();
		}
	
	public void setLevel() {
		level += 1;
	}
	
	public  void playSound(String Path) {
		File voice = new File(Path);
		
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(voice));
			clip.start();
		}
		
		catch(Exception e) {
			
		}
	}

	public void changePlayer() {
		int size = players.size();
		for(Player player : players) {
			if(getActive_player() == player) {
				int index = players.indexOf(player) + 1;
				if(index != size) {
					setActive_player(players.get(index));
				}
				else {
					setActive_player(players.get(0));
				}
				mapChanger = active_player.isInFarmState();
				break;
			}
		}
		window.setPlayer(getActive_player());
		notifyView();
		
	}

	public void setComputerTimer(int waitCount, int difficulity) {
		window.setComputerTimer(waitCount, difficulity);
		notifyView();
		
	}

	public void makePlayerDirty() {
		getActive_player().beDirty();
		notifyView();
		
	}

	public void addObject(GameObject o) {
		objects.add(o);
		notifyView();
		
	}
	
	public static boolean getMapChanger() {
		return mapChanger;
	}

	public Player getActive_player() {
		return active_player;
	}

	public void setActive_player(Player active_player) {
		this.active_player = active_player;
	}


	public void allPlayersEat(int energy, int satisfaction) {
		for(Player player : players) {
			player.addEnergy(energy);
			player.addSatisfaction(satisfaction);
		}
		
	}

	

}