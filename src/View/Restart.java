package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;
import View.ShopMenu.ShopMap;


public class Restart extends JFrame {
	private JPanel groupPanel = new JPanel(new BorderLayout());
	private JButton Button;
	private JButton Button1;
	private Mouse mouse;
	
	public Restart (String title) {
		super("Shop");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(2000, 2000 , 550, 500);
        this.getContentPane().setBackground(Color.gray);
        groupPanel.add(this, BorderLayout.LINE_START);
        
		
		Button = new JButton("Restart");
        Button.setBounds(0, 400, 260, 50);
		add(Button);
			}
	
    
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
	
	
	
	

