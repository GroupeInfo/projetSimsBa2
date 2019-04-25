package View;

import Model.BlockUnbreakable;
import Model.EnergyCoin;
import Model.Apple;
import Model.Bed;
import Model.BlockBreakable;
import Model.Game;
import Model.Directable;
import Model.GameObject;
import Model.Player;

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

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    public final int HOUSE_SIZE = 25;
    public final int OUTSIDE_SIZEX = 13;
    public final int OUTSIDE_SIZEY = 25;//final permet de fixer la map_size a 25 on peut plus jamais changer...essayez d'erire Map_size = 2 par exemple
    								//ca ne marchera pas...
    public static int BLOC_SIZE = 40;
    private Mouse mouseController = null;

    public Map() {
    	this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(HOUSE_SIZE*BLOC_SIZE, HOUSE_SIZE*BLOC_SIZE));
        																			//25 carreaux de dimension 20... donc 25*20
    
    	//MAP_SIZE = 2;
        
        /*addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/BLOC_SIZE; //combien de carreaux sépare 0 et le point...
				int y = e.getY()/BLOC_SIZE;
				mouseController.mapEvent(x, y);
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});*/
    }

    public void paint(Graphics g) {

    	ImageIcon iconFirst = null;
        Image imageFirst = null;
		
    	for (int i = 0; i < Game.houseSize; i++) { 
            for (int j = 0; j < Game.houseSize; j++) {
                int x = i;
                int y = j;
                iconFirst = new ImageIcon("Resources/Floor.jpg");
            	imageFirst = iconFirst.getImage();
            	g.drawImage(imageFirst, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, null);
            }
        }
    	
    	
    	int BLOC_SIZE1 = BLOC_SIZE -2;

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

             g.fillRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1);
             g.setColor(Color.BLACK);
             g.drawRect(x * BLOC_SIZE, y * BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1);
             
        
        ImageIcon icon = null;
        Image image = null;
        
        if (object instanceof Apple) {
        	icon = new ImageIcon("Resources/AppleFromAbove.jpg");
        	image = icon.getImage();
        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1, null);
        }
    
        
        if (object instanceof EnergyCoin) {
        	icon = new ImageIcon("Resources/EnergyCoin.jpg");
        	image = icon.getImage();
        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1, null);
        }
        
        if (object instanceof Player) {
        	boolean z = ((Player) object).isSleeping();
        	if(z != true) {
        	icon = new ImageIcon("Resources/SimsPerson.jpg");
        	image = icon.getImage();
        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1, null);
        	}
        }
        
        if (object instanceof BlockUnbreakable) {
        	  icon = new ImageIcon("Resources/Brick_Block.png");
              image = icon.getImage();
              g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1,  BLOC_SIZE1, null);
        }
        
        if (object instanceof Bed) {
      	  icon = new ImageIcon("Resources/Bed.jpg");
            image = icon.getImage();
            g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE , null);
        }
               
        	
        	// Decouper en fontions
            if(object instanceof Directable) {
                int direction = ((Directable) object).getDirection();
                
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

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint(); //après avoir bouger, ceci permet de recolorier le carreau du player... 
    }

	public void addMouse(Mouse m) {
		this.mouseController = m;
	}
}
