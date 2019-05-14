package Model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Saver implements Serializable {
	private int chickenKilled;
	
	public Saver(int chickenKilled) {
		this.chickenKilled = chickenKilled;
		
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\hassa\\Desktop\\ChickenKilled.txt");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(this);
			System.out.println("Object has been serialized successfully");
			os.close();
			System.out.println("Closing");
		}
		catch(Exception e) {
			e.printStackTrace();
			}
	}

	public int getChickenKilled() {
		return chickenKilled;
	}

}
	