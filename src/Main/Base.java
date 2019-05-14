package Main;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Controller.Mouse;
import Controller.Keyboard;
import Model.Game;
import Model.Sounds;
import View.Map;
import View.Window;
import Main.PlayWindow;

public class Base implements ActionListener, Sounds {

 
	public void actionPerformed(ActionEvent e) {
		
		PlayWindow.frame.dispose();
		try {
			String Difficulity  = PlayWindow.Difficulity.getText();
			String HOUSE_SIZE = PlayWindow.MapSize.getText();
			
			Game.setMapSize(Integer.valueOf(HOUSE_SIZE));
			Game.setDifficulity(Integer.valueOf(Difficulity));
			Map.setMapSize(Integer.valueOf(HOUSE_SIZE));
			Window window = new Window();
			playSound("Resources/Sounds/startgame.wav");
	        Game game = new Game(window);
		
			
			Keyboard keyboard = new Keyboard(game);
		    window.setKeyListener(keyboard);
			}
		
			catch(Exception e1) {
				System.out.println("enter an integer!");
				PlayWindow playWindow = new PlayWindow();
         
			}
			finally {
				System.out.println("Enjoy the game:) (if it has started...)");
				}

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
        
      
	

