package Controller;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import Model.Game;
import View.ShopMenu;

public class Mouse implements MouseListener {
    private Game game;
    private ShopMenu shop;

    public Mouse(Game game, ShopMenu shop) {
        this.game = game;
        this.shop = shop;
    }
    
    
    
	public void mapEvent(int x, int y) {
		synchronized(game) {
			game.sendPlayer(x, y);
		}
	}

	public void mouseClicked(MouseEvent e) {
		Object s = e.getSource();
		if(shop.getButtonClicked(s) == "button") {
			System.out.println(shop.getButtonClicked(s)); 
			game.removeMoneyFromPlayer(50);
		}
		else if(shop.getButtonClicked(s) == "button1") {
			game.removeMoneyFromPlayer(150);
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
