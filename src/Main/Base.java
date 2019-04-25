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

		Window window = new Window();
		PlayWindow.frame.dispose();
		
		String SIZE = PlayWindow.MapSize.getText(); //prendre ce qui a �t� �crit dans l'une des case de PlayWindow
		Game.houseSize = Integer.valueOf(SIZE);
		
		
		String Breakabla_Blocks = PlayWindow.BreakableBlocks.getText(); //idem
		Game.numberOfBreakableBlocks = Integer.valueOf(Breakabla_Blocks);
		
		String PixelNumber = PlayWindow.PixelNumber.getText();//idem
		Map.BLOC_SIZE = Integer.valueOf(PixelNumber);

        Game game = new Game(window, Buttons.i3);
        
        Keyboard keyboard = new Keyboard(game);
        window.setKeyListener(keyboard);
        Mouse mouse = new Mouse(game);
        window.setMouseListener(mouse);
	}
}
