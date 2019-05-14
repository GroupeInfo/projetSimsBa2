package Model;

public class MilkContainer extends GameObject implements Activable, OversizedObject {
	private int energy = 10;
	private int Satisfaction = 100;
	private Player p;
	private Game g;
	public MilkContainer(int X, int Y, int color, int width, int height, Game g) {
		super(X, Y, color, width, height);
		this.g = g;
	}

	@Override
	public void activate() {
		p = g.getActive_player();
		if(p instanceof Baby) {
			p.addSatisfaction(Satisfaction);
			p.addEnergy(energy);
		}
		
	}

	@Override
	public boolean isObstacle() {
		
		return true;
	}
	
	public boolean isInObjectSpace(int x, int y) {
    	boolean z = false;
    	int k1 = getPosX(); 
    	int k2  = getPosY() ;
    	int k3 = getWidthRatio() ; 
    	int k4 =  getHeightRatio();
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
