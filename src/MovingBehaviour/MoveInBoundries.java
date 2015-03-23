package MovingBehaviour;

import java.util.Random;

import Environment.Grid;
import People.Human;

public class MoveInBoundries implements Move{

	@Override
	public int[] getMove() {
		MoveDirection randomMove = MoveDirection.values()[(int) (Math.random() * MoveDirection.values().length)];

		System.out.println(randomMove);
		int[] move = new int[]
				{0,0};
			   //x,y
		switch(randomMove){ 
		case E: 
			move[0]+=1;
			break;
		case W:
			move[0]-=1;
			break;
		case N:
			move[1]-=1;
			break;
		case S:
			move[1]+=1;
			break;
		case SE:
			move[0] +=1;
			move[1] +=1;
			break;
		case NW:
			move[0] -=1;
			move[1] -=1;
			break;
		case SW:
			move[0] -=1;
			move[1]	+=1;
			break;
		case NE:
			move[0] +=1;
			move[1] -=1;
			break;			
			default:
			
		}
		return move;
	}

	public void movePerson(Human h, int x, int y){
		if(isValidMove(x,y)){
			h.setx(x);
			h.sety(y);
		}
		else
			return;
	}
	@Override
	public boolean isValidMove(int x, int y) {
		return 	x>0 && x<Grid.numCols && y>0 && y<Grid.numRows;
		
	}
}
