package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.Game;
import Model.GameObject;
import Model.Player;
import View.Window;

public class Buttons extends JFrame {
	public static JFrame frame;
	public static int i3 = 0;
	
	public Buttons (String title) {
		
		this.frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(150, 30, 150, 200);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.lightGray);
 
        
        
        
	}
	/*public void addRestart(JFrame window) {
		JButton restartButton = new JButton("Restart");
		restartButton.setBounds(150, 30, 150, 200);
    	restartButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				window.dispose();
			}
    	});
    	frame.setLayout(new BorderLayout());
    	frame.add(restartButton, BorderLayout.CENTER);
	}*/
	
	/*public void addNextlevel(JFrame window) {
		i3=i3+1;
		JButton NextLevel = new JButton("NextLevel");
		NextLevel.setBounds(150, 30, 150, 200);
		NextLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				window.dispose();
			}
    	});
    	frame.setLayout(new BorderLayout());
    	frame.add(NextLevel, BorderLayout.CENTER);
    	
	}*/
	
	
}
