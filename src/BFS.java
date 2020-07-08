import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
/**
 * This BFS algorithm  will look for the optimal children states.
 *  
 * @author NviCoder
 *
 */

public class BFS extends Algoritm {
	
	
//******************Constructor***************************************************//
	
	public BFS(int[] black, int[] red,int[] size, int[][] gameBoard) {
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
	    Queue<Node> queue = new ArrayDeque<>();
	    Hashtable<String, Node> openList = new Hashtable<String, Node>();
	    Hashtable<String, Node> closedList = new Hashtable<String, Node>();
	    
	  
	    //Add the start Node to the queue.
	    queue.add(this.start);
	    openList.put(this.start.getID(), this.start);
	    
	    while(queue.size() != 0)
	    {	
	    	//open Print option.
	    	if(open) {
	    		
	    		openListIterations++;
	    		withOpen(openList,openListIterations);
	    	}
	    	openList.entrySet();
	        Node vertex = queue.poll();
	       // System.out.println("The Node: "+vertex.getID()+" "+vertex.getdir());
	        closedList.put(vertex.getID(), vertex);
	        //finding the "_" on the vertex.state -> findEmptySlot()
	        int [] EmptySlot = findEmptySlot(vertex.getState());
	        //finding the allowed sons of this vertex
	        Set<Node> sons = findSons(vertex.getState(), EmptySlot,vertex);
	        if(sons.size()==0) {
	        	out.make("",this.moves+1,0,0,time,false);//finish because all blacks.
	        	queue.clear();//End the while loop.
			    break;
	        }
	        
	        for(Node son: sons) 
	        {	
	        	
	        	if(!openList.containsKey(son.getID()) && !closedList.containsKey(son.getID())) 
	        	{
	        		this.moves++;//compute the moves of the algorithm.
	        		//System.out.println(openList.size());
	        		if(isGoal(son))
		        	{	
	        			//this.moves = this.moves-sons.size()+1;
	        			long END =  System.currentTimeMillis();
	        			float seconds = (END-START) / 1000F;
	    			    out.make(findHispathToRoot(son),openList.size()-sons.size()+1,findHisCostToRoot(son),seconds,time,true);
	    			    queue.clear();//End the while loop.
	    			    break;
		        	}
	        		
	        		queue.add(son);
    			    openList.put(son.getID(), son);
    			   
	        		
	        	}
	        	
	        }
	        
	    }
	}
	
/////*******************************END OF ALGORITHM******************************************************////	
	
	
	

	


}
