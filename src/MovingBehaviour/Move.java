package MovingBehaviour;

import Environment.Grid;
import People.Human;

public interface Move {
	public static enum MoveDirection{ N, W , S , E, NW , SW, SE, NE };
	//, NW , SW, SE, NE
	//public void move(Grid g, int x, int y,  int dirX, int dirY);
	 public int[]getMove();
	 public boolean isValidMove(int x, int y);
	 public void movePerson(Human h, int x, int y);
}
