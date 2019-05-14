package Model;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Parent extends Player implements Sounds {
	private ArrayList<AttackWeapon> Weapons;
	private AttackWeapon currentWeapon = null;
    private AttackWeapon punch ;
    private AttackWeapon shovel ;
    private AttackWeapon knife; 
    
	public Parent(int x, int y, Game g, String gender) {
		super(x, y, g, gender);
		 
	  this.punch =  new AttackWeapon(25, 1, 0);;
      this.shovel = new AttackWeapon(50, 1, 50);;
      this.knife = new AttackWeapon(100, 1, 150);;
       
      this.Weapons = new ArrayList<AttackWeapon>();
        
      this.Weapons.add(punch);
      currentWeapon = this.punch;
	}
	
	public String getCurrentWeapon() {
    	if (currentWeapon == punch) {
    		return "punch";
    	}
    	else if (currentWeapon == shovel){
    		return "shovel";
    	}
    	else {
    		return "knife";
    	}
    }
	
	 public void changeWeapon() {
			int index = Weapons.indexOf(currentWeapon) +1;
			int size = Weapons.size();
			System.out.println(size);
			System.out.println(index);
			try {
			if(index != size) {
				currentWeapon = Weapons.get(index);
			     }
			else {
				currentWeapon = Weapons.get(0);
			     }
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
			finally{
				System.out.println("Tip of the day : if you want a new Weapon, go to Shop!");
			}
			}
	 
	 public int getPlayersWeaponForce() {
	    	return currentWeapon.getAttackForce();
	    }
	    
	 public int getPlayersWeaponRange() {
	    	return currentWeapon.getAttackRange();
	    }
	 
	 public void addMoney(int ExtraMoney) {
			FamilyMoney = FamilyMoney + ExtraMoney;
		}
	@Override
	 public void buy(String buttonx) {
			if(buttonx == "button") {
				int sum = FamilyMoney - this.shovel.getPrice();
				if(sum>=0) {
							if(!this.Weapons.contains(this.shovel)) {
							this.Weapons.add(this.shovel);
							FamilyMoney -= this.shovel.getPrice();
							playSound("Resources/Sounds/money.wav");
							}
						}
			
				}
			else if(buttonx == "button1") {
				int sum = FamilyMoney - this.knife.getPrice();
				if(sum>=0) {
						if(!this.Weapons.contains(this.knife)) {
						this.Weapons.add(this.knife);
						FamilyMoney -= this.knife.getPrice();
						playSound("Resources/Sounds/money.wav");
						}
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
		
/*else {
	//TODO Sounds i2i2
}*/

