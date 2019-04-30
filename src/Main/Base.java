package Main;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import Controller.Mouse;
import Controller.Keyboard;
//import Model.FarmGame; //A remmettre!!!!!!
import Model.Game;
import View.Buttons;
//import View.FarmWindow; //A remmettre!!!!!!
import View.Map;
import View.Window;
import Main.PlayWindow;

public class Base implements ActionListener {

 
	public void actionPerformed(ActionEvent e) {
		
		PlayWindow.frame.dispose();
		Window window = new Window();
		//FarmWindow window1 = new FarmWindow(); //A remmettre!!!!!!
		
		String SIZE = PlayWindow.MapSize.getText(); //prendre ce qui a été écrit dans l'une des case de PlayWindow
		Game.houseSize = Integer.valueOf(SIZE);
		
		
		String Breakabla_Blocks = PlayWindow.BreakableBlocks.getText(); //idem
		Game.numberOfBreakableBlocks = Integer.valueOf(Breakabla_Blocks);
		
		String PixelNumber = PlayWindow.PixelNumber.getText();//idem
		Map.BLOC_SIZE = Integer.valueOf(PixelNumber);

        Game game = new Game(window, /*window1,*/ Buttons.i3);  //A remmettre!!!!!!
        
        //FarmGame game = new FarmGame(FarmWindow, Buttons.i3);
        
        //FarmGame farmGame = new FarmGame(window1, window, Buttons.i3); //A remmettre!!!!!!
        
        Keyboard keyboard = new Keyboard(game);
        //FarmWindow.setKeyListener(keyboard);
        window.setKeyListener(keyboard);
        
        
        
        /* Mouse mouse = new Mouse(game);
        window.setMouseListener(mouse);*/
	}
}
        
      
	

