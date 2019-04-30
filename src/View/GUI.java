package View;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.Player;

import Model.GameObject;
import Model.Apple;
import Model.Attachable;
import Model.EnergyCoin;
import Model.Game;
import Model.Player;
public class GUI extends JPanel {
	private Player p;
	private int BAR_LENGTH = 120;
	private int BAR_WIDTH = 30;
	private int AVATAR_SIZE = 100;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<Attachable> inventory = new ArrayList<Attachable>();
	private ArrayList<Player> players = new ArrayList<Player>();

	private boolean playerDead = false;

    public GUI() {
        this.setPreferredSize(new Dimension(450, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
    }
    
    public void setGUIAttributes(ArrayList<GameObject> objects, ArrayList<Attachable> inventory, ArrayList<Player> players) {
    	this.objects = objects;
    	this.inventory = inventory;
    	this.players = players;
    	
    } 
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		//draw avatar
        ImageIcon icon = new ImageIcon("Resources/SimsPerson.jpg");
        Image image = icon.getImage();
        g.drawImage(image, 150, 50, 100, 100, null);
        
        ImageIcon icon1 = new ImageIcon("Resources/Hunger.jpg");
        Image image1 = icon1.getImage();
        g.drawImage(image1, 6, 310, 48, 52, null);

		// bars 
        // Energy 
        g.setColor(Color.YELLOW);
        g.drawString("ENERGY", 6, 190);//drawString(str, int x, int y)
        
        g.setColor(Color.cyan);
        g.drawString("HUNGER", 6, 300);
        
        g.setColor(Color.RED);
        g.fillRect(60, 210, BAR_LENGTH, BAR_WIDTH);
        g.fillRect(60, 320, BAR_LENGTH, BAR_WIDTH);
        
        
        g.setColor(Color.GREEN);
        int length_ok = (int)  Math.round(BAR_LENGTH*p.getEnergy()); //getEnergy du player! elle touche pas à la classe EnergyCoin!! Rien à voir .
        int length1_ok = (int) Math.round(BAR_LENGTH*p.getSatisfaction());
        g.fillRect(60, 210, length_ok, BAR_WIDTH);
        g.fillRect(60, 320, length1_ok, BAR_WIDTH);
        
        
        
        //DrawingPLayer
        
        for(Player player: players) {
			if (player.isDead() == false) {
				
				int playerLife = player.getLifePoints();
				
				g.setColor(Color.black);
				g.drawString("Player's life:", 20, 650);
				
				g.setColor(Color.blue);
				g.drawString(String.valueOf(playerLife) + "%", 170, 650);
				
				g.setColor(Color.white);
				g.fillRect(20, 665, 200, 30);
				
				g.setColor(Color.red);
				g.fillRect(20, 665, 2*playerLife, 30);
				
				String currentWeapon = player.getCurrentWeapon();
				if (currentWeapon == "sword") {
					g.fillOval(14, 320, 8, 8);
				}
				else {
					g.fillOval(14, 405, 8, 8);
				}
				break;
			}
			
		}
        
        
        //ENERGYCOIN
        
        ImageIcon icon2 = null;
	    Image image2 = null;
	    for(GameObject object: objects) {
			
			if (object instanceof EnergyCoin) {
				icon2 = new ImageIcon("Resources/Energy.png");
            	image2 = icon2.getImage();
            	g.drawImage(image2, 6, 200, 48, 52, null);
			}
			
			//INVENTORY
		g.setColor(Color.black);
		g.drawString("INVENTORY:", 20, 500);
			
		g.setColor(Color.darkGray);
		for (int i=0; i < 4; i++) {
			g.fillRect(20 + i*50, 520, 48, 48);
			}
			
	    }
		
	    for (Object object: inventory) {
			int position = inventory.indexOf(object);
			
			if (object instanceof Apple) {
				icon = new ImageIcon("Resources/Apple.png");
	            image = icon.getImage();
	            g.drawImage(image, 20 + position*50, 520, 48, 48, null);
				}
			
			if(object instanceof EnergyCoin) {
				icon = new ImageIcon("Resources/EnergyBulb.jpg");
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
    public void setPlayer(Player p2) {
		this.p = p2;
	}

}
