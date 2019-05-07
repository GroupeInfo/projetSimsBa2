package Model;

public class AttackWeapon {
	private int attackForce;
	private int attackRange;
	private int price;
	
	
	public AttackWeapon(int force, int range, int price) {
		attackForce = - force;
		attackRange = range;
		this.price = price;
	}
	
	public int getAttackForce() {
		return attackForce;
	}
	
	public int getAttackRange() {
		return attackRange;
	}
	
	public int getPrice() {
		return price;
	}


}