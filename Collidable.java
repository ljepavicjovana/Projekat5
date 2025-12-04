
public interface Collidable {
	
		boolean intersects(Collidable other);
		void setPosition(int x , int y);
		//u sledecoj RectangleCollider mi treba ovo 
		int getX();
	    int getY();
	    
		String getDescription();
	}


