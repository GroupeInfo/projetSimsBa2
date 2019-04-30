package Model;

public class AttackWeapon {
	private int attackForce;
	private int attackRange;
	
	public AttackWeapon(int force, int range) {
		attackForce = - force;
		attackRange = range;
	}
	
	public int getAttackForce() {
		return attackForce;
	}
	
	public int getAttackRange() {
		return attackRange;
	}


}