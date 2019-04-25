package Model;
import View.Window;
import View.Map;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import Model.Herb;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.omg.CosNaming.IstringHelper;

import Controller.Keyboard;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Attachable> inventory = new ArrayList<Attachable>();
    private ArrayList<Player> SleepingObject = new ArrayList<Player>();
    
    private Player active_player = null;

    private Window window;
    public static int houseSize = 10;
    public static int outsideSize;
    // private int bombTimer = 3000;
    public static int numberOfBreakableBlocks = 40;
    public Game(Window window, int level) {
    	//Keyboard keyboard = new Keyboard(this);
    	//window.setKeyListener(keyboard);
        this.window = window;
        houseSize = window.getHouseSize();
        outsideSize = window.getOutsideSizeX();
        // Creating one Player at position (1,1)
        Player p = new Player(10, 10, 3);
        // apres 10 carreaux !!! ca ne veux pas!! dire à la coordonnées X de java...
        objects.add(p);
        players.add(p);
        window.setPlayer(p);
        active_player = p;
        
        Bed b = new Bed(1,1,p,3,3, this);
        objects.add(b); 
        
        for(int i = 0; i<5; i++) {
        createEnergyCoins();
        createApples();
        }
       
        
 
       // Map building
        
        //DrawingexternalWalls
        for (int i = 0; i < houseSize; i++) { //size == 25 ici
            objects.add(new BlockUnbreakable(i, 0));
            objects.add(new BlockUnbreakable(i, houseSize - 1));
            objects.add(new BlockUnbreakable(0, i));
            if(i != 11 & i!=12) {
            	
            	objects.add(new BlockUnbreakable(houseSize - 1, i));
            }
            
            
       
        //DrawinGarden
            
      /* for(int i1 = 25; i1 <38; i1++) {
        	objects.add(new Herb(i1,0,active_player));
        	objects.add(new Herb(i1, houseSize-1,active_player));
        	objects.add(new Herb(0,i1,active_player));
        	objects.add(new Herb(houseSize-1, i1, active_player));
        }
       */
            
        //DrawingInternalUpperLongWall
        int j = 1;
        while( j <houseSize -1) {
        	if(j != 4 & j!=19 & j!= 12) {
        	objects.add(new BlockUnbreakable(j,9));
        	}
        	j++;
        	
        }
        
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
        /*Random rand = new Random();
        for (int i1 = 0; i1 < numberOfBreakableBlocks; i1++) {
            int x = rand.nextInt(houseSize-4) + 2; //maximum il le met a 22 en x(donc 3 carreaux avant la fin)
            int y = rand.nextInt(houseSize-4) + 2;
            int lifepoints = rand.nextInt(5) + 1; //donc lifepointsMax = 5
            BlockBreakable block = new BlockBreakable(x, y, lifepoints);
            block.attachDeletable(this);
            objects.add(block);
        }*/
        
       
        
        window.setGameObjects(this.getGameObjects());
        window.setGuiAttributes(this.getGameObjects(), this.inventory);
        notifyView();
        }
    }
    
   /* public boolean ObstacleIsBed(Bed b, int x, int y) {
    	boolean z = false;
    	int k1 = b.getPosX(); 
    	int k2  =  b.getPosY() ;
    	int k3 =  b.getWidthRatio() ; 
    	int k4 =  b.getHeightRatio();
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
  }*/
    
    public boolean ObstacleIsBed(Bed b , int x, int y) {
    	boolean z = b.IsInBedSpace(x, y);
    	return z;
    }
    
    public boolean SleepingStateOfPlayer() {
    	boolean z = this.active_player.isSleeping();
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
          	 if(x == object.getPosX() && y == object.getPosY()){
        		z = true;
        	}	
        }
        if(z == false) {
        	Apple app = new Apple(x,y,active_player);
        	this.objects.add(app);
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
    
   /* public void useInventory(int position) {
    	try {
	    	GameObject object = inventory.get(position);
	    	
	    	if (object instanceof MagicPotionLife) {
	    		((MagicPotionLife) object).addLifeToPlayer();
	    	}
	    	else if (object instanceof MagicPotionSpeed) {
	    		((MagicPotionSpeed) object).changeSpeed();
	    	}
			inventory.remove(object);
    	} catch (Exception e) {
    		System.out.print("Index out of range");
    	}
    }*/
    
    
    
    public void movePlayer(int x, int y) {
        int nextX = active_player.getPosX() + x;
        int nextY = active_player.getPosY() + y;

        boolean obstacle = false;
        for (GameObject object : objects) {
        	if (object instanceof Bed) {
        		if(((Bed) object).IsInBedSpace(nextX, nextY) == true){
        			obstacle = object.isObstacle();
        		}
        	}
        	else if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();    	
            }
            if (obstacle == true) {
                break; //casse la boucle for car on a donc  trouvé un obstacle on peut donc plus jamais faire le pas souhaité ...
                	//sert a rien de continuer a cherche des objets ayant le meme x,y pour voir si ils sont des obstacles ...
            	
            }
            
           /* if(object instanceof EnergyCoin) {
            	((EnergyCoin) object).addEnergyToPlayer();
            	objects.remove(object);
            	notifyView();
            	break;
            }*/
            
        }
        
        active_player.rotate(x, y); //x et y sont les memes dans rotate et move donc rotate va donner la direction vers laquelle  le player se dirige
        //on change ici l'état de la direction dans la classe player...
        if (obstacle == false) {
            active_player.move(x, y);
        }      
        notifyView(); //refresh car j'ai changer la position donc faut réactualiser le jeu...
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

	for (GameObject object : objects) {
        if (object.isAtPosition(FrontX, FrontY)) {
        	if(object instanceof Attachable) {
            	if(inventory.size() < 4) {
            		inventory.add((Attachable) object);
            		objects.remove(object);
            		notifyView();
                	break; }
            	}
        	}
        }
                	
   }      
    
    public void action() {
        Activable aimedObject = null;//initianliser aimedobject a null et puis on changera si un peu (dnas le deuxieme if) 
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
		if(aimedObject != null){ //sera utile pour les étoiles, trucs qui peuvent etre activés et donc ramasser etc...(pas!! des blocks par exemple)
		    aimedObject.activate();
            notifyView();
		}
		
		//space + fleche permet de tuer les blocks, space toute seule ne fait rien, donc en fonction de la direction
		//dans laquelle pointe  le player(frontX et frontY) on va tuer ce qui est devant....
		//si il y a rien devant le premier if n'est jamais satisfait est le player va simplement bouger dans la direction
		//puisque comme j'ai dis, il faut cliquer fleche +space ....
        
    }

    private void notifyView() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() { //retourne l'array contenant tous les objets
        return this.objects;
    }
    
    public ArrayList<Attachable> getinventory(){
    	return this.inventory;
    }
    
    public void addPlayerToObjects(Player p) {
    	this.objects.add(p);
    	notifyView();
    }
    
    public void removePlayerFromObjects(Player p) {
    	this.objects.remove(p);
    	notifyView();
    }
    
    
    public void addPlayerToSleepingObjects(Player p) {
    	this.SleepingObject.add(p);
    }
    
    public void removePlayerFromSleepingObjects(Player p) {
    	this.SleepingObject.remove(p);
    }
    
    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
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


}