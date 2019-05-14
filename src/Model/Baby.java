package Model;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Baby extends Player implements Sounds {

	public Baby(int x, int y, Game g, String gender) {
		super(x, y, g, gender);
		
	}
	
	@Override
	public void buy(String buttonx) {
		if(buttonx == "button2") {
			int price = Diaper.getPrice();
			int sum = FamilyMoney - price;
			if(sum>=0) {
				Diaper diaper = new Diaper();
				diaper.assignPlayer(this);
				inventory.add(diaper);
				FamilyMoney -= Diaper.getPrice();
				playSound("Resources/Sounds/money.wav");
				}
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
		

