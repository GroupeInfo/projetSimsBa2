package Model;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Door extends GameObject implements Activable, Changeable, Sounds {
	private boolean opened = false;
	private Player p;
	
	public Door(int X, int Y, int color) {
		super(X, Y, color, 0, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate() {
		if(this.opened  == false) {
			opened = true;
			playSound("Resources/Sounds/doorclose.wav");
		}
		
		else {
			opened = false;
		}
		
	}

	@Override
	public boolean isObstacle() {
		boolean z;
		// TODO Auto-generated method stub
		if(opened == true) {
			z = false;
		}
		else {
			z = true;
		}
		return z;
	}

	

	@Override
	public boolean isUsed() {
		return  opened;
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
