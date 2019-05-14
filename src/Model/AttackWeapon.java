package Model;

public class AttackWeapon {
	private final int attackForce;
	private final int attackRange;
	private final int price;
	
	
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