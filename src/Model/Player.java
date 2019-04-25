package Model;
public class Player extends GameObject implements Directable {

    int energy = 100;
    int Satisfaction = 100;
    int FamilyMoney = 250;
    int direction = EAST;  
    private boolean SleepingState;
    
    public Player(int x, int y, int maxBomb) {
        super(x, y, 2,0,0);
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

   ////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean isObstacle() {
        return false;
    }
    
    @Override
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
        						//fait un schéma dans ta tete .
        						//rotate et direction et tout ce bazzare nous permet de connaitre les valeurs des objets qui entour le player
        						//rien de plus... on est en 2D rotate ne veut rien dire comme tourner le player car le tourner ne changera rien
        						//car move va le déplacer qu'il regarde a gauche droite en haut en bas le move() le prends et lui pose ou il veut
        						//en  effet, il fallait qu'il nomme cette fonction autrement(la fonction rotate())...
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
    
    public double getMoney() {
    	return FamilyMoney/100.0;
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
		if((sum) <100) {
			energy += extraEnergy;
		}
		else {
			energy = 100;
		}
	}
	
	public void setSleepingState() {
		SleepingState = true;
	}
	
	public void setAwakeState() {
		SleepingState = false;
	}
	
	public boolean isSleeping() {
		return SleepingState;
	}
	
	public void addSatisfaction(int ExtraSatisfaction) {
		this.Satisfaction = this.Satisfaction + ExtraSatisfaction;
	}

	
}
