package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import Controller.Mouse;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Model.Game;

public class ShopMenu extends JFrame{
	private JPanel groupPanel = new JPanel(new BorderLayout());
	private ShopMap shopMap = new ShopMap();
	private JButton Button;
	private JButton Button1;
	private Mouse mouse;
 

	public ShopMenu(Game g) {
		super("Shop");
		mouse = new Mouse(g, this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(500, 300 , 550, 500);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(shopMap, BorderLayout.LINE_START);
        
		
		Button = new JButton("Get Shovel for 50");
        Button.setBounds(0, 400, 260, 50);
		add(Button);
		
		Button1 = new JButton("Get knife for 150");
        Button1.setBounds(260, 400, 280, 50);
		add(Button1);
	
		
		Button.addMouseListener(mouse);
		Button1.addMouseListener(mouse);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
        
        
    	}

		public void update() {
	        this.shopMap.redraw();
}
		
		public String getButtonClicked(Object o) {
			String s = ""; 
			if(o == this.Button) {
				s = "button";
			}
			else {
				s = "button1";
			}
			
			return s;
		}
	public class ShopMap extends JPanel {
		
		public ShopMap() {
	    	this.setFocusable(true);
	        this.requestFocusInWindow();
	        this.setPreferredSize(new Dimension(540,450));
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
	}

		 
		 
	public void redraw() {
	       this.repaint(); 
	}
	 
    }

}
