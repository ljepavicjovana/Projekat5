
public abstract class GameObject {
    private int x;
    private int y;
    private Collidable collider;

    protected GameObject(int x, int y, Collidable collider) {
        setX(x);
        setY(y);
        setCollider(collider);
    }

   

    public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}



	public void setX(int x) {
		this.x = x;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Collidable getCollider() { return collider; }
    public void setCollider(Collidable collider) {
        if (collider == null) throw new IllegalArgumentException("Collider ne smije biti  null");
        this.collider = collider;
    }

    public boolean intersects(GameObject other) {
        return this.collider.intersects(other.getCollider());
    }

    public abstract String getDisplayName();

    @Override
    public String toString() {
        return "GameObject{name=" + getDisplayName() + ", x=" + x + ", y=" + y + "}";
    }
}
