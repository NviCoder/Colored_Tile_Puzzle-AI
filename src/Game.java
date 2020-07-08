import java.io.IOException;
import java.util.Arrays;
/**
 * This class responsible on choosing which algorithm to invoke, 
 *  with the data from the input.txt 
 * @author NviCoder
 *
 */
public class Game {
	
	private boolean open,time;
	
	
	public Game() {
		// TODO Auto-generated constructor stub
	}

	public static void StartTheGame(String algo,boolean time,boolean open, int[] size,int[] black,int[] red,int[][] gameBoard ) throws IOException {
		 
		if(algo.equals("BFS")) //Search with BFS the solution
		{
			BFS bfs = new BFS(black, red,size,gameBoard);
			bfs.start(time,open);

		}
		
		if(algo.equals("A*"))// Search with A* the solution
		{

			A_Star a_star = new A_Star(black, red, size, gameBoard);
			a_star.start(time,open);
		}
		
		if(algo.equals("IDA*")) //Search with IDA* the solution
		{

			IDA_Star ida_star = new IDA_Star(black, red, size, gameBoard);
			ida_star.start(time,open);
		}
		
		if(algo.equals("DFID")) //Search with DFID the solution
		{

			DFID dfid = new DFID(black, red, size, gameBoard);
			dfid.start(time,open);
		}
		
		if(algo.equals("DFBnB")) //Search with DFBnB the solution
		{

			DFBnB dfbnb = new DFBnB(black, red, size, gameBoard);
			dfbnb.start(time,open);
		}
	}
}
