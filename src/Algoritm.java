import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
/**'
 * From this class all the algorithms will derive those methods and attributes that generic to 
 * all of them.
 * @author NviCoder
 *
 */
public class Algoritm  {
	
	protected int[] black;
	protected int[] red;
	protected int[] size;
	private int[][] gameBoard;
	protected int moves=0;
	protected int[][] goal;
	protected Node start;
	protected int openListIterations=0;
	
/*********************************Constructor*****************************************************************///

	public Algoritm( int[]  black,  int[]  red,int[] size,int[][] gameBoard) {
		this.black = black;
		this.red = red;
		this.size = size;
		this.gameBoard = gameBoard;
		int numberFillMatrixGoal = 1;
		this.goal = new int [size[0]][size[1]];
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[1];j++) {
				if(i == size[0]-1 && j == size[1]-1 ) {
					this.goal[i][j]=0;
				}
				else {
					this.goal[i][j]=numberFillMatrixGoal;
				}
				
				numberFillMatrixGoal++;
			}
		}
		start = new Node(gameBoard,0,"NULL",this.size); // init the first Node.
	}

/*********************************Getters and Setters*****************************************************************///



	public int[] getBlack() {
		return black;
	}


	public int[] getRed() {
		return red;
	}
	
/*********************************************************************************************************/     	
/*********************************Methods*****************************************************************/
/*********************************************************************************************************/     	
	
	/**
	 * Print the iteration on each 
	 * @param openList the hash table to print
	 * @param iteration which one is it. 
	 * @param open 
	 */
	protected void withOpen(Hashtable<String, Node> openList, int iteration) {
		System.out.println("This is the "+iteration+" itertion");
		System.out.println("");
		int counter =1;
		for (Entry<String, Node> entry : openList.entrySet()) {
		    String key = entry.getKey();
		    Node value = entry.getValue();
		    System.out.println ("("+counter+") "+"Key: " + key + " Value: " + value.getdir());
		    counter++;
		}
		System.out.println("");
		System.out.println("*********************************************");
		

		}
	

/*******************************************************************************************************///

	/**
	 * Find the Empty slot in the matrix 
	 * @param gameboard The state
	 * @return An Array with the indexes 
	 */
	protected int[] findEmptySlot(int[][]gameboard) {
		int[] indexesOfEmptySlot = new int[2];
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[1];j++) {
				if (gameboard[i][j] == 0) {
					indexesOfEmptySlot[0] = i;
					indexesOfEmptySlot[1] = j;
				}
			}
		}
		return indexesOfEmptySlot;
	}
	
	
