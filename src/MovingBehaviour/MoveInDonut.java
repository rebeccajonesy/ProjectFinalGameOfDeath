package MovingBehaviour;

import java.util.Random;

import Environment.Grid;
import MovingBehaviour.Move.MoveDirection;
import People.Human;

public class MoveInDonut implements Move {
	@Override
	public int[] getMove() {
		MoveDirection randomMove = MoveDirection.values()[(int) (Math.random() * MoveDirection.values().length)];

		int[] move = new int[]
				{0,0};
			   //x,y
		switch(randomMove){ 

		case E: // check 
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

	@Override
	public boolean isValidMove(int x, int y) {
		return false;
	}

	@Override
	public void movePerson(Human h, int x, int y) {
		if(x<=0){
			if(y>=0 && y<=Grid.numRows){
				x = Grid.numCols-1;
			}
			//else
				//x = Grid.numCols;
			//	y = Grid.numRows;
		}

		if(y<=0){
			if(x>=0 && x<=Grid.numCols){
				y = Grid.numRows-1;
				}
			//else
				//x = Grid.numCols;
			//	y = Grid.numRows;
			}
		
		if(x>=Grid.numCols){
			if(y>=0 && y<=Grid.numRows){
					x = 2;
				}
			//else
				//x = 1;
				//y = 1;
		}	
					
		if(y >=Grid.numRows){
			if(x>=0 && x<=Grid.numCols){
					y = 2;
				}
			//else
				//x = 1;
				//y = 1;
		}
						
	
		h.setx(x);
		h.sety(y);
	}
}

