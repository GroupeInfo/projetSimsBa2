package Model;
import View.Window;

import View.Map;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import Model.FarmGameObjects;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.omg.CosNaming.IstringHelper;

import Controller.Keyboard;

public class Game implements DeletableObserver{
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    protected ArrayList<FarmGameObjects> farmObjects = new ArrayList<FarmGameObjects>();
    private ArrayList<Player> players = new ArrayList<Player>();
    
    private ArrayList<Attachable> inventory = new ArrayList<Attachable>();
    private ArrayList<Player> SleepingObject = new ArrayList<Player>();
    private FarmTeleportation teleport1;
    public static boolean mapChanger = false;
    public boolean endGame = false;
    private static Player active_player = null;
    private Window window;
    public static int houseSize = 10;
    public static int outsideSize;
    public static int numberOfBreakableBlocks = 40;
    
    private ArrayList<Integer> GraphicalObjectX = new ArrayList<Integer>(); 
    private ArrayList<Integer> GraphicalObjectY = new ArrayList<Integer>(); 
    
    public Game(Window window, /* FarmWindow farmWindow,*/  int level) { 
    	
        this.window = window;
        //this.farmWindow = farmWindow; //A remmettre!!!!!!
        
        houseSize = window.getHouseSize();
        outsideSize = window.getOutsideSizeX();
        // Creating one Player at position (1,1)
        Player p = new Player(10, 10, 3);
        // apres 10 carreaux !!! ca ne veux pas!! dire à la coordonnées X de java...
        
        players.add(p);
        window.setPlayer(p);
        active_player = p;
        Chicken chicken = new Chicken(12, 12, this, players);
    	chicken.attachDeletable(this);
    	
    	farmObjects.add(chicken);
        
    	
    	
        Bed b = new Bed(1,1,p,3,3, this);
        Door d1 = new Door(12,9,0);
        objects.add(b); 
        objects.add(d1);
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
        
        //AddingXcomponentToGraphicalObjects
        GraphicalObjectX.add(15); 
        GraphicalObjectY.add(12);
        
        
        
        //DrawingInternalLowerLongWall
        int k = 1;
        while(k<houseSize -1) {
        	if(k<10 & k !=4) {
        		objects.add(new BlockUnbreakable(k,15));
        	}
        	else if (k>= 10 & k!= 14) {
        		objects.add(new BlockUnbreakable(k+5,15));
        	}
        	k++;
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
       
        
        window.setGameObjects(this.getGameObjects());
        window.setPlayers(this.getPlayers());
        window.setFarmGameObjects(this.getFarmGameObjects());
        window.setGuiAttributes(this.getGameObjects(), this.inventory, this.players);
        notifyView();
        }
    //}
   
    
    public boolean ObstacleIsBed(Bed b , int x, int y) {
    	boolean z = b.IsInBedSpace(x, y);
    	return z;
    }
    
    public boolean SleepingStateOfPlayer() {
    	boolean z = this.active_player.getSleepingState();
    	return z;
    	 }
    	 
    public void createEnergyCoins() {
    	Random rand = new Random();
    	int x = rand.nextInt(houseSize-1);
    	int y = rand.nextInt(houseSize-1);
    	ArrayList<GameObject> CurrentObjects = getGameObjects();
        boolean z = false;
        
        for(GameObject object: CurrentObjects) {
        	if(object instanceof Bed) {
          		Bed b = ((Bed) object);
        		z = ObstacleIsBed(b, x, y);
        
        }
        	
        	else if(x == object.getPosX() && y == object.getPosY()){
        		z = true;
        	}	
        }
        if(z==false) {
        	EnergyCoin EC = new EnergyCoin(x,y,active_player);
        	this.objects.add(EC);
        			}
        		
       }

    public void createApples() {
    	Random rand = new Random();
    	int x = rand.nextInt(houseSize-1);
    	int y = rand.nextInt(houseSize-1);
    	ArrayList<GameObject> CurrentObjects = getGameObjects();
        boolean z = false;
        
        for(GameObject object: CurrentObjects) {
        	if(object instanceof Bed) {
          		Bed b = ((Bed) object);
        		z = ObstacleIsBed(b, x, y);
        
        }
        	
        	else if(x == object.getPosX() && y == object.getPosY()){
        		z = true;
        	}	
        }
        if(z==false) {
        	Apple apple = new Apple(x,y,active_player);
        	this.objects.add(apple);
        			}
        		
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
    
    public boolean onGraphicalObject(int X, int Y){
    	boolean z = false;
    	for(Integer integer : this.GraphicalObjectX) {
    		if (integer == X) {
    			for(Integer integer1 : this.GraphicalObjectY) {
    				if(integer1 == Y ) {
    					z = true;
    				}
    			}
    		}
    	}
    	return z;
    	
    }
 
    public void movePlayer(int x, int y) {
        int nextX = active_player.getPosX() + x;
        int nextY = active_player.getPosY() + y;
 
        boolean obstacle = false;
        if(this.mapChanger == false) {
        for (GameObject object : objects) {
        	if (object instanceof Bed) {
        		if(((Bed) object).IsInBedSpace(nextX, nextY) == true){
        			obstacle = object.isObstacle();
        		}
        	}
        	
        	else if (teleport1.isAtPosition(nextX, nextY)) {
    			EnableFarm();
    			active_player.setIsInFarm();
    			active_player.setPosX(22); active_player.setPosY(22);
        		}
        	
        	
        	else if (object.isAtPosition(nextX, nextY)) {
                 obstacle = object.isObstacle();    	
        		}
            
            if (obstacle == true) {
                break; 
            }  
        }
        
        boolean z = onGraphicalObject(nextX, nextY);
        active_player.rotate(x, y); 
        if (obstacle == false && z == false) {
            active_player.move(x, y);
    }
        notifyView();
        }
        
        else {
        	for (FarmGameObjects farmObject : farmObjects) {
            	if (farmObject.isAtPosition(nextX, nextY)) {
            		obstacle = farmObject.isObstacle();
            	}
            
                if (obstacle == true) {
                    break; 
                }  
        	}
        	if (teleport1.isAtPosition(nextX, nextY)){
    			DisableFarm();
    			active_player.setIsNotInFarm();
    			active_player.setPosX(22); active_player.setPosY(22);
    		}
        	active_player.rotate(x, y); 
            if (obstacle == false) {
                active_player.move(x, y);
                notifyView(); 
            		}      
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
    
    public void hurtPlayer(int difficulity) {
    	active_player.removeLifePoints(difficulity);
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
            		//PlaySound();
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
			
			if(object instanceof Bed) {
				boolean z = ObstacleIsBed((Bed) object,active_player.getFrontX() , active_player.getFrontY());
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


	public void addPlayerToPlayers(Player p) {
		this.players.add(p);
		notifyView();
		
	}


	public void removePlayerFromPlayers(Player p) {
		this.players.remove(p);
		notifyView();
		
	}


	public void setEndGame() {
		endGame = true;	
	}
	
	public boolean getEndGame() {
		return endGame;
	}


	public int getPlayersWeaponForce() {
		return(this.active_player.getPlayersWeaponForce());
	}
	
	/*public static void PlaySound() {
		File zipper = new file("resources/Sounds/zipper.wav");
		
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(zipper));
		}
		
		catch(Exception e) {
			
		}
	}*/

}