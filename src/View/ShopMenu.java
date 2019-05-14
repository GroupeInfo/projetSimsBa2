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
	private JButton Button2;
	private Mouse mouse;
 

	public ShopMenu(Game g) {
		super("Shop");
		mouse = new Mouse(g, this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(500, 300 , 850, 500);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(shopMap, BorderLayout.LINE_START);
		
		Button = new JButton("Get Shovel for 50");
        Button.setBounds(0, 400, 260, 50);
		add(Button);
		
		Button1 = new JButton("Get knife for 150");
        Button1.setBounds(260, 400, 280, 50);
		add(Button1);
		
		Button2 = new JButton("Get diaper for 30");
        Button2.setBounds(530, 400, 300, 50);
		add(Button2);
	
		
		Button.addMouseListener(mouse);
		Button1.addMouseListener(mouse);
		Button2.addMouseListener(mouse);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
        
        
    	}

		public String getButtonClicked(Object o) {
			String s = ""; 
			if(o == this.Button) {
				s = "button";
			}
			else if(o == this.Button1) {
				s = "button1";
			}
			else {
				s = "button2";
			}
			
			return s;
		}
	
		 
}
