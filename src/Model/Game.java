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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Game implements DeletableObserver, Sounds{
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Attachable> inventory = new ArrayList<Attachable>();
    private ArrayList<Player> SleepingObject = new ArrayList<Player>();
    private int level = 0;
    private Window window;
    private int[][] GraphicalArray;
    private FarmTeleportation teleport1;
    private static  int houseSize;
    private  int outsideSize;
    public static boolean mapChanger = false;
    public boolean endGame = false;
    public static Player active_player = null;
    public static int numberOfBreakableBlocks = 40;
    
    
    protected ArrayList<FarmGameObjects> farmObjects = new ArrayList<FarmGameObjects>();
    
    public Game(Window window) { 
    	
        this.window = window;

        // Creating one Player at position (10,10)
        Player p = new Player(10, 10, this, "Male");
        Player p2 = new Player(12, 12,this, "Female");
        // apres 10 carreaux !!! ca ne veux pas!! dire à la coordonnées X de java...
        
        players.add(p);
        players.add(p2);
        
        active_player = p;
        window.setPlayer(p); //forGUI
        
        //CreatingFirstChicken
        Chicken chicken = new Chicken(12, 12, this, players);
    	chicken.attachDeletable(this);
    	
    	//GameEssentialObjects
    	Computer c = new Computer(1,13, 2,2,this);
        Bed b = new Bed(3,1,4,4,this);
        Shower s = new Shower(4,22,5,2,this);
        Kitchen k = new Kitchen(19,21,3,3,this);
        Shop shop = new Shop(27,2,1,1,this);
        Door d1 = new Door(12,9,0); Door d2 = new Door(4,9,0); Door d3 = new Door(19,9,0); Door d4 = new Door(4,15,0); Door d5 = new Door(19,15,0);
        
        objects.add(c);
        objects.add(b); 
        objects.add(d1); objects.add(d2) ; objects.add(d3); objects.add(d4); objects.add(d5);
        objects.add(s);
        objects.add(k);
        objects.add(shop);
       
        
        for(int i = 0; i<5; i++) {
        createApples();
        createEnergyCoins();
        }
        
        this.teleport1 = new FarmTeleportation(23,23,active_player);
         
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
        int x[][] = {{9,4},{10,4},{11,4},{11,3},{11,2},{11,1},{13,1},{13,2},{13,3},{13,4},{14,4},{15,4},{19,1},{19,2},{20,1},
        			{20,2},{21,1},{21,2},{18,4},{18,5},{19,4},{19,5},{20,4},{20,5},{21,4},{21,5},{10,16},{11,16},{12,16},
        			{13,16},{14,16},{2,16},{2,17},{3,16},{3,17},{7,18},{7,19},{8,18},{8,19},{4,22},{4,23},{5,22},{5,23},
        			{6,22},{6,23},{7,22},{7,23},{8,22},{8,23},{19,22},{19,23},{20,22},{20,23},{21,22},{21,23},{19,21},{20,21},{21,21}};
        
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
        window.setGuiAttributes(inventory);
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
    	boolean z = this.active_player.getSleepingState();
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
    		EnergyCoin EC = new EnergyCoin(x,y,active_player);
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
    		Apple apple = new Apple(x,y,active_player);
        	this.objects.add(apple);
    	}
    }
}
    public boolean takenPositionHouse(int x, int y) {
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
 	    		Chicken chicken = new Chicken(x, y, this, players);
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
    	try {
    	Attachable inventoryItem = this.inventory.get(position);
    	
    	if(inventoryItem instanceof Activable) {
    		((Activable) inventoryItem).activate();
    		this.inventory.remove(inventoryItem);
    		notifyView();
    		}
    	}
    	catch(Exception e) {
    		System.out.println("SORRY CANT DO THAT");
    	}
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
	 
	        boolean decider = false;
	        for(Player player: players) {
	        	if(player != active_player) {
	        	if(player.getPosX() == nextX && player.getPosY() == nextY) {
	        		decider = true;
	        	}
	        }
	      }
	        
	        if(!this.mapChanger && !decider) {
	        //
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
	        
	        if (teleport1.isAtPosition(nextX, nextY)){
    			EnableFarm();
    			active_player.setIsInFarm();
    			active_player.setPosX(22); active_player.setPosY(22);
    		}
	        
	        //
	        boolean z = onGraphicalObject(nextX, nextY);
	        active_player.rotate(x, y); 
	        if (obstacle == true || z == true) {
	            decider = true;
	        }
	        notifyView();
	        }
	        
	        else if(this.mapChanger  && !decider) {
	        	for (FarmGameObjects farmObject : farmObjects) {
	            	if (farmObject.isAtPosition(nextX, nextY)) {
	            		decider = farmObject.isObstacle();
	            	}
	            
	                if (decider == true) {
	                    break; 
	                }  
	        	}
	        	if (teleport1.isAtPosition(nextX, nextY)){
	    			DisableFarm();
	    			active_player.setIsNotInFarm();
	    			active_player.setPosX(22); active_player.setPosY(22);
	    		}
	        	   
	       }
	        active_player.rotate(x, y); 
	        if (decider == false) {
	            active_player.move(x, y);
	            notifyView(); 
	        		} 
    	  
   }


    
    public int getPlayerX() {
    	return this.active_player.getPosX();
    }
    
    public int getPlayerY() {
    	return this.active_player.getPosY();
    }
    
    public void tirePlayer() {
    	active_player.tire();
    	notifyView();
    }
    
    public void makePlayerHungry() {
    	active_player.beHungry();
    	notifyView();
    }
    
    public void addToInventory(){
    	int X = getPlayerDirectionVector().get(0);
    	int Y = getPlayerDirectionVector().get(1);
    	
    	int FrontX = active_player.getPosX() + X;
    	int FrontY = active_player.getPosY() + Y;
   
    if(mapChanger == false) {
	for (GameObject object : objects) {
        if (object.isAtPosition(FrontX, FrontY)) {
        	if(object instanceof Attachable) {
            	if(inventory.size() < 4) {
            		inventory.add((Attachable) object);
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
	        if(mapChanger == false) {//initianliser aimedobject a null et puis on changera si un peu (dnas le deuxieme if) 
			for(GameObject object : objects){
				
				if(object instanceof OversizedObject) {
					boolean z = ObstacleIsOversizedObject((OversizedObject) object,active_player.getFrontX() , active_player.getFrontY());
					if(z == true) {
						aimedObject = (Activable) object;
					}
				}
				else if(object.isAtPosition(active_player.getFrontX(),active_player.getFrontY())){
				    if(object instanceof Activable){
				        aimedObject = (Activable) object; 
				    }
				}
			}
	     }
	        
	        else {
	        	for(FarmGameObjects farmObject : farmObjects) {
	    			if(farmObject.isAtPosition(active_player.getFrontX(),active_player.getFrontY())) {
	    				if(farmObject instanceof Activable) {
	    					System.out.println("LEK YA AYRE");
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
    
    public static Player getPlayer() {
    	return active_player;
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
				active_player = player;
				window.setPlayer(active_player);
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
        System.out.println(active_player.getPosX() + ":" + active_player.getPosY());
        
    }

	public void stop() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}


	public void sendPlayer(int x, int y) {
		Thread t = new Thread(new AStarThread(this, active_player, x,  y));
		t.start();
	}
	
	public ArrayList<Integer> getPlayerDirectionVector() {
		ArrayList<Integer> arl = new ArrayList<Integer>();		
		int intDirection = this.active_player.getDirection();
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
		players = null;
		Restart restart = new Restart("GameOver");
		endGame = true;
	}
	
	public boolean getEndGame() {
		return endGame;
	}

	public static void setDifficulity(int difficulity) {
		Chicken.setChickenDamage(difficulity);
	}
	
	public void removeMoneyFromPlayer(int Money) {
		active_player.buy(Money);
		notifyView();
		}

	public void setPlayerWeapon() {
		active_player.changeWeapon();
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
			if(active_player == player) {
				int index = players.indexOf(player) + 1;
				if(index != size) {
					active_player = players.get(index);
				}
				else {
					active_player = players.get(0);
				}
				break;
			}
		}
		window.setPlayer(active_player);
		notifyView();
		
	}

	

}