package Model;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import View.Buttons;
import View.Window;

//Enemy takes a player as an argument due to the thread containing moveEnemy and enemyAttacksPlayer

public class Chicken extends FarmGameObjects implements Directable, Activable, Deletable, Runnable{
	
	private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private static int sleeptime = 500;
	private Thread t;
	private Player active_player = null;
	private int lifes = 100;
	private int direction = EAST;
	private Game game = null;
	private AttackWeapon weapon = null;
	private static int damage = 10;
	private boolean Alive = true;
	//private Player player;
	public Chicken(int x, int y, Game game, ArrayList<Player> chickenPlayers) {
		super(x, y, 6, 1, 1);
		this.game = game;
		this.setChickenPlayers(chickenPlayers);
		//Ennemy has a sword as a weapon
		AttackWeapon sword = new AttackWeapon(25, 1);
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
							this.active_player = player;
						}
						else {
							this.active_player = null;
						}
						break;
					}

					if(this.active_player != null) {
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
		return this.lifes;
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
    	int attackForce = game.getPlayersWeaponForce();
    	if (lifes <= Math.abs(attackForce)) {
    		crush();
    		Alive = false;
    		}    			
    	else {
    		lifes = lifes + attackForce;
    	}
    }
    
    private void moveEnemyX() {
		int playerX = active_player.getPosX();
		int chickenX = this.getPosX();
		int dx = Math.abs(playerX - chickenX);
		int nextX;
		int nextY;
		boolean obstacle = false;
		
		if (playerX > chickenX) {
    		nextX = this.getPosX() +1;
    		nextY = this.getPosY();
    		
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
	    		this.setPosX(nextX);
			}
    		this.setDirection(0);
    	}
		
		else if (playerX < chickenX) {
    		nextX = this.getPosX() -1;
    		nextY = this.getPosY();
    		
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
	    		this.setPosX(nextX);
			}
			this.setDirection(2);
    	}
		
		game.notifyView();
	}
    
    
	private void moveEnemyY() {
		
    	int playerY = game.getPlayerY();
    	int chickenY = this.getPosY();
    	int dy = Math.abs(playerY - chickenY);
    	int nextX;
    	int nextY;
    	boolean obstacle = false;
    	
    	if (playerY > chickenY) {
    		nextX = this.getPosX();
    		nextY = this.getPosY() + 1;
    		
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
	    		this.setPosY(nextY);
			}
			this.setDirection(3);
    	}
    	
    	else if (playerY < chickenY) {
    		nextX = this.getPosX();
    		nextY = this.getPosY() -1;
    		
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
	    		this.setPosY(nextY);
			}
			this.setDirection(1);
    	}
    	game.notifyView();
    }
	
	
	
	private void enemyAttacksPlayer() {
		int dx = Math.abs(game.getPlayerX()- this.getPosX());
		int dy = Math.abs(game.getPlayerY()- this.getPosY());
		if ((dx == 0 && dy == 1) || (dy == 0 && dx == 1 )) {
			game.hurtPlayer(this.damage);
		}
		else {
		}

	}
	
    
    
	public void crush() {
		
		notifyDeletableObserver();
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		observers.add(po);
	}

	@Override
	public void notifyDeletableObserver() {
		int i = 0;
		for (DeletableObserver object: observers) {
			i++;
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
	
}