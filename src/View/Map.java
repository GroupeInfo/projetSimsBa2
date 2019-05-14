package View;

import Model.BlockUnbreakable;


import Model.Changeable;
import Model.Kitchen;
import Model.MilkContainer;
import Model.Parent;
import Model.Shop;
import Model.EnergyCoin;
import Model.Apple;
import Model.Bed;
import Model.Game;
import Model.Directable;
import Model.Door;
import Model.GameObject;
import Model.Player;
import Model.Chicken;
import Model.Computer;
import Model.Shower;
import Model.Trash;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Model.FarmGameObjects;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    private ArrayList<FarmGameObjects> farmObjects = null;
    private ArrayList<Player> players = null;
    private static int HOUSE_SIZE ;
    private final int OUTSIDE_SIZEX = 7; 
    private static int BLOC_SIZE = 40;
    private static int BLOC_SIZE1 = BLOC_SIZE -2;

    public Map() {
    	this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension((getHOUSE_SIZE()+ getOUTSIDE_SIZEX()) *BLOC_SIZE , getHOUSE_SIZE()*BLOC_SIZE));
    
    }
    
    public static void setMapSize(int house_size) {
    	setHOUSE_SIZE(house_size);
    }
    
    
    public void paint(Graphics g) {
    	Graphics G = g;
    	
    	ImageIcon iconFirst = null;
        Image imageFirst = null;

        if(Game.getMapChanger() == false) {
        
        
    	for (int i = 0; i < getHOUSE_SIZE(); i++) { 
            for (int j = 0; j < getHOUSE_SIZE(); j++) {
                int x = i;
                int y = j;
                iconFirst = new ImageIcon("Resources/floor1.jpeg");
            	imageFirst = iconFirst.getImage();
            	g.drawImage(imageFirst, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, null);
            }
        }
    	for (int i  = getHOUSE_SIZE(); i < getHOUSE_SIZE() + getOUTSIDE_SIZEX(); i++) { 
            for (int j = 0; j < getHOUSE_SIZE(); j++) {
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
        

        if (object instanceof Trash) {
        	icon = new ImageIcon("Resources/Trash.jpg");
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
        
        if (object instanceof MilkContainer) {
      	  	icon = new ImageIcon("Resources/MilkContainer.jpg");
            image = icon.getImage();
            g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE , null);
        }
        
        
        if(object instanceof Changeable) {
        	if(((Changeable) object).isUsed()){
        		if(object instanceof Shower) {
	        	icon = new ImageIcon("Resources/douche2.jpeg");
	        	image = icon.getImage();
	        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1, h*BLOC_SIZE, null);
        		} 
        		else if (object instanceof Bed) {
        	      	  icon = new ImageIcon("Resources/bed2.jpeg");
        	            image = icon.getImage();
        	            g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE , null);
        	        }
        	    else if(object instanceof Door) {
        	        	icon = new ImageIcon("Resources/door1.jpeg");
        	        	image = icon.getImage();
        	        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1,  BLOC_SIZE1 , null);
        	        	}
        	    else if (object instanceof Kitchen) {
        	    	icon = new ImageIcon("Resources/gas2.jpeg");
                    image = icon.getImage();
                    g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE1, null);
                  }
        	   
        		
        	}
        	else {
        		if (object instanceof Shop) {
                	icon = new ImageIcon("Resources/Shop.jpg");
                	image = icon.getImage();
                	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1, h*BLOC_SIZE1, null);
                }  

        		else if(object instanceof Shower) {
        			icon = new ImageIcon("Resources/douche1.jpeg");
                	image = icon.getImage();
                	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1, h*BLOC_SIZE, null);
        		}
        		else if (object instanceof Bed) {
      	      	  icon = new ImageIcon("Resources/bed1.jpeg");
      	            image = icon.getImage();
      	            g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE , null);
      	        }
        		else if(object instanceof Door) {
      	        	icon = new ImageIcon("Resources/door2.jpeg");
      	        	image = icon.getImage();
      	        	g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1,  BLOC_SIZE1 , null);
      	        	}
        		else if (object instanceof Kitchen) {
	        		icon = new ImageIcon("Resources/gas1.jpeg");
	                image = icon.getImage();
	                g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, w*BLOC_SIZE1,  h*BLOC_SIZE1, null);
                }
        	}
        }
             	// Decouper en fontions
            
        }
        ImageIcon icon = null;
        Image image = null;
        
        icon = new ImageIcon("Resources/foutain.jpeg");
        image = icon.getImage();
        g.drawImage(image, 26*BLOC_SIZE, 17*BLOC_SIZE, 5*BLOC_SIZE1,  5*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/cabinet.jpeg");
        image = icon.getImage();
        g.drawImage(image, 7*BLOC_SIZE, 6*BLOC_SIZE, 1*BLOC_SIZE1,  3*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/cabinet.jpeg");
        image = icon.getImage();
        g.drawImage(image, 9*BLOC_SIZE, 6*BLOC_SIZE, 1*BLOC_SIZE1,  3*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/cabinet.jpeg");
        image = icon.getImage();
        g.drawImage(image, 15*BLOC_SIZE, 6*BLOC_SIZE, 1*BLOC_SIZE1,  3*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/cabinet.jpeg");
        image = icon.getImage();
        g.drawImage(image, 1*BLOC_SIZE, 19*BLOC_SIZE, 1*BLOC_SIZE1,  4*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/tv12.jpeg");
        image = icon.getImage();
        g.drawImage(image, 19*BLOC_SIZE, 1*BLOC_SIZE, 3*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/tree.jpeg");
        image = icon.getImage();
        g.drawImage(image, 25*BLOC_SIZE, 21*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/tree.jpeg");
        image = icon.getImage();
        g.drawImage(image, 30*BLOC_SIZE, 21*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/tree.jpeg");
        image = icon.getImage();
        g.drawImage(image, 25*BLOC_SIZE, 15*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);

        
        icon = new ImageIcon("Resources/tree.jpeg");
        image = icon.getImage();
        g.drawImage(image, 30*BLOC_SIZE, 15*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/welcome.jpeg");
        image = icon.getImage();
        g.drawImage(image, 25*BLOC_SIZE, 11*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/path.jpeg");
        image = icon.getImage();
        g.drawImage(image, 27*BLOC_SIZE, 12*BLOC_SIZE, 2*BLOC_SIZE1,  5*BLOC_SIZE1, null);
       
      
        icon = new ImageIcon("Resources/roundtable.jpeg");
        image = icon.getImage();
        g.drawImage(image, 16*BLOC_SIZE, 17*BLOC_SIZE, 3*BLOC_SIZE1,  3*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/fridge.jpeg");
        image = icon.getImage();
        g.drawImage(image, 16*BLOC_SIZE, 21*BLOC_SIZE, 3*BLOC_SIZE1,  3*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/washbasin.jpg");
        image = icon.getImage();
        g.drawImage(image, 20*BLOC_SIZE, 16*BLOC_SIZE, 4*BLOC_SIZE1,  4*BLOC_SIZE1, null);
        
        icon = new ImageIcon("Resources/hanafie2.jpeg");
        image = icon.getImage();
        g.drawImage(image, 2*BLOC_SIZE, 16*BLOC_SIZE, 2*BLOC_SIZE1,  2*BLOC_SIZE1, null);
      
        
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
    	//TODO
	
    	g.setColor(Color.lightGray);
		g.fillRect(0, 0, 1300, 1020);    	
    	
		for (int i = 0; i < getHOUSE_SIZE(); i++) { 
            for (int j = 0; j < getHOUSE_SIZE(); j++) {
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
        
        
        for (Player player : this.players) {
        	
	       boolean z = player.getSleepingState();
	       boolean green = false;
	       if(!z) {
	    	   int x = player.getPosX();
		       int y = player.getPosY();
		       String gender = player.getGender();
	           
		       if(x>24) {
		    	   green = true;
		       }
		       
	           String s = "";
	           if(player instanceof Parent) {
	        	   s = "Parent";
	           }
	           else {
	        	   s = "Baby";
	           }
	           if(player.isInFarmState() == Game.getMapChanger()){
		       int direction = player.getDirection();
		       
		       DrawEverything(x, y, gender, direction, g, s, green);
	       		}
           	}
        }
        
        
    }
       
    public void DrawEverything(int x,int y,String gender, int direction, Graphics g, String s, boolean green){
    	int deltaX = 0;
	    int deltaY = 0;
    	switch (direction) {
	       case Directable.EAST:
	    	   drawPlayer(gender, x, y, g, s, 1, green);
	    	   deltaX = BLOC_SIZE1/2;
	    	   break;
	       case Directable.NORTH:
	    	   drawPlayer(gender, x, y, g, s, 2, green);
		       deltaY = -BLOC_SIZE1/2;
	    	   break;
	       case Directable.WEST:
	    	   drawPlayer(gender, x, y, g, s, 3, green);
	    	   deltaX = -BLOC_SIZE1/2;
	    	   break;
	    	   
	       case Directable.SOUTH:
	    	   drawPlayer(gender, x, y, g, s, 4, green);
		      deltaY = BLOC_SIZE1/2;
		      break;
    	}
    	
    	int xCenter = x * BLOC_SIZE + BLOC_SIZE1/2;
	    int yCenter = y * BLOC_SIZE + BLOC_SIZE1/2;
	    g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY); 
    	
    }
    
    public void drawImage(int x, int y, String s, Graphics g, String type) {
    	ImageIcon icon = null;
        Image image = null;
        
       icon = new ImageIcon("Resources/" +s+ type);
	   image = icon.getImage();
	   g.drawImage(image, x*BLOC_SIZE, y*BLOC_SIZE, BLOC_SIZE1, BLOC_SIZE1, null);
 	  
        
    }
    
    public void drawPlayer(String gender, int x, int y, Graphics g, String s, int pictureNumber, boolean green) { 
	 	   if(s == "Parent") {
	 		   drawParent(gender, x, y, g, pictureNumber, green);
	 	   }
	 	   else {
	 		   drawBaby(gender, x, y, g, pictureNumber, green);
	 	   }
	 	   
    }
    
    
    public void drawParent(String gender, int x, int y, Graphics g, int pictureNumber, boolean green) {
    	String s = ".jpeg";
    	if(!(Game.getMapChanger()) && !green) {
 	 	   if(gender == "Male") {
 	 		  drawImage(x, y, "adam"+ String.valueOf(pictureNumber), g, s);   
 	 	   }
 	 	   else {
 	 		   drawImage(x, y, "eve"+ String.valueOf(pictureNumber), g, s);     
 	 	   }
 	 	   
 	 	   }
 	 	   else {
 	 		   if(gender == "Male") {
 	 			   drawImage(x, y, "adam" + String.valueOf(pictureNumber) +  "F", g, s);
 		    	   }
 	 		   else {
 	 			   drawImage(x, y, "eve" + String.valueOf(pictureNumber) +  "F", g, s);
 			           
 		    	   }
 	 	   }
    }
    
    public void drawBaby(String gender, int x, int y, Graphics g, int pictureNumber, boolean green) {
    	String s = ".jpg";
    	if(!(Game.getMapChanger()) && !green) {
  	 	   if(gender == "Male") {
  	 		  drawImage(x, y, "Baby"+ String.valueOf(pictureNumber), g, s);   
  	 	   }
  	 	   else {
  	 		   drawImage(x, y, "Baby"+ String.valueOf(pictureNumber), g , s);     
  	 	   }
  	 	   
  	 	   }
  	 	   else {
  	 		   if(gender == "Male") {
  	 			   drawImage(x, y, "Baby"+ String.valueOf(pictureNumber) +  "F", g, s);
  		    	   }
  	 		   else {
  	 			   drawImage(x, y, "Baby" + String.valueOf(pictureNumber) +  "F", g, s);
  			           
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
        this.repaint(); 
    }

	public static int getHOUSE_SIZE() {
		return HOUSE_SIZE;
	}

	public static void setHOUSE_SIZE(int hOUSE_SIZE) {
		HOUSE_SIZE = hOUSE_SIZE;
	}

	public int getOUTSIDE_SIZEX() {
		return OUTSIDE_SIZEX;
	}
    

	
}
