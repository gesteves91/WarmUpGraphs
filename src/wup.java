import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class wup {
	static ArrayList<Integer> ary = new ArrayList<Integer>();
	static ArrayList<String> focuses = new ArrayList<String>();
	
	static FileWriter writer;
	static BufferedWriter buffer;
	
	static int sizeV, sizeE;
	
	//ReadInput
	public static String readInputFile(String file) throws FileNotFoundException, IOException{
		String entireFile;
		Boolean collect = false;
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    Scanner sca = new Scanner(line);
		    int edges = 0, index = 0;
		    
		    while(sca.hasNextInt())
		    	edges = sca.nextInt();
		    
		    while (line != null) {
		    	sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        if(index >= edges)
		        	collect = true;
		        if(collect)
		        	focuses.add(line);
		        index++;
		    }  
		    entireFile = sb.toString();
		}
		return entireFile;
	}

	//Generating Matrix
	public static void GeneratingMatrix(String inputFile, String outputFile) throws FileNotFoundException, IOException{
		//Store Sizes of vertices and edges
				int sizeE;
				//Call the function to read the input file
				String file = readInputFile(inputFile);
				
				writer = new FileWriter(outputFile);
				buffer = new BufferedWriter(writer);
				
				int j = 0;
				Scanner sc = new Scanner(file);
				
				while(sc.hasNextInt()){
				    ary.add(sc.nextInt());
				}
				
				//Set the vertices and edges to the matrix
				sizeV = ary.remove(0);
				sizeE = ary.remove(0);
				
				int x, y;
				
				//Size and Edges are summed by 1 because a matrix in Java starts at 0
				//0 will be desconsidered as long as it is not important here
				Graph g = new Graph (sizeV+1, sizeV+1);
				
				
				for(int i=0; i<sizeE;i++){
					x = ary.remove(0);
					y = ary.remove(0);
					g.insertEdge(x,y);
					g.insertEdge(y, x);
				}

				g.print(buffer); 
	}
	
	public static void GeneratingFocuses(String outputFile) throws FileNotFoundException, IOException{
		//Store Sizes of vertices and edges
		int sizeF = Integer.parseInt(focuses.remove(0));
		
		int j = 1;
		
		//Size and Edges are summed by 1 because a matrix in Java starts at 0
		//0 will be desconsidered as long as it is not important here
		Graph g = new Graph (sizeV+1, sizeF+1);
		Scanner sc = new Scanner(focuses.get(0));
		
		for (int i = 0; i < focuses.size() - 1; i++){
			sc = new Scanner(focuses.get(i));
			while(sc.hasNextInt())
				g.insertEdge(j, sc.nextInt());
			j++;
		}
		
		g.printFocuses (buffer); 
	}
	
	public static void main (String[] args) throws Exception {
		//args[0] = "in2";
		//args[1] = "out2";
		GeneratingMatrix(args[0], args[1]);
		GeneratingFocuses(args[1]);
	    writer.close();
	}
}