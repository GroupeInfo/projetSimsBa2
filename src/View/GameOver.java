package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.Sounds;

public class GameOver extends JPanel implements Sounds {
	
	public GameOver() {
    	this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(2000,2000));
    }
	 public void paint(Graphics g) {	
		ImageIcon icon = null;
	    Image image = null;
	    
	    icon = new ImageIcon("Resources/GameOver.jpg");
	    playSound("Resources/Sounds/gameover.wav");
	   	image = icon.getImage();
	   	g.drawImage(image, 0, 0 ,1000, 500 , null);
	   	
	 }

	 
	 
	 public void redraw() {
       this.repaint(); 
	 }


	 public  void playSound(String file) {
		 File voice = new File(file);
	
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(voice));
			clip.start();
			}
		
		catch(Exception e) {
		}
	}
	 
}