/*******************************************************************************************************///
	
	/**
	 * This function finding the optional operator for the current node.
	 * @param gameboard The state of the current node
	 * @param position Shows where is the blank ("_") 
	 * @param father The current node.
	 * @return A set of the sons of the current node.
	 */
	protected Set<Node> findSons(int[][] gameboard, int[] position,Node father) {
		 Set<Node> sons = new HashSet<Node>();
		 int[][] stateForSon =  new int [size[0]][size[1]];
		 copyMatrix(stateForSon,gameboard);
		//***********EDGE CASES**************//
		 /***Corners***/
		// i=0,j=0
	        if(position[0] == 0 && position[1] == 0) { 
	        	if(!checkIfBlack(gameboard[1][0])) {//D
	        		Node son = moveIt(stateForSon,"D",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		
	        	}
	        	if(!checkIfBlack(gameboard[0][1])) { // R
	        		Node son = moveIt(stateForSon,"R",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	
	        }
	      // i=n,j=n
	        else  if(position[0] == this.size[0]-1 && position[1] == this.size[1]-1) { 
	        	if(!checkIfBlack(gameboard[this.size[0]-2][this.size[1]-1])) { // U
	        		Node son = moveIt(stateForSon,"U",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[this.size[0]-1][this.size[1]-2])) {//L
	        		Node son = moveIt(stateForSon,"L",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        }
	     // i=0,j=n
	        else  if(position[0] == 0 && position[1] == this.size[1]-1) { 
	        	if(!checkIfBlack(gameboard[0][this.size[1]-2])) { // L
	        		Node son = moveIt(stateForSon,"L",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[1][this.size[1]-1])) {//D
	        		Node son = moveIt(stateForSon,"D",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        	
	        }
	     // i=n,j=0
	        else if(position[0] == this.size[0]-1 && position[1] == 0) { 
	        	if(!checkIfBlack(gameboard[this.size[0]-2][0])) { // U
	        		Node son = moveIt(stateForSon,"U",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	
	        	if(!checkIfBlack(gameboard[this.size[0]-1][1])) {//R
	        		Node son = moveIt(stateForSon,"R",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        }
	        /******END Corners******/ 
	        /***Edges***/
	     // i=0,j=1...n-1
	        else if(position[0] == 0 && (position[1] != 0 && position[1] != this.size[1]-1) ) { 
	        	if(!checkIfBlack(gameboard[position[0]][position[1]-1])) {//L
	        		Node son = moveIt(stateForSon,"L",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	if(!checkIfBlack(gameboard[position[0]+1][position[1]])) { // D
	        		Node son = moveIt(stateForSon,"D",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]+1])) {//R
	        		Node son = moveIt(stateForSon,"R",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        	
	        
	        }
	        // i=1...n-1,j=0
	        else  if(position[1] == 0 && (position[0] != 0 && position[0] != this.size[0]-1) ) { 
	        	if(!checkIfBlack(gameboard[position[0]-1][position[1]])) { // U
	        		Node son = moveIt(stateForSon,"U",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[position[0]+1][position[1]])) {//D
	        		Node son = moveIt(stateForSon,"D",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]+1])) {//R
	        		Node son = moveIt(stateForSon,"R",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        	
	        	
	        	
	        }
	     // i=n,j=1...n-1
	        else  if(position[0] == this.size[0]-1 && (position[1] != 0 && position[1] != this.size[1]-1) ) { 
	        	if(!checkIfBlack(gameboard[position[0]-1][position[1]])) {//U
	        		Node son = moveIt(stateForSon,"U",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]-1])) { // L
	        		Node son = moveIt(stateForSon,"L",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]+1])) {//R
	        		Node son = moveIt(stateForSon,"R",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        }
	        // i=1...n-1,j=n
	        else if(position[1] == this.size[1]-1 && (position[0] != 0 && position[0] != this.size[0]-1) ) { 
	        	if(!checkIfBlack(gameboard[position[0]-1][position[1]])) { // U
	        		Node son = moveIt(stateForSon,"U",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]-1])) {//L
	        		Node son = moveIt(stateForSon,"L",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	if(!checkIfBlack(gameboard[position[0]+1][position[1]])) {//D
	        		Node son = moveIt(stateForSon,"D",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	
	        	
	        	
	        }
	        
	        /********END Edges***************************/ 
	        
	        /***********************EDGE CASES*******************************/
	        //Simple cases!! 
	        else {
	        	if(!checkIfBlack(gameboard[position[0]-1][position[1]])) { // U
	        		Node son = moveIt(stateForSon,"U",position);
	        		if(AvoidBackOperator(son,father)) {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        		}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]-1])) {//L
	        		Node son = moveIt(stateForSon,"L",position);
	        		if(AvoidBackOperator(son,father))  {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	if(!checkIfBlack(gameboard[position[0]+1][position[1]])) {//D
	        		Node son = moveIt(stateForSon,"D",position);
	        		if(AvoidBackOperator(son,father))  {// avoid go back to the same operator.
	        			sons.add(son);
		        		son.setFather(father);
		        		copyMatrix(stateForSon,gameboard);	
	        		}
	        	}
	        	if(!checkIfBlack(gameboard[position[0]][position[1]+1])) {//R
	        		Node son = moveIt(stateForSon,"R",position);
	        		if(AvoidBackOperator(son,father)) {
	        		
	        			sons.add(son);
		        		son.setFather(father);
		        			
	        		}
	        		
	        	}
	        	
	      	
	        }	        
		return sons;
	}
//*****************************************************************************************************************************//	

	/**
	 * This function will find the exect puzzle tiles that we moved last time and avoid do this again,
	 * by taking the first char of the dir of the node.
	 * @param son 
	 * @param father
	 * @return true or false.
	 */
private boolean AvoidBackOperator(Node son, Node father) {
	boolean flag = true;
	String sonOperator = son.getdir();
	sonOperator.substring(0,son.getdir().length()-1);
	String fatherOperator = father.getdir();
	fatherOperator.substring(0,son.getdir().length()-1);
	if(sonOperator.equals(fatherOperator))
		flag = false;
		
	return flag;
}

//*****************************************************************************************************************************//	
	/**
	 * This function copy one matrix to Other.
	 * 
	 * @param a Matrix A
	 * @param b Matrix B
	 */
	protected void copyMatrix(int[][] a, int[][] b) {
		for(int i=0; i<a.length; i++)
			  for(int j=0; j<a[i].length; j++)
			    a[i][j]=b[i][j];
		
	}
	
//*****************************************************************************************************************************//
	/**
	 * This function moves the gameBoard for the right direction
	 * @param stateForSon The game board.
	 * @param direction Where to move the puzzles
	 * @param position The position of the blank
	 * @return
	 */
	protected Node moveIt(int[][] stateForSon, String direction,int[]position) {
		// TODO Auto-generated method stub
		int[][] newState = stateForSon;
		int moveCost = 0;
		Node son;
		
		if(direction.equals("U")){ //move the puzzle number down to the "_" (=blank) that goes up.
			newState[position[0]][position[1]] = stateForSon[position[0]-1][position[1]]; //Move
			stateForSon[position[0]-1][position[1]]=0; // blank
			if(checkIfRed(newState[position[0]][position[1]])) {
				moveCost =  30;
			}
			else {
				moveCost =  1;
				
			}
		 son = new Node(newState,moveCost,Integer.toString(newState[position[0]][position[1]])+"D",this.size); //The direction the Node went to.	
		}
		
		else if(direction.equals("D")){//move the puzzle up to the "_" (=blank).
			newState[position[0]][position[1]] = stateForSon[position[0]+1][position[1]]; //Move
			stateForSon[position[0]+1][position[1]]=0; // blank
			if(checkIfRed(newState[position[0]][position[1]])) {
				moveCost =  30;
			}
			else {
				moveCost =  1;
				
			}
			 son = new Node(newState,moveCost,Integer.toString(newState[position[0]][position[1]])+"U",this.size); //The direction the Node went to.	
		}
		else if(direction.equals("R")){//move the puzzle left to the "_" (=blank).
			newState[position[0]][position[1]] = stateForSon[position[0]][position[1]+1]; //Move
			stateForSon[position[0]][position[1]+1]=0; // blank
			
			if(checkIfRed(newState[position[0]][position[1]])) {
				moveCost =  30;
			}
			else {
				moveCost =  1;	
			}
			 son = new Node(newState,moveCost,Integer.toString(newState[position[0]][position[1]])+"L",this.size); //The direction the Node went to.	
		}
		
		else{//if(direction.equals("L")){
			newState[position[0]][position[1]] = stateForSon[position[0]][position[1]-1]; //Move
			stateForSon[position[0]][position[1]-1]=0; // blank
			if(checkIfRed(newState[position[0]][position[1]])) {
				moveCost =  30;
			}
			else {
				moveCost =  1;
				
			}
			 son = new Node(newState,moveCost,Integer.toString(newState[position[0]][position[1]])+"R",this.size); //The direction the Node went to.	
		}

		return son;
	}
	
//*****************************************************************************************************************************//
	/**
	 * Find the path to the root from some Node v.
	 * @param v The Node
	 * @return String of the path.
	 */
	protected String findHispathToRoot(Node v) {
		if (v.getFather()==null)
			return "";
		else
		return findHispathToRoot(v.getFather())+"-"+ v.getdir();
		
	}
//*****************************************************************************************************************************//
	/**
	 * Find How much is cost from some Node v.
	 * 
	 * @param v Node.
	 * @return Integer with the sum.
	 */
	protected int findHisCostToRoot(Node v) {
			if (v.getFather()==null)
				return 0;
			else
			return v.getCost()+findHisCostToRoot(v.getFather());
	}

//*****************************************************************************************************************************//
	/**
	 * This function checks if the given puzzle tile is black!
	 * @param number The tile
	 * @return True - black. False - Not black.
	 */
	protected boolean checkIfBlack(int number) {
		boolean flag = false;
		if(this.getBlack()!=null) {
			for(int i=0; i<this.getBlack().length;i++) {
				if (this.getBlack()[i]==number)
					flag = true;
			}	
		}
		return flag;
	}
//*****************************************************************************************************************************// 	
	/**
	 * This function checks if the given puzzle tile is Red!
	 * @param number  The tile
	 * @return True - black. False - Not black.
	 */
	protected boolean checkIfRed(int number) {
		boolean flag = false;
		if(this.getRed()!=null) {
			for(int i=0; i<this.getRed().length;i++) {
				if (this.getRed()[i]==number)
					flag = true;
			}
		}
		return flag;
	}
//*****************************************************************************************************************************// 	
	
	/**
	 * This function delete some Node from any place in the stack.
	 * And the most important saving the original order.
	 * @param stack That we are going to delete from her a node. 
	 * @param node the node we want to delete from the stack.
	 */
	protected static void deleteNodeFromStack(Stack<Node> stack, String node) {
		Stack<Node> temp =new Stack<Node>();
		boolean flag = false;
		while(!stack.isEmpty()&& flag == false) {
			Node v = stack.pop();
			if (v.getID().equals(node)) 
			{
				flag=true;
				if (!temp.isEmpty())
				{
					while(!temp.isEmpty())
						stack.push(temp.pop());
				}
			}
			else
			{
			temp.push(v);
			}
		}
		
		
		
	}
	
	
//*****************************************************************************************************************************//	
	/**
	 * This function checks if the given node is The goal we looking for.
	 * @param son The Node. 
	 * @return True - This is the goal!!!. False - if not.
	 */
	protected boolean isGoal(Node son) {
		boolean flag = true;
		for(int i=0;i<this.size[0];i++) {
			for(int j=0;j<this.size[1];j++) {
				if(son.getState()[i][j]!= this.goal[i][j])
					flag=false;
			}
		}
		return flag;
	}
	
	
	
}
	
