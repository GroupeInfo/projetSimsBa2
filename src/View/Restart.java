package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;
import Main.PlayWindow;
import Model.Game;
import Model.Sounds;
import View.ShopMenu;
import View.ShopMap;


public class Restart extends JFrame implements ActionListener {
	private JPanel groupPanel = new JPanel(new BorderLayout());
	private GameOver GameOver = new GameOver();
	private JButton Button;
 

	public Restart() {
		super("Restart");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(500, 500, 1000, 500);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(GameOver, BorderLayout.LINE_START);
		
        Button = new JButton("RESTART");
        Button.setBounds(5, 410, 260, 50);
        Button.addActionListener(this);
		add(Button);
        
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
   
    	}

		public void update() {
	        this.GameOver.redraw();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.dispose();
			PlayWindow play = new PlayWindow();	
		}


	
}

	
	
    


	
	
	
	

