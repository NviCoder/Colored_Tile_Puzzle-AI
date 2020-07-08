import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;
/**
 * This IDA_Star algorithm class.
 * @author NviCoder
 *
 */
public class IDA_Star extends Algoritm  {


//******************Constructor***************************************************//

	public IDA_Star(int[] black, int[] red,int[] size, int[][] gameBoard) {
		super(black, red, size, gameBoard);
		
	}
	
/////******************************* ALGORITHM ******************************************************////	
	
	/**
	 * This is the algorithm!
	 * @param time yes or not to print.
	 * @param open yes or not to print. 
	 * @throws IOException if there is any problem with the .txt files.
	 */
	public void start(boolean time,boolean open) throws IOException{
		long START = System.currentTimeMillis();//Time
		NodeEvaluationComparator evaluation = new NodeEvaluationComparator(this);
		boolean flagEndLoop = false;
		output out = new output();
		Stack<Node> stack = new Stack<Node>();
	    Hashtable<String, Node> hashTable = new Hashtable<String, Node>();
	    int threshold = evaluation.HeuristicFunction(this.start);//for the Evaluation function.
	   
	    
	    while(threshold != Integer.MAX_VALUE) 
	    {
	    	int minThreshold = Integer.MAX_VALUE;
	    	this.start.out=false;
	    	stack.push(this.start);
	    	hashTable.put(this.start.getID(), this.start);
	    	
	    	while(!stack.isEmpty()) 
	    	{
	    		
	    		Node vertex = stack.pop();
	    		if(vertex.getOut()) 
	    		{
	    			hashTable.remove(vertex.getID());
	    		}
	    		else 
	    		{
	    			vertex.setOut();
	    			stack.push(vertex);
	    			int [] EmptySlot = findEmptySlot(vertex.getState());
	    	        //finding the allowed sons of this vertex
	    	        Set<Node> sons = findSons(vertex.getState(), EmptySlot,vertex);
	    	        for(Node son: sons) 
	    	        {	
	    	        	this.moves++;
	    	        	int sonThreshold = evaluation.EvaluationFunction(son);
	    	        	
	    	        	if(sonThreshold>threshold) 
	    	        	{
	    	        		minThreshold = Math.min(minThreshold, sonThreshold);
	    	        	}
	    	        	//loop avoidance
	    	        	else if(hashTable.containsKey(son.getID()) && son.getOut()){
	    	        		//continue with the next operator.
	    	        	}
	    	        	else if(hashTable.containsKey(son.getID()) && !son.getOut()) {
	    	        		if(evaluation.EvaluationFunction(hashTable.get(son.getID())) > sonThreshold)
	    	        				{
	    	        					hashTable.remove(son.getID());
	    	        					//remove form stack
	    	        					deleteNodeFromStack(stack,son.getID());
	    	        					
	    	        				}
	    	        		else {
	    	        			//continue
	    	        		}
	    	        		
	    	        	}
	    	        	
	    	        	else if(isGoal(son)) 
	    	        	{
	    	        		long END =  System.currentTimeMillis();
	    	    			float seconds = (END-START) / 1000F;
	    				    out.make(findHispathToRoot(son),hashTable.size(),findHisCostToRoot(son),seconds,time,true);
	    				    stack.clear();//End the while loop.
	    				    threshold = Integer.MAX_VALUE;
	    				    flagEndLoop = true;
	    				    break;    
	    	        	}
	    	        	
	    	        	 else { 
	    	        	
	    	        		stack.push(son);
		    		    	hashTable.put(son.getID(), son);	
	    	        	 }
	    	        	
	    	        }
	    		}
	    	
	    		if(open) {
		    		openListIterations++;
		    		withOpen(hashTable,openListIterations);
		    	}
	    		
	    	}
	    	if(!flagEndLoop)
	    		threshold = minThreshold;
	    	
	    }
	
	}
	
/////*******************************END OF ALGORITHM******************************************************////	
	
	
}
