package View;

import Model.FarmGameObjects;
import Model.Attachable;
import Model.GameObject;
import Model.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;

public class Window extends JFrame {
	private JPanel groupPanel = new JPanel(new BorderLayout());
    private Map map = new Map();
    private GUI GUI = new GUI();
    private boolean Farm = false;
 

    public Window(){
    	super("CHICKEN SIMS");
    	if(Farm == false) {
        // JFrame window = new JFrame("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 2000, 2000);
        this.getContentPane().setBackground(Color.gray);   
        groupPanel.add(map, BorderLayout.LINE_START);
        groupPanel.add(GUI, BorderLayout.LINE_END);
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
    	}

    }

    public void removeGameWindow() {
    	this.setVisible(false);
    }
    
    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }
    
    public void setFarmGameObjects(ArrayList<FarmGameObjects> farmObjects) {
    	this.map.setFarmObjects(farmObjects);
    	this.map.redraw();
    
    }
    public void setPlayers(ArrayList<Player> Players) {
    	this.map.setPlayers(Players);
    	this.map.redraw();
    }

    public void update() {
        this.map.redraw();
        this.GUI.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.map.addMouse(m);
    }

	public int getHouseSize() {
		return map.HOUSE_SIZE;
	}
	
	public int getOutsideSizeX() {
		return map.OUTSIDE_SIZEX;
	}
	
	public int getOutsideSizeY() {
		return map.OUTSIDE_SIZEY;
	}
	
	public void setGuiAttributes(ArrayList<GameObject> objects, ArrayList<Attachable> inventory, ArrayList<Player> players) {
		GUI.setGUIAttributes(objects, inventory,  players);
	}
	
	public void setPlayer(Player p) {
		GUI.setPlayer(p);
	}

}
