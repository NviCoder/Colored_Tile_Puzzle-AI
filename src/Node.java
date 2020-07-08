import java.util.Comparator;
/**
 * This class is The node of each state of the graph. 
 */
	
//******************Constructor***************************************************//

	public class Node{
		
		private int[][] state;
		private int cost=0;
		private int moves = 0;
		private String path,id,dir;
		private Node father;
		public boolean out = false;
		

		public Node(int[][] state,int cost,String dir,int[]size) {
			this.state = new int [size[0]][size[1]];
			copyMatrix(this.state,state);
			this.cost = cost;
			this.dir = dir;
			this.id = nodeIdGenertor(this.state,size);
		}
		
		/**
	 *This function gives to each node an id by his
	 *number order on the game board.
	 *Then we can easily find duplicated nodes.
	 *
	 * @param gameboard - a state of the puzzel. 
	 * @return a string of the id
	 */
	public String nodeIdGenertor(int[][]gameboard,int[]size) {
        StringBuilder id = new StringBuilder();
        for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[1];j++) {
				id.append(Integer.toString(gameboard[i][j]));
			}
        }
        String saltStr = id.toString();
        return saltStr;

    }
	
	private void copyMatrix(int[][] a, int[][] b) {
		for(int i=0; i<a.length; i++)
			  for(int j=0; j<a[i].length; j++)
			    a[i][j]=b[i][j];
		
	}

		public Node getFather() {
			return father;
		}

		public void setFather(Node father) {
			this.father = father;
		}

		public String getID() {
			return this.id;
		}
		public int[][] getState(){
			return this.state;
		}

		public int getCost() {
			return cost;
		}

		public int getMoves() {
			return moves;
		}

		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			 this.path = path;
		}
		public String getdir() {
			return dir;
		}
		public boolean getOut() {
			return out;
		}
		public void setOut() {
			 this.out = true;
		}

	}
