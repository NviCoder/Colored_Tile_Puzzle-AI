import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;

/**
 * This is The DFnB algorithm!!
 * @author NviCoder
 *
 */
public class DFBnB extends Algoritm {
	private  NodeEvaluationComparator fn;
	
	//******************Constructor***************************************************//
		
		public DFBnB(int[] black, int[] red,int[] size, int[][] gameBoard) {
			super(black, red, size, gameBoard);
					
		}
		

		
	/////******************************* ALGORITHM ******************************************************////	


		/**
		 * This is the algorithm!
		 * @param time yes or not
		 * @param open yes or not
		 * @throws IOException if there is any problem with the .txt files.
		 */
		public void start(boolean time,boolean open) throws IOException
		{
			long START = System.currentTimeMillis();//Time
			output out = new output();
			boolean flagEndLoop = false; 
			fn = new NodeEvaluationComparator(this);
			Stack<Node> stack =new Stack<Node>();
			Hashtable<String, Node> hashTable = new Hashtable<String, Node>();
			
			
			int result;
			int threshold = Integer.MAX_VALUE;
			
			stack.push(this.start);
			hashTable.put(this.start.getID(), this.start);
			
			while(!stack.isEmpty()) {
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
	    	        Node []sortedNodes = sortIt(sons);
	    	        for(int i = 0;i<sortedNodes.length;i++)
	    	        {
	    	        	int fnSon = fn.EvaluationFunction(sortedNodes[i]);
	    	        	if(fnSon>=threshold) 
	    	        	{
	    	        		//remove all the Nodes after this one
	    	        		i=sortedNodes.length;
	    	        	}
	    	        	//loop avoidance
	    	        	else if(hashTable.containsKey(sortedNodes[i].getID()) && sortedNodes[i].getOut())
	    	        	{
	    	        		//continue with the next operator.
	    	        	}
	    	        	else if(hashTable.containsKey(sortedNodes[i].getID()) && !sortedNodes[i].getOut()) 
	    	        	{
	    	        		if(fn.EvaluationFunction(hashTable.get(sortedNodes[i].getID())) <= fnSon)
	        				{
	    	        			sortedNodes[i]=null;
	        				}
	    	        		else {
	    	        			hashTable.remove(sortedNodes[i].getID());
	    	        			deleteNodeFromStack(stack,sortedNodes[i].getID());
	    	        		}
	    	        	
	    	        	
	    	        	}
	    	        	else if(isGoal(sortedNodes[i])) 
	    	        	{
	    	        		long END =  System.currentTimeMillis();
	    	    			float seconds = (END-START) / 1000F;
	    	    			threshold = fn.EvaluationFunction(sortedNodes[i]);
	    				    out.make(findHispathToRoot(sortedNodes[i]),hashTable.size(),findHisCostToRoot(sortedNodes[i]),seconds,time,true);
	    				    //remove all the next operators.
	    				    i=sortedNodes.length;
	    				    flagEndLoop =true;
	    				    sortedNodes=null;
	    				    stack.clear();//End the while loop.
	    				    break;    
	    	        	}
	    	        	
	    	        }
	    	        if(!flagEndLoop) // avoid the null pointer!
	    	        {
		    	        for(int i =sortedNodes.length-1;i>=0;i--) {
		    	        	if(sortedNodes[i]!=null) {
		    	        		stack.push(sortedNodes[i]);
		    	    			hashTable.put(sortedNodes[i].getID(), sortedNodes[i]);
		    	        	}
		    	        }
				
	    	        }
				}
			
			
			
			
			}
		}
/////*******************************END OF ALGORITHM******************************************************////	
	
	/**
	 * The first place is the little value with the F(n)
	 * @param sons
	 * @return sons sorted
	 */
	private  Node[] sortIt(Set<Node> sons) {
		Node[] sortedNodes = new Node[sons.size()];// index =0 min, index =1 sortedNodes[1], index =2 max.
		Node[] Nodes = new Node[sons.size()];
//		int[] sortedHeuristicFunctionNodeValue = new int[3];
		int i=0;
		for(Node son: sons) 
		{
			//sortedHeuristicFunctionNodeValue[i]=this.fn.HeuristicFunction(son);
			Nodes[i]= son;
			i++;
	     }
		//System.out.println(Arrays.toString(Nodes));
		
		// First case - one node
		if (Nodes.length ==1) {
			return Nodes;
		}
		
		// Second case - two nodes
		else if(Nodes.length == 2)
		{
			int result = fn.compare(Nodes[0],Nodes[1]);
			if(result == 1) {
				sortedNodes[0] = Nodes[1];
				sortedNodes[1] = Nodes[0];
			}
			else if(result == -1) {
				sortedNodes[0] = Nodes[0];
				sortedNodes[1] = Nodes[1];
			}
			else {
				return Nodes;
			}
			
		}
		
		// Third case - three nodes.
		else {
			sortedNodes = sortThreeNodes(Nodes);
		}
	
		
		return sortedNodes;	
		
 }
/////*******************************END OF ALGORITHM******************************************************////	

/**
 * Sort Three Nodes
 * @param nodes
 * @return
 */
	private Node[] sortThreeNodes(Node[] nodes) {
		Node[] sortedNodes = new Node[3];
		if(fn.EvaluationFunction(nodes[0]) > fn.EvaluationFunction(nodes[1]) ){
		if( fn.EvaluationFunction(nodes[0]) >fn.EvaluationFunction(nodes[2]) ){
			 sortedNodes[2] = nodes[0];
		 if(  fn.EvaluationFunction(nodes[1]) > fn.EvaluationFunction(nodes[2]) ){
			  sortedNodes[1] = nodes[1];
			  sortedNodes[0] = nodes[2];
		 }else{
		  sortedNodes[1] = nodes[2];
		  sortedNodes[0] = nodes[1];
		 }
		}else{
		 sortedNodes[1] = nodes[0];
		 sortedNodes[2] = nodes[2];
		 sortedNodes[0] = nodes[1];
		}
		}else{
		if( fn.EvaluationFunction(nodes[1]) > fn.EvaluationFunction(nodes[2]) ){
		 sortedNodes[2] = nodes[1];
		 if( fn.EvaluationFunction(nodes[0]) > fn.EvaluationFunction(nodes[2]) ){
		  sortedNodes[1] = nodes[0];
		  sortedNodes[0] = nodes[2];
		 }else{
		  sortedNodes[1] = nodes[2];
		  sortedNodes[0] = nodes[0];
		 }
		}else{
		 sortedNodes[1] = nodes[1];
		 sortedNodes[2] = nodes[2];
		 sortedNodes[0] = nodes[0];
		}
		
		}
		return sortedNodes;
	}
}







