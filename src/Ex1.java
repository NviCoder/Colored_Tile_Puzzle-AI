import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
/**
 * The class responsible of the reading the input.txt file,
 * This class take all the data from the input and create a Game object and init him.
 * @author eladn
 *
 */
public class Ex1 {
  
	public static Game game;
	
	public static void main(String[] args) throws IOException
  {
	  boolean time=true,open = true;
	  String algo=""; 
	  int row = 1;
	  int matrixRow = 1;
	  int[] black = null;
	  int[]red = null ;
	  int[] size = new int[2];
	  int[][] gameBoard = null;
	  String[] parts;
	  
	 
    try {
      //Using File to read the Input txt
      File myObj = new File("input.txt");
      Scanner myReader = new Scanner(myObj);
      String[] arrOfStr;
      while (myReader.hasNextLine()) 
      {   
    	 String currentLine = myReader.nextLine();
    	 //Using switch cases to find the data we need from the input.txt
    	 switch(row) 
    	 {
    	 	case 1:
    	 		//Algorithm to use.
    	 		algo = currentLine;
    	 		break;
    	 		
    	 	case 2:
    	 		//Time?
    	 		arrOfStr = currentLine.split(" ", 2);
    	 		if(arrOfStr[0].equals("no"))
    	 			time = false;
    	 		break;
	 	  
    	 	case 3:
    	 		//Open or no open
    	 		arrOfStr = currentLine.split(" ", 2);
    	 		if(arrOfStr[0].equals("no"))
    	 			open = false;
    	 		break;
	 	   
    	 	case 4:
    	 		//Size
    	 		arrOfStr = currentLine.split("x", 2);
    	 		size[0] = Integer.parseInt(arrOfStr[0]); 
    	 		size[1] = Integer.parseInt(arrOfStr[1]); 
    	 		break;
    	 	
    	 	case 5:
    	 		//Blacks
    	 		parts = currentLine.split(":");// Part1: [black:] Part2: [" 12,1,2,3,5"]
    	 		if(parts.length > 1) {// When we don't have any number. like "black: "
        	 		arrOfStr = parts[1].split(",");
	    	 		black = new int[arrOfStr.length];
	    	 		for(int i=0;i<arrOfStr.length;i++) 
	    	 		{
	    	 			if(i==0)
	    	 				black[i] =  Integer.parseInt(arrOfStr[i].substring(1));
	    	 			else 
	    	 				black[i] =  Integer.parseInt(arrOfStr[i]);
	    	 		}
    	 		}
    	 		break;	
    	 	
    	 	case 6:
    	 		//Reds
    	 		parts = currentLine.split(":"); // Part1: [red:] Part2: [" 12,1,2,3,5"]
    	 		if(parts.length >1) { // When we don't have any number. like "red: "
	    	 		arrOfStr = parts[1].split(",");
	    	 		red = new int[arrOfStr.length];
	    	 		for(int i=0;i<arrOfStr.length;i++) 
	    	 		{
	    	 			if(i==0)
	    	 				red[i] =  Integer.parseInt(arrOfStr[i].substring(1));
	    	 			else 
	    	 				red[i] =  Integer.parseInt(arrOfStr[i]);;
	    	 		}
    	 		}
    	 		break; 
    	 }
    	 //Now is the matrix data.
    	 if(row==7) //The first row of the matrix.
    	 {
    		 gameBoard = new int[size[0]][size[1]];
    		 arrOfStr = currentLine.split(",", size[1]);
    		 for(int i=0;i<arrOfStr.length;i++) {
    			 if(!arrOfStr[i].equals("_"))
    				 gameBoard[0][i] = Integer.parseInt( arrOfStr[i]); 
    		 }
    		 
    	 }
    	 if(row>7) //The other rows of the matrix. 
    	 {
    		 arrOfStr = currentLine.split(",", size[1]);
    		 for(int i=0;i<arrOfStr.length;i++) {
    			 if(!arrOfStr[i].equals("_"))
    				 gameBoard[matrixRow][i] = Integer.parseInt(arrOfStr[i]); 
    		 }
    		 matrixRow++;
    	 }
    	row++;
    	
      }
      myReader.close();
    } 
    
    catch (FileNotFoundException e)
    {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
    
    //Start The Game with the Game object!!
    game.StartTheGame(algo, time, open, size, black, red, gameBoard);
    
    
  }

  }
  
  
  
  
  
//}