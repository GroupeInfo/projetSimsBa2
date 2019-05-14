package Model;

import java.util.ArrayList;

public abstract class Player implements Directable {
    private int energy = 100;
    private int Satisfaction = 100;
    private int hygiene = 100;
    private int direction = EAST;
    private int posX;
    private int posY;
    private int lifes = 100;
    private boolean dead = false;
    private boolean SleepingState;
    private boolean isInFarm = false;
    private Game g;
    private String gender;
    protected static int FamilyMoney = 250;
    private static int FamilyFood = 0;
    protected ArrayList<Attachable> inventory;
    
    
    public Player(int x, int y, Game g, String gender) {
        this.posX = x;
        this.posY = y;
        this.g = g;
        this.gender = gender;
        
        this.inventory = new ArrayList<Attachable>();
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
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return this.posX + delta; 
        						
    }

    public int getFrontY() {
        int delta = 0; 
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return this.posY + delta;
    }
    
    public double getEnergy() {
    	return energy/100.0; 
    }
    
    public double getFamilyMoney() {
    	return FamilyMoney;
    }
    
    public double getSatisfaction() {
    	return Satisfaction/100.0; 
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
	
	public double getHygiene() {
		return hygiene/100.0;
	}
	
	public void setDeath() {
		dead = true;
	}
	
	
	public void addSatisfaction(int ExtraSatisfaction) {
		int sum = this.Satisfaction + ExtraSatisfaction;
		if((sum) <=100) {
			Satisfaction += ExtraSatisfaction;
		}
		else {
			Satisfaction = 100;
		}
	}

	public void buy(String buttonx) {
		 
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

	public void addHygiene(int ExtraHygiene) {
		int sum = this.hygiene + ExtraHygiene;
		if((sum) <=100) {
			hygiene += ExtraHygiene;
		}
		else {
			hygiene = 100;
		}
		
	}

	public void addChicken(int value) {
		this.FamilyFood += value;
	}
	
	public int getFamilyFood() {
		return FamilyFood;
	}

	public boolean isDead() {
		return dead;
	}
	
	public String getGender() {
		return gender;
	}

	public void addToInventory(Attachable object) {
		this.inventory.add(object);
		
	}
	public void useInventoryItem(int position) {
    	try {
    	Attachable inventoryItem = this.inventory.get(position);
    	
    	if(inventoryItem instanceof Usable) {
    		((Usable) inventoryItem).use();
    		this.inventory.remove(inventoryItem);
    		}
    	}
    	catch(Exception e) {
    		System.out.println("SORRY CANT DO THAT");
    	}
    }
	
	public ArrayList<Attachable> getinventory(){
    	return this.inventory;
    }

	public void beDirty() {
		if(hygiene > 1) {
		hygiene -= 1;
		}
		if(hygiene == 1) {
			if(lifes >0) {
				lifes -=1;
			}
		}
	}
	public void tire() {
		if (energy > 1) {
			energy -= 1;
		}
		if(energy == 1) {
			if(lifes >0) {
				lifes -=1;
			}
		}
	}
	
	public void beHungry() {
		if(Satisfaction > 1) {
			Satisfaction -= 1;
		}
		if(Satisfaction == 1) {
			if(lifes >0) {
				lifes -=1;
			}
		}
	}

	public void decreaseFamilyFood() {
		FamilyFood -= 300;
		
	}
	
  } 
	
	
	
