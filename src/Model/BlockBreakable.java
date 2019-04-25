package Model;
import java.util.ArrayList;

public class BlockBreakable extends Block implements Deletable, Activable {

    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private int lifepoints = 0;
    public BlockBreakable(int X, int Y, int lifepoints) {
        super(X, Y, 1);
        this.lifepoints = lifepoints; 
    }
    
    public void activate(){
        if (lifepoints == 1){
            crush();
        }
        else {
            lifepoints--;
            this.color = lifepoints + 2; // pour éviter de retourner au gris
        }
    }


    public void crush(){
        notifyDeletableObserver();
    }
    // //////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachDeletable(DeletableObserver po) {
        observers.add(po); //c'est ici qu'on rajoutte des deletableObserver différent et que la métode delete va jouer différement.
    }

    @Override
    public void notifyDeletableObserver() {
        int i = 0;
        for (DeletableObserver o : observers) {
            i++;
            o.delete(this, null); //creer une liste d'objet blockbreakable si on veut tuer celui-ci, on fait appele a cette methode depuis game
        }
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

}
