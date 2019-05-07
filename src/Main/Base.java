package Main;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import Controller.Mouse;
import Controller.Keyboard;
import Model.Game;
import View.Map;
import View.Window;
import Main.PlayWindow;

public class Base implements ActionListener {

 
	public void actionPerformed(ActionEvent e) {
		
		PlayWindow.frame.dispose();
		try {
			String Difficulity  = PlayWindow.Difficulity.getText();
			String HOUSE_SIZE = PlayWindow.MapSize.getText();
			
			Game.setMapSize(Integer.valueOf(HOUSE_SIZE));
			Game.setDifficulity(Integer.valueOf(Difficulity));
			Map.setMapSize(Integer.valueOf(HOUSE_SIZE));
			Window window = new Window();
	        Game game = new Game(window);
		
			 //prendre ce qui a été écrit dans l'une des case de PlayWindow
			
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
}
        
      
	

