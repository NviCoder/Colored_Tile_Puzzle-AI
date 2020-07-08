import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

/**
 * This algorithm should be recurcevly.
 * @author Elad
 *
 */
public class DFID extends Algoritm {
		Node goalthatFounded;
	
	//******************Constructor***************************************************//
		
		public DFID(int[] black, int[] red,int[] size, int[][] gameBoard) {
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
			int result;
			output out = new output();
			
			for(int depth=0;depth<Integer.MAX_VALUE;depth++) {
				Hashtable<String, Node> hashTable = new Hashtable<String, Node>();
				result = Limited_DFS(this.start,this.goal,depth,hashTable);
				if(open) {
		    		openListIterations++;
		    		withOpen(hashTable,openListIterations);
		    	}
				if (result != 3) { //which is cutoff
					long END =  System.currentTimeMillis();//Time
        			float seconds = (END-START) / 1000F;
        			System.out.println(goalthatFounded.getID());
    			    out.make(findHispathToRoot(goalthatFounded),hashTable.size(),findHisCostToRoot(goalthatFounded),seconds,time,true);
    			    depth = Integer.MAX_VALUE;//End the while loop.
    			    break;
				}
				
			}
		}
/////*******************************END OF ALGORITHM******************************************************////	

		/**
		 * 
		 * @param start node
		 * @param goal matrix of the goal
		 * @param depth to go in this iteration
		 * @param hashTable to save the nodes
		 * @return 1 - find the goal, 2- fail/no path, 3 - cutoff.
		 */
		public int Limited_DFS(Node vertex,int[][] goal, int limit,Hashtable<String, Node> hashTable) {
			boolean isCutOff;
			int result;
			if(isGoal(vertex))
        	{	
				goalthatFounded = vertex;
				return 1;
        	}
			else if (limit == 0)
				return 3;
			else 
			{
				
				hashTable.put(vertex.getID(), vertex);
				isCutOff = false;
				int [] EmptySlot = findEmptySlot(vertex.getState());
		        //finding the allowed sons of this vertex
		        Set<Node> sons = findSons(vertex.getState(), EmptySlot,vertex);
		        if(sons.size()==0) {
		        	return 2;
		        }
		        for(Node son: sons) 
		        {
		        	if(hashTable.containsKey(son.getID()))
		        	{
		        		//continue to the next son.
		        	}
		        	else {
		        		result = Limited_DFS(son,this.goal,limit-1,hashTable);
		        		if(result == 3)
		        			isCutOff=true;
		        		else if(result != 2) 
		        		{
		        			//goalthatFounded = son;
		        			return 1;
		        		}
		        		
		        	}
		        }
		        hashTable.remove(vertex.getID());
        		if(isCutOff)
        			return 3;
        		else return 2;
			}
			
		}
}
