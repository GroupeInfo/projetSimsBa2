package Model;

public abstract class FarmGameObjects {
    private int posX;
    private int posY;
    private int color;
    private int width;
    private int height;

    public FarmGameObjects(int X, int Y, int color, int width, int height) {
        posX = X;
        posY = Y;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public int getPosX() {
        return posX;
    }
    
    public void setPosX(int X) {
    	posX = X;
    }
    
    public int getPosY() {
        return posY;
    }
    
    public void setPosY(int Y) {
    	posY = Y;
    }
    
    public int getColor() {
        return color;
    }

    public int getWidthRatio() {
    	return width;
    }
    
    public int getHeightRatio() {
    	return height;
    }
    
    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();
    
}