package Main;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import Controller.Mouse;
import Controller.Keyboard;
import Model.Game;
import View.Buttons;
import View.Map;
import View.Window;
import Main.PlayWindow;

public class Base implements ActionListener {

 
	public void actionPerformed(ActionEvent e) {
		
		PlayWindow.frame.dispose();
		Window window = new Window();
		//FarmWindow window1 = new FarmWindow(); //A remmettre!!!!!!
		
		String SIZE = PlayWindow.MapSize.getText(); //prendre ce qui a été écrit dans l'une des case de PlayWindow
		Game.setMapSize(Integer.valueOf(SIZE));
		
		
		String Breakabla_Blocks = PlayWindow.BreakableBlocks.getText(); //idem
		Game.numberOfBreakableBlocks = Integer.valueOf(Breakabla_Blocks);
		
		String Difficulity = PlayWindow.Difficulity.getText(); //prendre ce qui a été écrit dans l'une des case de PlayWindow
		Game.setDifficulity(Integer.valueOf(Difficulity));

        Game game = new Game(window, /*window1,*/ Buttons.i3);  
        

        
        Keyboard keyboard = new Keyboard(game);
        window.setKeyListener(keyboard);
        
        
        
        /* Mouse mouse = new Mouse(game);
        window.setMouseListener(mouse);*/
	}
}
        
      
	

