package Main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Game;

public class PlayWindow extends JFrame{
	//Window which gives the possibility to change some settings before launching game
	
	
	public static JTextField MapSize = null;
	public static JTextField BreakableBlocks = null;
	public static JTextField PixelNumber = null;
	public static JTextField Difficulity = null;
    static JFrame frame;

	public PlayWindow() {
		this.frame = new JFrame("Game Launcher");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		
		JButton playButton = new JButton("PLAY");
		playButton.setBounds(150, 30, 100, 50);
		panel.add(playButton);
		playButton.addActionListener(new Base());
		
		////////////////////////////////////////////////
		JLabel label1 = new JLabel("Set map size:");
		label1.setBounds(50, 100, 100, 20);
		panel.add(label1);
		
		JTextField mapSize = new JTextField("25");
		mapSize.setBounds(150, 100, 50, 20);
		panel.add(mapSize);
		MapSize = mapSize;
		
		////////////////////////////////////////////////
		JLabel label2 = new JLabel("Set number of breakble blocks:");
		label2.setBounds(50, 140, 200, 20);
		panel.add(label2);
		
		JTextField breakableBlocks = new JTextField("40");
		breakableBlocks.setBounds(250, 140, 50, 20);
		panel.add(breakableBlocks);
		BreakableBlocks = breakableBlocks;
		
		//////////////////////////////////////////////////////////
		JLabel label3 = new JLabel("Set difficulity:");
		label3.setBounds(50, 170, 100, 20);
		panel.add(label3);
		
		JTextField difficulity = new JTextField("15");
		difficulity.setBounds(150, 170, 50, 20);
		panel.add(difficulity);
		Difficulity = difficulity;
		
		
		//////////////////////////////////////////////////////////
		JLabel up = new JLabel("UP arrow : move up");
		up.setBounds(50, 200, 200, 20);
		panel.add(up);
		
		JLabel down = new JLabel("DOWN arrow: move down");
		down.setBounds(50, 230, 200, 20);
		panel.add(down);
		
		JLabel left = new JLabel("LEFT arrow: move left");
		left.setBounds(50, 260, 200, 20);
		panel.add(left);
		
		JLabel right = new JLabel("RIGHT arrow: move right");
		right.setBounds(50, 290, 200, 20);
		panel.add(right);
		
		///////////////////////////////////////////////////////
		JLabel space = new JLabel("SPACE : Activate things or Attack an animal");
		space.setBounds(50, 320, 400, 20);
		panel.add(space);
		/////////////////////////////////////////////////////////////////////////
		JLabel A = new JLabel("A : Hold things");
		A.setBounds(50, 350, 200, 20);
		panel.add(A);
		/////////////////////////////////////////////////////////////////////////
		JLabel Q = new JLabel("Q : Quit Game");
		Q.setBounds(150, 400, 300, 20);
		panel.add(Q);
		
		
		
		frame.add(panel);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 430, 600);
//		frame.setSize(430, 600);
		frame.setVisible(true);
		
		
	}

}