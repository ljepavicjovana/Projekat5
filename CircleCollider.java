
public class CircleCollider implements Collidable {
    private int cx;
    private int cy;
    private final int radius;

    public CircleCollider(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Circle radius mora biti veci od 0 ");
        }
        this.radius = radius;
    }

    @Override
    public void setPosition(int x, int y) {
        this.cx = x;
        this.cy = y;
    }

    @Override
    public int getX() {
    	return cx;
    	}

    @Override
    public int getY() {
    	return cy;
    }

    public int getRadius() { 
    	return radius; 
    	}
//ovo sada znaci da ce mi biti u intervalu minimum do maksimum 
    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public boolean intersects(Collidable other) {
        if (other instanceof CircleCollider) {
            CircleCollider c = (CircleCollider) other;
            int dx = this.cx - c.cx;
            int dy = this.cy - c.cy;
            int rsum = this.radius + c.radius;
            return dx * dx + dy * dy <= rsum * rsum;
        } else if (other instanceof RectangleCollider) {
            RectangleCollider r = (RectangleCollider) other;
            int closestX = clamp(cx, r.getLeft(), r.getRight());
            int closestY = clamp(cy, r.getTop(), r.getBottom());
            //clamp smo koristili u grupnom projektu desete nedelje zato sto nam clamp u ovom slucaju pomaze da vratimo vrijednost koja je data u zadatu 
            int dx = cx - closestX;
            int dy = cy - closestY;
            return dx * dx + dy * dy <= radius * radius;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Circle[r=" + radius + "] at (" + cx + "," + cy + ")";
    }
}
