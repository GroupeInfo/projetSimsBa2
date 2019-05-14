package Controller;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import Model.Game;


public class Keyboard implements KeyListener {
    private Game game;

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
    	if(game.SleepingStateOfPlayer() == false) {
    		
    	
	        int key = event.getKeyCode();
	
	        switch (key) { 
	       
	        case KeyEvent.VK_RIGHT :
	            game.movePlayer(1, 0);
	            break;
	        case KeyEvent.VK_LEFT:
	            game.movePlayer(-1, 0);
	            break;
	        case KeyEvent.VK_DOWN:
	            game.movePlayer(0, 1);
	            break;
	        case KeyEvent.VK_UP:
	            game.movePlayer(0, -1);
	             break;
	         case KeyEvent.VK_SPACE:
	             game.action();
	             break;
	         case KeyEvent.VK_Q:
	             game.stop();
	             break;
	         case KeyEvent.VK_T:
	             game.makePlayerTired();
	             break;
	         case KeyEvent.VK_Y:
	        	 game.makePlayerHungry();
	        	 break;
	         case KeyEvent.VK_U:
	        	 game.makePlayerDirty();
	        	 break;
	        case KeyEvent.VK_P:
	             game.playerPos();
	             break;
	        case KeyEvent.VK_1:
	        	 game.useInventoryItem(0);
	        	 break;
	        case KeyEvent.VK_2:
	       	 game.useInventoryItem(1);
	       	 	 break;
	        case KeyEvent.VK_3:
	       	 game.useInventoryItem(2);
	       	 	 break;
	        case KeyEvent.VK_4:
	       	 game.useInventoryItem(3);
	       	 	 break;
	        case KeyEvent.VK_A:
	        	game.addToInventory();
	        	break;
	        case KeyEvent.VK_SHIFT:
	        	game.setPlayerWeapon();
	        	break;
	        case KeyEvent.VK_M:
	        	game.changePlayer();
	        	break;
	        }
    	}
        
      }
    	

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
