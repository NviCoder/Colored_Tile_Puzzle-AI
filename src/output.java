import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * This is an output class that will make an output.txt file
 * Description of the file:
 * First Row-
 * Sequence of operations done by the algorithm.
 * 
 * Second Row-
 * On the second line, write "Num: " and then The number of vertices produced.
 * 
 * Third Row-
 * In the third line, write "Cost" and then the cost of the solution that founded.
 * 
 * Fourth Row - (if needed)
 * In the fourth row, time should be written Which took the algorithm to find the solution (in seconds).
 * 
 * **Example for output.txt**
 * 7L-6U
 * Num: 18 
 * Cost: 100
 * 25.02 sec
 *  
 * @author NviCoder.
 *
 */

public class output {
	
//	public static void main(String[] args) throws IOException {
//		output out = new output();
//	    out.make("7L-6U",25,150,22.23);
//	}

	public output() {
		
	}

	public void make(String op, int num, int cost, float seconds,boolean time,boolean path) throws IOException {
		
		File file = new File("output.txt");
		  
		//Create the file
		if (file.createNewFile())
		{
		    //System.out.println("File 'output.txt' is created!");
		} else {
		    //System.out.println("File already exists.");
		}
		PrintWriter  writer = new PrintWriter(file);
		 if (path) {
			 op = op.substring(1, op.length());//cut the last "-" in the path!!
				writer.println(op);
				writer.println("Num: "+num);
				writer.println("Cost: "+cost);
				if(time)
					writer.println(seconds+" seconds");
		 }
		 else {
			 	writer.println("no path");
			 	writer.println("Num: "+num);
		 }
		 writer.close();
		//Write Content
		
		
		
	}
		
	}
	

