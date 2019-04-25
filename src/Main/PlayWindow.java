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
		
		JTextField mapSize = new JTextField("20");
		mapSize.setBounds(150, 100, 50, 20);
		panel.add(mapSize);
		MapSize = mapSize;
		
		///////////////////////////////////////////////////
		JLabel pixel = new JLabel("Pixels:");
		pixel.setBounds(245, 100, 100, 20);
		panel.add(pixel);
		
		JTextField pixelNumber = new JTextField("40");
		pixelNumber.setBounds(300, 100, 50, 20);
		panel.add(pixelNumber);
		PixelNumber = pixelNumber;
		
		////////////////////////////////////////////////
		JLabel label2 = new JLabel("Set number of breakble blocks:");
		label2.setBounds(50, 140, 200, 20);
		panel.add(label2);
		
		JTextField breakableBlocks = new JTextField("40");
		breakableBlocks.setBounds(250, 140, 50, 20);
		panel.add(breakableBlocks);
		BreakableBlocks = breakableBlocks;
		
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
		JLabel space = new JLabel("SPACE : attack");
		space.setBounds(145, 330, 100, 20);
		panel.add(space);
		
		
		/////////////////////////////////////////////////////////////////////////		
		frame.add(panel);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 430, 600);
//		frame.setSize(430, 600);
		frame.setVisible(true);
		
		
	}

}