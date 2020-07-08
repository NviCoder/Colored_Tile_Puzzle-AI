import java.util.Comparator;
import java.util.PriorityQueue;

public class NodeEvaluationComparator implements Comparator<Node> {
//	private static Algoritm algo = new A_Star( new int[]{ 11 }, new int[]{ 9,10 }, new int[]{ 3,4 }, new int[][]{{1,2,3,4},{5,6,7,8},{9,10,0,11}});
	private  Algoritm algo;

	public NodeEvaluationComparator () {
		
	}
	
	public NodeEvaluationComparator (Algoritm algo) {
		this.algo = algo;
	}
	@Override
	public int compare(Node v1, Node v2) {
		int rateV1= EvaluationFunction(v1);
		int rateV2= EvaluationFunction(v2);
		if (rateV1 > rateV2) 
	        return 1; 
	    else if (rateV1 < rateV2) 
	        return -1; 
	    return 0;
		
	} 
	        
/*******************************************************************************************************///
		
	        /**
	         * This is The Evaluation Function.
	         * This function consists of two parts  F(n)= g(n) + h(n).
	         * g(n) - The cost of this actual node. 
	         * h(n) - A HeuristicFunction.
	         * 
	         * @param v The node we want to give a grade.
	         * @return grade that will give some indication.
	         */
	        public  int EvaluationFunction(Node v) {
				// TODO Auto-generated method stub
				return HeuristicFunction(v)+algo.findHisCostToRoot(v);
			}

/*******************************************************************************************************///

			/**
	         * This is Heuristic Function.
	         * This function will be an inclusion of Manhattan function
	         * first, we will take the distance of the x + y of the grid.
	         * second, we will multiply the cost of this piece.
	         *  
	         * @param v The node to compute
	         * @return Grade for this node. 
	         */
	        public  int HeuristicFunction(Node v) {
				// TODO Auto-generated method stub
				int grade=0;
				for(int i=0 ; i<v.getState().length;i++)
					for(int j=0 ; j<v.getState()[0].length;j++)
						grade+=(manhattan(v.getState()[i][j],i,j)*findTheColorCost(v.getState()[i][j]));
						
				return grade;
			
			}

			

/*******************************************************************************************************///
			/**
			 * Returns the amount to multiply the pieces on the puzzle board
			 * @param i The i,j to check
			 * @return
			 */
			private  int findTheColorCost(int i) {
				// TODO Auto-generated method stub
				if(algo.checkIfBlack(i))
					return 200;
				else if(algo.checkIfRed(i))
					return 100;
				else return 1;
			}
			/**
			 * This is The Manhattan Function.
			 * @param number the piece we moving
			 * @param row The index of the row that the piece now in this state.
			 * @param col The index of the column that the piece now in this state.
			 * @return
			 */
			private  int manhattan(int number,int row , int col) {				
				//look for the index index in the goal
				int Row=0,Col=0;
				for(int i = 0;i<algo.size[0];i++)
					for(int j = 0;j<algo.size[1];j++)
						if(algo.goal[i][j] == number) {
							Row=i;
							Col=j;
						}	
				// Subtraction 
				int stepsToPlace =  Math.abs(row - Row) +  Math.abs(col-Col);
				return stepsToPlace;
			}
}
