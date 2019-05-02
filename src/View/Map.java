package View;

import Model.BlockUnbreakable;
import Model.EnergyCoin;
import Model.FarmTeleportation;
import Model.Apple;
import Model.Bed;
//import Model.BlockBreakable;
import Model.Game;
import Model.Directable;
import Model.Door;
import Model.GameObject;
import Model.Player;
import Model.Chicken;
import Model.Computer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Controller.Mouse;
import Model.FarmGameObjects;
import Model.Game;
public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    private ArrayList<FarmGameObjects> farmObjects = null;
    private ArrayList<Player> players = null;
    public final int HOUSE_SIZE = 25;
    public final int OUTSIDE_SIZEX = 7;
    public final int OUTSIDE_SIZEY = 25;
    public static int BLOC_SIZE = 40;
    public static int BLOC_SIZE1 = BLOC_SIZE -2;
    private Mouse mouseController = null;

    //private Player p;
    public Map() {
    	this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension((HOUSE_SIZE+ OUTSIDE_SIZEX) *BLOC_SIZE , HOUSE_SIZE*BLOC_SIZE));
    
    }

    
    public void paint(Graphics g) {
    	
    	ImageIcon iconFirst = null;
        Image imageFirst = null;

        if(Game.mapChanger == false) {
        
        
    	for (int i = 0; i < HOUSE_SIZE; i++) { 
            for (int j = 0; j < HOUSE_SIZE; j++) {
                int x = i;
                int y = j;
                iconFirst = new ImageIcon("Resources/floor1.jpeg");
            	imageFirst = iconFirst.getImage();
            	g.drawImage(imageFirst, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, null);
            }
        }
    	for (int i = 25; i < 25 + OUTSIDE_SIZEX; i++) { 
            for (int j = 0; j < HOUSE_SIZE; j++) {
                int x = i;
                int y = j;
                iconFirst = new ImageIcon("Resources/Green.jpg");
            	imageFirst = iconFirst.getImage();
            	g.drawImage(imageFirst, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, null);
            }
        }    

        for (GameObject object : this.objects) {
        	 int x = object.getPosX();
             int y = object.getPosY();
             int w = object.getWidthRatio();
             int h = object.getHeightRatio();
             int color = object.getColor();

             if (color == 0) {
                 g.setColor(Color.DARK_GRAY);
             } else if (color == 1) {
                 g.setColor(Color.GRAY);
             } else if (color == 2) {
                 g.setColor(Color.BLUE);
             } else if (color == 3) {
                 g.setColor(Color.GREEN);
             } else if (color == 4) {
                 g.setColor(Color.RED);
             } else if (color == 5) {
                 g.setColor(Color.ORANGE);
             } else if (color == 6) {
             	g.setColor(Color.LIGHT_GRAY);
             }
             else if(color == 7) {
            	 g.setColor(Color.BLACK);
             }

             g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1);
             g.setColor(Color.BLACK);
             g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1);
             
        
        ImageIcon icon = null;
        Image image = null;
        
        if (object instanceof Apple) {
        	icon = new ImageIcon("Resources/pomme.jpeg");
        	image = icon.getImage();
        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1, h*BLOC_SIZE1, null);
        }
        
        
        if (object instanceof EnergyCoin) {
        	icon = new ImageIcon("Resources/energycoin1.jpeg");
        	image = icon.getImage();
        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1, null);
        }

        
        if (object instanceof BlockUnbreakable) {
        	  icon = new ImageIcon("Resources/Brick_Block.png");
              image = icon.getImage();
              g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE1, null);
        }
        
        if (object instanceof Computer) {
      	  	icon = new ImageIcon("Resources/Ordinateur.jpg");
            image = icon.getImage();
            g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE , null);
        }
        
        if (object instanceof Bed) {
      	  icon = new ImageIcon("Resources/bed1.jpeg");
            image = icon.getImage();
            g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE , null);
        }
        
        if(object instanceof Door) {
        	if(((Door) object).isOpened()) {
        	icon = new ImageIcon("Resources/door1.jpeg");
        	image = icon.getImage();
        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1,  BLOC_SIZE1 , null);
        	}
        	
        	else {
        		icon = new ImageIcon("Resources/door2.jpeg");
            	image = icon.getImage();
            	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1,  BLOC_SIZE1 , null);
            	
        	}
        }
            
             	// Decouper en fontions
            
        }
        ImageIcon icon = null;
        Image image = null;
        
        icon = new ImageIcon("Resources/tv12.jpeg");
        image = icon.getImage();
        g.drawImage(image, 19*BLOC_SIZE, 1*BLOC_SIZE, 3*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        
        icon = new ImageIcon("Resources/douche2.jpeg");
        image = icon.getImage();
        g.drawImage(image, 4*BLOC_SIZE, 22*BLOC_SIZE, 5*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/hanafie2.jpeg");
        image = icon.getImage();
        g.drawImage(image, 2*BLOC_SIZE, 16*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
       
        icon = new ImageIcon("Resources/gas2.jpeg");
        image = icon.getImage();
        g.drawImage(image, 19*BLOC_SIZE, 21*BLOC_SIZE, 3*BLOC_SIZE1,  3*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/sofa11.jpeg");
        image = icon.getImage();
        g.drawImage(image, 18*BLOC_SIZE, 4*BLOC_SIZE, 4*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/toilet1.jpeg");
        image = icon.getImage();
        g.drawImage(image, 7*BLOC_SIZE, 18*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        
        icon = new ImageIcon("Resources/single bed.jpeg");
        image = icon.getImage();
        g.drawImage(image, 9*BLOC_SIZE, 1*BLOC_SIZE, 3*BLOC_SIZE1,  4*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/single bed.jpeg");
        image = icon.getImage();
        g.drawImage(image, 13*BLOC_SIZE, 1*BLOC_SIZE, 3*BLOC_SIZE1,  4*BLOC_SIZE1, null);
        
        
        icon = new ImageIcon("Resources/table1.jpeg");
        image = icon.getImage();
        g.drawImage(image, 10*BLOC_SIZE, 16*BLOC_SIZE, 5*BLOC_SIZE1,  6*BLOC_SIZE1, null);

}

    
    else {
	
    	g.setColor(Color.lightGray);
		g.fillRect(0, 0, 1300, 1020);    	
    	
		for (int i = 0; i < HOUSE_SIZE; i++) { 
            for (int j = 0; j < HOUSE_SIZE; j++) {
                int x = i;
                int y = j;
                iconFirst = new ImageIcon("Resources/Green.jpg");
            	imageFirst = iconFirst.getImage();
            	g.drawImage(imageFirst, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, null);
            }
        }

    	
    	for (FarmGameObjects farmObject : this.farmObjects) {
       	 int x = farmObject.getPosX();
            int y = farmObject.getPosY();
            int w = farmObject.getWidthRatio();
            int h = farmObject.getHeightRatio();
            int color = farmObject.getColor();

            if (color == 0) {
                g.setColor(Color.DARK_GRAY);
            } else if (color == 1) {
                g.setColor(Color.GRAY);
            } else if (color == 2) {
                g.setColor(Color.BLUE);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            } else if (color == 6) {
            	g.setColor(Color.LIGHT_GRAY);
            }
            else if(color == 7) {
           	 g.setColor(Color.BLACK);
            }


            g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1);
            g.setColor(Color.BLACK);
            g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1);
            
       
       ImageIcon icon2 = null;
       Image image2 = null;
       
       if (farmObject instanceof Chicken ) {
       	icon2 = new ImageIcon("Resources/chicken.jpg");
       	image2 = icon2.getImage();
       	g.drawImage(image2, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1, h*BLOC_SIZE1, null);
       	
        int lifes = ((Chicken) farmObject).getLifePoints();
        g.setColor(Color.white);
        g.fillRect(x*BLOC_SIZE, y*BLOC_SIZE -4, BLOC_SIZE, 5);
        g.setColor(Color.red);
        g.fillRect(x*BLOC_SIZE, y*BLOC_SIZE -4, (lifes*BLOC_SIZE)/100, 5);
       	
      
        }
       }
     }
   
        

    	ImageIcon iconFirst1 = null;
        Image imageFirst1 = null;
        
        iconFirst1 = new ImageIcon("Resources/ChickenArrow.png");
        imageFirst1 = iconFirst1.getImage();
        g.drawImage(imageFirst1, 23*BLOC_SIZE, 23*BLOC_SIZE , BLOC_SIZE1, BLOC_SIZE1 , null);
        
        ImageIcon iconFirst2 = null;
        Image imageFirst2 = null;
        
        for (Player player : this.players) {     
	        boolean z = player.getSleepingState();
	        boolean z1 = player.isDead();
	        if(!z && !z1) {
	        iconFirst2 = new ImageIcon("Resources/SimsPerson.jpg");
	        imageFirst2 = iconFirst2.getImage();
	        int x = player.getPosX();
	        int y = player.getPosY();
	        g.drawImage(imageFirst2, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1, null);
	        	
	        //
	        
	       int direction = player.getDirection();
	       int deltaX = 0;
	       int deltaY = 0;
	            
	       switch (direction) {
	       case Directable.EAST:
	    	   deltaX = +BLOC_SIZE1/2;
	    	   break;
	       case Directable.NORTH:
	           deltaY = -BLOC_SIZE1/2;
	           break;
	       case Directable.WEST:
	           deltaX = -BLOC_SIZE1/2;
	           break;
	        case Directable.SOUTH:
	            deltaY = BLOC_SIZE1/2;
	            break;
	            }
	      
	       int xCenter = x * BLOC_SIZE + BLOC_SIZE1/2;
	       int yCenter = y * BLOC_SIZE + BLOC_SIZE1/2;
	       g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY); 	   
	       
	    }
    }
   
}    
    
  
    
    
    public void setFarmObjects(ArrayList<FarmGameObjects> farmObjects) {
    	this.farmObjects = farmObjects;
    }
    
    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }
    
    public void setPlayers(ArrayList<Player> Players) {
        this.players = Players;
    }
    
    public void redraw() {
        this.repaint(); //après avoir bouger, ceci permet de recolorier le carreau du player... 
    }

	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
}
