package View;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.omg.CORBA.portable.ValueBase;

import Model.Player;
import Model.Trash;
import Model.Apple;
import Model.Attachable;
import Model.Baby;
import Model.Diaper;
import Model.EnergyCoin;
import Model.Parent;
public class GUI extends JPanel {
	private Player p;
	private int BAR_LENGTH = 240;
	private int BAR_WIDTH = 30;
	private int timeLeft = 0;
	private ArrayList<Attachable> inventory = new ArrayList<Attachable>();


    public GUI() {
        this.setPreferredSize(new Dimension(450, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
    }
    
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//draw avatar
		if(p instanceof Parent) {
	        if(p.getGender() == "Male") {
	        	ImageIcon icon = new ImageIcon("Resources/SimsBoy.webp");
		        Image image = icon.getImage();
		        g.drawImage(image, 150, 50, 150, 100, null);
	        }
	        else {
	        	ImageIcon icon = new ImageIcon("Resources/SimsGirl.png");
		        Image image = icon.getImage();
		        g.drawImage(image, 150, 50, 150, 100, null);	
	        	}
			}
		
		else if(p instanceof Baby) {
		    ImageIcon icon = new ImageIcon("Resources/SimsBaby.jpg");
	        Image image = icon.getImage();
	        g.drawImage(image, 150, 50, 150, 100, null);	
	     }
		
		ImageIcon icon1 = new ImageIcon("Resources/Energybar.jpeg");
        Image image1 = icon1.getImage();
        g.drawImage(image1, 6, 200, 48, 52, null);
		
        ImageIcon icon2 = new ImageIcon("Resources/like.jpeg");
        Image image2 = icon2.getImage();
        g.drawImage(image2, 6, 310, 48, 52, null);
        
        ImageIcon icon3 = new ImageIcon("Resources/Hygiene.jpg");
        Image image3 = icon3.getImage();
        g.drawImage(image3, 6, 420, 48, 52, null);
        
        ImageIcon icon4 = new ImageIcon("Resources/Punch.png");
        Image image4 = icon4.getImage();
        g.drawImage(image4, 26, 800, 48, 52, null);
        
        ImageIcon icon5 = new ImageIcon("Resources/Shovel.jpg");
        Image image5 = icon5.getImage();
        g.drawImage(image5, 110, 800, 48, 52, null);
        
        ImageIcon icon6 = new ImageIcon("Resources/Knife.jpg");
        Image image6 = icon6.getImage();
        g.drawImage(image6, 192, 800, 48, 52, null);
        
        ImageIcon icon7 = new ImageIcon("Resources/ChickenDead.jpg");
        Image image7 = icon7.getImage();
        g.drawImage(image7, 26, 900, 48, 52, null);
        
        

		// ComputerTimer
        g.setColor(Color.black);
        if(timeLeft == 0) {
            g.drawString("YOU CAN WORK WITH ONE OF THE PARENTS!", 19, 760);//drawString(str, int x, int y)
        }
        
        else {
        	g.drawString("Oops, You can work in : " + String.valueOf(timeLeft) + " seconds", 19, 760);
        }
        
        // Energy 
        g.setColor(Color.black);
        g.drawString("ENERGY", 6, 190);//drawString(str, int x, int y)
        g.drawString("SATISFACTION", 6, 300);
        g.drawString("HYGIENE", 6, 410);
        
        g.setColor(Color.RED);
        g.fillRect(60, 210, BAR_LENGTH, BAR_WIDTH);
        g.fillRect(60, 320, BAR_LENGTH, BAR_WIDTH);
        g.fillRect(60, 440, BAR_LENGTH, BAR_WIDTH);
        
        
        g.setColor(Color.GREEN);
        int length_ok = (int)  Math.round(BAR_LENGTH*p.getEnergy()); //getEnergy du player! elle touche pas à la classe EnergyCoin!! Rien à voir .
        int length1_ok = (int) Math.round(BAR_LENGTH*p.getSatisfaction());
        int length2_ok = (int) Math.round(BAR_LENGTH*p.getHygiene());
        
        g.fillRect(60, 210, length_ok, BAR_WIDTH);
        g.fillRect(60, 320, length1_ok, BAR_WIDTH);
        g.fillRect(60, 440, length2_ok, BAR_WIDTH);
        
        
        //DrawingPLayer	
        int playerLife = p.getLifePoints();
		
		g.setColor(Color.black);
		g.drawString("Player's life:", 20, 650);
		
		g.setColor(Color.blue);
		g.drawString(String.valueOf(playerLife) + "%", 170, 650);
		
		g.setColor(Color.white);
		g.fillRect(20, 665, 200, 30); //200 de longeur donc 2*player en bas obligatoire car sinon ca donne la moitié du rectangle (100 ---> 50 par exemple)
		
		g.setColor(Color.red);
		g.fillRect(20, 665, 2*playerLife, 30);
		
		if(p instanceof Parent) {
			String currentWeapon = ((Parent) p).getCurrentWeapon();
			if (currentWeapon == "punch") {
				g.fillOval(7, 820, 8, 8);
			}
			else if(currentWeapon == "shovel"){
				g.fillOval(100, 820, 8, 8);
			}
			else {
				g.fillOval(180, 820, 8, 8);
		}
		}
		g.setColor(Color.BLACK);
		int food = p.getFamilyFood();
		g.drawString("x" + String.valueOf(food), 75, 950);
		
		//Inventory
		
		g.setColor(Color.black);
		g.drawString("INVENTORY:", 20, 500);
			
		g.setColor(Color.darkGray);
		for (int i=0; i < 4; i++) {
			g.fillRect(20 + i*50, 520, 48, 48);
			}
			
	    
		this.inventory = p.getinventory();
		
	    for (Object object: inventory) {
			int position = inventory.indexOf(object);
			
			if (object instanceof Apple) {
				ImageIcon icon = new ImageIcon("Resources/pomme2.png");
	            Image image = icon.getImage();
	            g.drawImage(image, 20 + position*50, 520, 48, 48, null);
				}
			
			if(object instanceof EnergyCoin) {
				ImageIcon icon = new ImageIcon("Resources/energycoin2.png");
				Image image = icon.getImage();
				g.drawImage(image, 20 + position *50, 520, 48, 48, null);
				
			}
			
			if (object instanceof Trash) {
				ImageIcon icon = new ImageIcon("Resources/Trash.jpg");
	            Image image = icon.getImage();
	            g.drawImage(image, 20 + position*50, 520, 48, 48, null);
	            int X = ((Trash) object).getLightingCoordinateX();
	            int Y = ((Trash) object).getLightingCoordinateY();
	            g.drawString("Put it on (" + String.valueOf(X) + "," + String.valueOf(Y) +")" + " :D", 20, 600);
				}
			
			if(object instanceof Diaper) {
				ImageIcon icon = new ImageIcon("Resources/Diaper.jpg");
				Image image = icon.getImage();
				g.drawImage(image, 20 + position *50, 520, 48, 48, null);
				
			}
			
			
			}
	    
		if (inventory.size() == 4) {
			g.setColor(Color.red);
			g.drawString("Inventory is FULL", 110, 500);
		}
		
			
			
			
		
        
        //Money
        
		double FamilyMoney = p.getFamilyMoney();
        
        g.setColor(Color.black);
        g.drawString("FamilyMoney : ", 20, 740);
        g.drawString(String.valueOf(FamilyMoney), 100, 740);
        
		
        }
	
	
    

    public void redraw() {
        this.repaint();
    }
    
    public void setPlayer(Player p) {
		this.p = p;
	}
    
    public void setComputerTimer(int waitcount, int difficulity) {
    	timeLeft = difficulity - waitcount;
    	
    }


}
