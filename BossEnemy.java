
public class BossEnemy extends Enemy {
	//sve sto Enemy ima
    public BossEnemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(type, damage, health, x, y, collider);
    }

    @Override
    
    public int getEffectiveDamage() {
    	//Klasa BossEnemy redefiniše getEffectiveDamage tako da vraća dvostruku vrijednost.
        return Math.min(100, super.getEffectiveDamage() * 2); 
    }

    @Override
    public String toString() {
        return "BossEnemy{type=" + getDisplayName() + ", effDamage=" + getEffectiveDamage() +
               ", health=" + getHealth() + ", x=" + getX() + ", y=" + getY() +
               ", collider=" + getCollider().getDescription() + "}";
    }
}
