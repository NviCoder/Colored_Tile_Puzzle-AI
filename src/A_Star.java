import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


/**
 * This A_Star algorithm class.
 * @author NviCoder
 *
 */
public class A_Star extends Algoritm  {


//******************Constructor***************************************************//

	public A_Star(int[] black, int[] red,int[] size, int[][] gameBoard) {
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
		output out = new output();
		NodeEvaluationComparator comparator = new NodeEvaluationComparator(this);
		PriorityQueue<Node> queue = new PriorityQueue<Node>(comparator);
	    Hashtable<String, Node> openList = new Hashtable<String, Node>();
	    Hashtable<String, Node> closedList = new Hashtable<String, Node>();
	    
	  //Add the start Node to the queue.
	    queue.add(this.start);
	    openList.put(this.start.getID(), this.start);
	    //3.0
	    while(0 != queue.size()) 
	    {
	    	
	    	if(open) 
	    	{
	    		openListIterations++;
	    		withOpen(openList,openListIterations);
	    	}
	    	//3.1 pull from queue
	    	openList.entrySet();
	    	Node vertex = queue.poll();
	    	//3.2
	    	if(isGoal(vertex))
        	{	
    			
    			long END =  System.currentTimeMillis();
    			float seconds = (END-START) / 1000F;
			    out.make(findHispathToRoot(vertex),openList.size(),findHisCostToRoot(vertex),seconds,time,true);
			    queue.clear();//End the while loop.
			    break;
        	}
	    	//3.3
	    	closedList.put(vertex.getID(), vertex);
	    	//finding the "_" on the vertex.state -> findEmptySlot()
	        int [] EmptySlot = findEmptySlot(vertex.getState());
	        //finding the allowed sons of this vertex
	        Set<Node> sons = findSons(vertex.getState(), EmptySlot,vertex);
	        if(sons.size()==0) {
	        	out.make("",openList.size(),0,0,time,false);//finish because all blacks.
	        	queue.clear();//End the while loop.
			    break;
	        }
	        //3.4 -3.4.1
	        for(Node son: sons) 
	        {
	        	//this.moves++;//compute the moves of the algorithm.
	        	//3.4.2
	        	if(!openList.containsKey(son.getID()) && !closedList.containsKey(son.getID())) 
	        	{
	        		queue.add(son);
	        	    openList.put(son.getID(), son);
	        	}
	        	//3.4.3
	        	else if(openList.containsKey(son.getID())) 
	        	{
	        		Node same = openList.get(son.getID());
	        		if(findHisCostToRoot(son)<findHisCostToRoot(same))
	        			openList.replace(same.getID(), son);
	        	}
	        }
	    		
	    }
		
		
	}
	
/////*******************************END OF ALGORITHM******************************************************////	
	
	
	

}
