import java.util.Scanner;
import java.awt.Point;
import java.util.ArrayList;

public class Maze {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		/* Read from stdin */
		int height = Integer.parseInt(scan.nextLine()); // use nextLine b/b nextInt does not kill the newline
		String[] maze = new String[height];
		for (int i = 0; i < height; i++){
			maze[i] = scan.nextLine();
		}
		String directions = scan.nextLine();

		ArrayList<String> paths = new ArrayList<>();

		/* Find and test all empty spaces in all directions in the maze */
		for (int y = 0; y < maze.length; y++){
			for (int x = 0; x < maze[y].length(); x++){
				if (maze[y].charAt(x) == ' '){
					// found a potential space; begin traversing
					for (Direction d : Direction.values()){
						MazeTraveler mt = new MazeTraveler(d, new Point(x,y));
						if (mt.canTraverse(directions, maze)){
							Point dest = mt.getPosition();
							paths.add("From (" + x + ", " + y + ") to (" + (int)dest.getX() + ", " + (int)dest.getY() + ")");
						}
					}
				}
			}
		}

		/* Print results */
		for (String path : paths){
			System.out.println(path);
		}
	}
}