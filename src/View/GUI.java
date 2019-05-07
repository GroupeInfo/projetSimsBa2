package View;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.Player;
import Model.Apple;
import Model.Attachable;
import Model.EnergyCoin;
public class GUI extends JPanel {
	private Player p;
	private int BAR_LENGTH = 120;
	private int BAR_WIDTH = 30;
	private ArrayList<Attachable> inventory = new ArrayList<Attachable>();


    public GUI() {
        this.setPreferredSize(new Dimension(450, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
    }
    
    public void setGUIAttributes( ArrayList<Attachable> inventory) {
    	this.inventory = inventory;
    	
    } 
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//draw avatar
        ImageIcon icon = new ImageIcon("Resources/SimsPerson.jpg");
        Image image = icon.getImage();
        g.drawImage(image, 150, 50, 100, 100, null);
         
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
        
        

		// bars 
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
		
		String currentWeapon = p.getCurrentWeapon();
		if (currentWeapon == "box") {
			g.fillOval(7, 820, 8, 8);
		}
		else if(currentWeapon == "shovel"){
			g.fillOval(100, 820, 8, 8);
		}
		else {
			g.fillOval(180, 820, 8, 8);
		}
		
		g.setColor(Color.BLACK);
		int food = p.getFamilyFood();
		g.drawString("x" + String.valueOf(food), 75, 950);
			
			
			
		
        
        //Money
        
		double FamilyMoney = p.getFamilyMoney();
        
        g.setColor(Color.black);
        g.drawString("FamilyMoney : ", 20, 740);
        g.drawString(String.valueOf(FamilyMoney), 100, 740);
        
		//INVENTORY
		g.setColor(Color.black);
		g.drawString("INVENTORY:", 20, 500);
			
		g.setColor(Color.darkGray);
		for (int i=0; i < 4; i++) {
			g.fillRect(20 + i*50, 520, 48, 48);
			}
			
	    
		
	    for (Object object: inventory) {
			int position = inventory.indexOf(object);
			
			if (object instanceof Apple) {
				icon = new ImageIcon("Resources/pomme2.png");
	            image = icon.getImage();
	            g.drawImage(image, 20 + position*50, 520, 48, 48, null);
				}
			
			if(object instanceof EnergyCoin) {
				icon = new ImageIcon("Resources/energycoin2.png");
				image = icon.getImage();
				g.drawImage(image, 20 + position *50, 520, 48, 48, null);
				
			}
			}
	    
		if (inventory.size() == 4) {
			g.setColor(Color.red);
			g.drawString("Inventory is FULL", 110, 500);
		}
		
        }
	
	
    

    public void redraw() {
        this.repaint();
    }
    
    public void setPlayer(Player p) {
		this.p = p;
	}

}
