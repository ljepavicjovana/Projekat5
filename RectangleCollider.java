
public class RectangleCollider implements Collidable {
    private int x;
    private int y;
    private final int width;
    private final int height;

    public RectangleCollider(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                "RectangleCollider dimenzije moraju biti pozitivne : width=" + width + ", height=" + height
            );
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Gornje-lijevo
    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public int getLeft() { return x; }
    public int getTop() { return y; }
    public int getRight() { return x + width; }
    public int getBottom() { return y + height; }

    @Override
    public boolean intersects(Collidable other) {
        if (other instanceof RectangleCollider) {
            RectangleCollider r = (RectangleCollider) other;
            return this.getLeft() < r.getRight()
                && this.getRight() > r.getLeft()
                && this.getTop() < r.getBottom()
                && this.getBottom() > r.getTop();
        } else if (other instanceof CircleCollider) {
            // Prebacujemo logiku na krug (da imamo jednu implementaciju circle-vs-rect)
            return other.intersects(this);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Rectangle[" + width + "x" + height + "] at (" + x + "," + y + ")";
    }
}
