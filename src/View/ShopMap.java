package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ShopMap extends JPanel {
	
	public ShopMap() {
    	this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(840,450));
    }
	
	 public void paint(Graphics g) {	
		ImageIcon icon = null;
		Image image = null;
	
		icon = new ImageIcon("Resources/Shovel.jpg");
		image = icon.getImage();
		g.drawImage(image, 0, 0, 260, 400 , null);
		
		icon = new ImageIcon("Resources/Knife.jpg");
		image = icon.getImage();
		g.drawImage(image, 260, 0, 280, 400, null);
		
		icon = new ImageIcon("Resources/Diaper.jpg");
		image = icon.getImage();
		g.drawImage(image, 530, 0, 300, 400, null);
	
	
	 }

	 public void update() {
	    this.redraw();
	}
		
	 public void redraw() {
       this.repaint(); 
	 }
 
}