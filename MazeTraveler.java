import java.awt.Point;

/**
 * The dude who will take the directions and traverse the maze, checking if he doesn't run into a wall
 */
public class MazeTraveler {
	private Direction direction;
	private Point position;

	public MazeTraveler(Direction d, Point p){
		this.direction = d;
		this.position = p;
	}

	/**
	 * Given a set of directions, return true if it can be traveresed, false otherwise
	 */
	public boolean canTraverse(String directions, String[] maze){
		for (char c : directions.toCharArray()){
			if (c == 'r'){
				this.rotate(-1);
			}
			else if (c == 'l'){
				this.rotate(1);
			}
			else {
				int steps = Character.getNumericValue(c);
				for (int i = 0; i < steps; i++){
					if (willHitWall(maze)){
						return false;
					}
					else {
						moveForward();
					}
				}
			}
		}

		return true;
	}

	/**
	 * Change the direction
	 */
	private void rotate(int dir){
		// rotate counter clockwise (left)
		if (dir == 1){
			switch (this.direction){
				case UP:
					this.direction = Direction.LEFT;
					break;
				case RIGHT:
					this.direction = Direction.UP;
					break;
				case DOWN:
					this.direction = Direction.RIGHT;
					break;
				case LEFT:
					this.direction = Direction.DOWN;
					break;
			}
		}
		// rotate clockwise (right)
		else if (dir == -1) {
			switch (this.direction){
				case UP:
					this.direction = Direction.RIGHT;
					break;
				case RIGHT:
					this.direction = Direction.DOWN;
					break;
				case DOWN:
					this.direction = Direction.LEFT;
					break;
				case LEFT:
					this.direction = Direction.UP;
					break;
			}
		}
	}

	/**
	 * Check if there is a wall in front of the traveler before moving forward
	 */
	private boolean willHitWall(String[] maze){
		switch (this.direction){
			case UP:
				return maze[(int)this.position.getY()-1].charAt((int)this.position.getX()) != ' ';
			case RIGHT:
				return maze[(int)this.position.getY()].charAt((int)this.position.getX()+1) != ' ';
			case DOWN:
				return maze[(int)this.position.getY()+1].charAt((int)this.position.getX()) != ' ';
			case LEFT:
				return maze[(int)this.position.getY()].charAt((int)this.position.getX()-1) != ' ';
		}
		return false;
	}

	private void moveForward(){
		int x = (int)this.position.getX();
		int y = (int)this.position.getY();
		switch (this.direction){
			case UP:
				this.position.setLocation(x,y-1);
				break;
			case RIGHT:
				this.position.setLocation(x+1,y);
				break;
			case DOWN:
				this.position.setLocation(x,y+1);
				break;
			case LEFT:
				this.position.setLocation(x-1,y);
				break;
		}
	}

	public void setDirection(Direction d){
		this.direction = d;
	}

	public Direction getDirection(){
		return this.direction;
	}

	public void setPosition(Point p){
		this.position = p;
	}

	public Point getPosition(){
		return this.position;
	}
}