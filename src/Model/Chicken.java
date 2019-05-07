package Model;

import java.util.ArrayList;


//Enemy takes a player as an argument due to the thread containing moveEnemy and enemyAttacksPlayer

public class Chicken extends FarmGameObjects implements Directable, Activable, Deletable, Runnable{
	
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private static int sleeptime = 500;
	private Thread t;
	private Player enemy = null;
	private int lifes = 100;
	private int direction = EAST;
	private Game game = null;
	private AttackWeapon weapon = null;
	private static int damage = 10;
	private boolean Alive = true;
	private int value = 100;
	//private Player player;
	public Chicken(int x, int y, Game game, ArrayList<Player> chickenPlayers) {
		super(x, y, 6, 1, 1);
		this.game = game;
		setChickenPlayers(chickenPlayers);
		
		//Ennemy has a sword as a weapon
		AttackWeapon sword = new AttackWeapon(25, 1, 0);
		weapon = sword;
		
		//Initializing thread
		t = new Thread(this);
		t.start();
		//Adding enemy to "objects"
		this.game.farmObjects.add(this);
		
	}
	
	
	@Override
    public boolean isObstacle() {
		return true;
	}
	
	
	
	public  void run() {
		while (Alive) {
			try {
				if (game.getEndGame() == false) {
					for(Player player : players) {
					
						
						boolean PF = player.isInFarmState() ;
						if(PF) {
							enemy = player;
							break;
						}
						else {
							enemy = null;
						}
						
					}

					if(enemy != null) {
					Thread.sleep(sleeptime);
					enemyAttacksPlayer();
					moveEnemyX();
					Thread.sleep(sleeptime);
					moveEnemyY();
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	//No setLifes() required as the method activates() is taking care of changing the life.
	public int getLifes() {
		return lifes;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
    	this.direction = direction;
    }

	public static int getSleeptime() {
		return sleeptime;
	}
	
	public static void setSleeptime(int sleepTime) {
		sleeptime = sleepTime;
	}
    
    @Override
    public void activate() {
    	//attackForce is negative
    	int attackForce = enemy.getPlayersWeaponForce();
    	if (lifes <= Math.abs(attackForce)) {
    		Alive = false;
    		crush();
    		enemy.addChicken(value);
    		if(remainingEnemies() == false) {
    			game.setLevel();
    			game.createChickens();
    		}
    		}    			
    	else {
    		lifes = lifes + attackForce;
    	}
    }
    
    public boolean remainingEnemies() {
    	boolean z = false;
    	ArrayList<FarmGameObjects> farmGameObjects = game.getFarmGameObjects();
    	for(FarmGameObjects farmObject : farmGameObjects) {
    		if(farmObject instanceof Chicken) {
    			z = true;
    			break;
    		}	
    	}
    	return z;
    }
    
    private void moveEnemyX() {
		int playerX = enemy.getPosX();
		int chickenX = getPosX();
		int dx = Math.abs(playerX - chickenX);
		int nextX;
		int nextY;
		boolean obstacle = false;
		
		if (playerX > chickenX) {
    		nextX = getPosX() +1;
    		nextY = getPosY();
    		
    		for (FarmGameObjects object: game.farmObjects) {
    			if (object.isAtPosition(nextX, nextY)) {
    				obstacle = object.isObstacle();
    			}
    			
    			if (obstacle == true) {
    				break;
    			}
    		}
    		
    		for(Player player : players) {
    			if(player.isAtPosition(nextX, nextY)) {
    				obstacle = player.isObstacle();
    			}
    			
    		}
    		if (obstacle == false) {
	    		setPosX(nextX);
			}
    		setDirection(0);
    	}
		
		else if (playerX < chickenX) {
    		nextX = getPosX() -1;
    		nextY = getPosY();
    		
    		for (FarmGameObjects object: game.farmObjects) {
    			if (object.isAtPosition(nextX, nextY)) {
    				obstacle = object.isObstacle();
    			}
    			
    			if (obstacle == true) {
    				break;
    			}
    		}
    		
    		for(Player player : players) {
    			if(player.isAtPosition(nextX, nextY)) {
    				obstacle = player.isObstacle();
    			}
    			
    		}
    		
    		if (obstacle == false) {
	    		setPosX(nextX);
			}
			setDirection(2);
    	}
		
		game.notifyView();
	}
    
    
	private void moveEnemyY() {
		
    	int playerY = enemy.getPosY();
    	int chickenY = getPosY();
    	int dy = Math.abs(playerY - chickenY);
    	int nextX;
    	int nextY;
    	boolean obstacle = false;
    	
    	if (playerY > chickenY) {
    		nextX = getPosX();
    		nextY = getPosY() + 1;
    		
    		for (FarmGameObjects object: game.farmObjects) {
    			if (object.isAtPosition(nextX, nextY)) {
    				obstacle = object.isObstacle();
    			}
    			
    			if (obstacle == true) {
    				break;
    			}
    		}
    		
    		for(Player player : players) {
    			if(player.isAtPosition(nextX, nextY)) {
    				obstacle = player.isObstacle();
    			}
    			
    		}
    		
    		if (obstacle == false) {
	    		setPosY(nextY);
			}
			setDirection(3);
    	}
    	
    	else if (playerY < chickenY) {
    		nextX = getPosX();
    		nextY = getPosY() -1;
    		
    		for (FarmGameObjects object: game.farmObjects) {
    			if (object.isAtPosition(nextX, nextY)) {
    				obstacle = object.isObstacle();
    			}
    			
    			if (obstacle == true) {
    				break;
    			}
    		}
    		
    		for(Player player : players) {
    			if(player.isAtPosition(nextX, nextY)) {
    				obstacle = player.isObstacle();
    			}
    			
    		}
    		if (obstacle == false) {
	    		setPosY(nextY);
			}
			setDirection(1);
    	}
    	game.notifyView();
    }
	
	
	
	private void enemyAttacksPlayer() {
		int dx = Math.abs(enemy.getPosX()- getPosX());
		int dy = Math.abs(enemy.getPosY()- getPosY());
		if ((dx == 0 && dy == 1) || (dy == 0 && dx == 1 )) {
			if(enemy.getLifePoints() < damage) {
				players.remove(enemy);
				game.killPlayer(enemy);
				if(players.size() == 0) {
					game.setEndGame();
				}
			}
			enemy.removeLifePoints(damage);
			
		}
		

	}
	
    
    
	public void crush() {
		notifyDeletableObserver();
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		observers.add(po);
	}


	public void notifyDeletableObserver() {
		for (DeletableObserver object: observers) {
			object.delete(this, null);
		}
	}
	
	public void setChickenPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public int getLifePoints() {
		return lifes;
	}
	
	public static void setChickenDamage(int difficulity) {
		damage = difficulity; 
	}
	
	public AttackWeapon getChickenAttackWeapon() {
		return weapon;
	}
	
}