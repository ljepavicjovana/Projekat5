
public class Enemy extends GameObject implements Attacker {
    private String type;
    private int damage; 
    private int health; 

    public Enemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(x, y, collider);
        setType(type);
        setDamage(damage);
        setHealth(health);
    }

    public String getType() { return type; }

    public void setType(String type) {
        if (type == null) throw new IllegalArgumentException("Tip ne smije biti null");
        type = type.trim();
        if (type.isEmpty()) throw new IllegalArgumentException("Tip ne smije biti prazan");
        this.type = type;
    }


    public int getDamage() { return damage; }

    public void setDamage(int damage) {
        if (damage < 0 || damage > 100) throw new IllegalArgumentException("Damage mora biti  0-100");
        this.damage = damage;
    }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        if (health < 0 || health > 100) throw new IllegalArgumentException("Health mora biti 0-100");
        this.health = health;
    }

    @Override
    public int getEffectiveDamage() {
        return damage;
    }

   
    

    @Override
    public String toString() {
        return "Enemy{type=" + type + ", damage=" + damage + ", health=" + health +
               ", x=" + getX() + ", y=" + getY() + ", collider=" + getCollider().getDescription() + "}";
    }
    @Override
    public String getDisplayName() {
        return type + " (HP: " + health + ", DMG: " + damage + ")";
    }

}
