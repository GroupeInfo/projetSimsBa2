package Model;
public class Player implements Directable {

    int energy = 100;
    int Satisfaction = 100;
    int direction = EAST;
    int posX;
    int posY;
    int lifes = 100;
    boolean dead = false;
    private boolean SleepingState;
    private boolean isInFarm;
    private Game g;
    protected AttackWeapon currentWeapon = null;
    protected AttackWeapon sword = null;
    private static int FamilyMoney = 250;
    
    public Player(int x, int y, Game g) {
        this.posX = x;
        this.posY = y;
        this.g = g;
        
        AttackWeapon sword = new AttackWeapon(25, 1);
        this.sword = sword;
        
        currentWeapon = this.sword;
    }

    public void move(int X, int Y) {
        this.posX = this.posX + X;
        this.posY = this.posY + Y;
    }

    public void rotate(int x, int y) {
        if(x == 0 && y == -1)
            direction = NORTH;
        else if(x == 0 && y == 1)
            direction = SOUTH;
        else if(x == 1 && y == 0)
            direction = EAST;
        else if(x == -1 && y == 0)
            direction = WEST;
    }
    
    public void removeLifePoints(int lifePointsLess) {
    	lifes = lifes - lifePointsLess;
    	if (lifes <= 0) {
    		lifes = 0;
    		dead = true;
    	}
    }
    
    public void addLifePoints(int lifePointsMore) {
    	lifes = lifes + lifePointsMore;
    	if(lifes >= 100) {
    		lifes = 100;
    	}
    }
    

   ////////////////////////////////////////////////////////////////////////////////////////

    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }
    
    public boolean isObstacle() {
        return true;
    }
    
    public int getPosX() {
        return this.posX;
    }
    
    public void setPosX(int X) {
    	this.posX = X;
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public void setPosY(int Y) {
    	this.posY = Y;
    }
    
    public int getDirection() {
    return direction;
    }
    
    public int getFrontX() {
        int delta = 0;
        //System.out.println(direction); //je voulais bien m'assurer que la direction est changé quand on clique space...
        if (direction % 2 == 0){//si direction est  paire(donc est  east ou west voir Directable interface)
            delta += 1 - direction;
        }
        return this.posX + delta; //retourne la valeur en X de l'obstacle qu'il touche lorsqu'il bouge ouest ou est...
        						//pas besoin de retourne en Y car on l'a connait... c'est la meme que celle du player.......
        						
    }

    public int getFrontY() {
        int delta = 0; 
        if (direction % 2 != 0){//si direction n'est pas paire(donc est nord ou sud voir Directable interface)
            delta += direction - 2;
        }
        return this.posY + delta;
    }
    
    public double getEnergy() {
    	return energy/100.0; //idem read next comment. (si retourne 1 donc on a BAR_Length * 1 = ans donc rectangle complet)
    }
    
    public double getFamilyMoney() {
    	return FamilyMoney;
    }
    
    public double getSatisfaction() {
    	return Satisfaction/100.0; //sera utile pour dessiner le rectangle de Hunger dans GUI
    }
    
	public void tire() {
		if (energy > 10) {
			energy -= 10;
		}
	}
	
	public void beHungry() {
		if(Satisfaction>10) {
			Satisfaction -= 10;
		}
	}
	
	public void addMoney(int ExtraMoney) {
		this.FamilyMoney = this.FamilyMoney + ExtraMoney;
	}
	
	public void addEnergy(int extraEnergy){
		int sum = this.energy + extraEnergy;
		if((sum) <=100) {
			energy += extraEnergy;
		}
		else {
			energy = 100;
		}
	}
	
	 public String getCurrentWeapon() {
	    	if (currentWeapon == sword) {
	    		return "sword";
	    	}
	    	else {
	    		return "gun";
	    	}
	    }
	 
	 
	public int getPlayersWeaponForce() {
    	return currentWeapon.getAttackForce();
    }
    
    public int getPlayersWeaponRange() {
    	return currentWeapon.getAttackRange();
    }
    
    public int getLifePoints() {
    	return(lifes);
    }
	public void setSleepingState() {
		SleepingState = true;
	}
	
	public void setAwakeState() {
		SleepingState = false;
	}
	
	public boolean getSleepingState() {
		return SleepingState;
	}
	
	public void setDeath() {
		dead = true;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void addSatisfaction(int ExtraSatisfaction) {
		this.Satisfaction = this.Satisfaction + ExtraSatisfaction;
	}

	public void setIsInFarm() {
		isInFarm = true;	
	}
	
	public void setIsNotInFarm(){
		isInFarm = false;
	}

	public boolean isInFarmState() {
		return isInFarm;
	}
	
}
