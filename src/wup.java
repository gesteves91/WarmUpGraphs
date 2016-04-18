import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class wup {
	static ArrayList<Integer> ary = new ArrayList<Integer>();
	static ArrayList<String> focuses = new ArrayList<String>();
	static ArrayList<String> solutions = new ArrayList<String>();
	static ArrayList<Integer> vertices = new ArrayList<Integer>();
	static FileWriter writer;
	static BufferedWriter buffer;

	static int sizeV, sizeE, size;
	static String[][] mat;
	
	//This variable indicates whether or not I have found a solution
	static Boolean done = false;
	
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
	public static Graph GeneratingMatrix(String inputFile, String outputFile) throws FileNotFoundException, IOException{
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
					vertices.add(x);
					vertices.add(y);
					g.insertEdge(x,y);
					g.insertEdge(y,x);
				}

				g.print(buffer); 
				return g;
	}
	
	public static Graph GeneratingFocuses(String outputFile) throws FileNotFoundException, IOException{
		//Store Sizes of vertices and edges
		int sizeF = Integer.parseInt(focuses.remove(0));
		
		int j = 1;
		
		int num;
		
		//SumUp one because I want to store the element in the first position
		//mat = new int[sizeV+1][sizeF+1];
		
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
		return g;
	}
	
	public static Boolean compareSolution(String[] focus){
		String solution = "1111";
		String guessSol = "";
		Boolean afir = false;
		
		
		for(int j = 1; j < focus.length; j++)
			guessSol += focus[j];
		
		if(solution.equals(guessSol))
			afir = true;
			
		return afir;
	}
	
	public static String[] sumUpVertices(String[] v1, String[] v2){
		String[] res = new String[v1.length];
		
		res[0] = v1[0] + "," + v2[0];
		
		for(int i = 1; i < mat[0].length; i++)
			if(v1[i].equals("1") || v2[i].equals("1"))
				res[i] = "1";
			else
				res[i] = "0";
				
		return res;
	}

	
	public static void addToSolution(String[] s){
		for(int i = 0; i < s.length; i++)
			mat[size][i] = s[i];
		size+=1;
	}
	
	public static String[] returnLine(String v1){
		String[] ret = new String[mat[0].length];
		Boolean stop = false;
		
		for (int i = 0; i < mat.length && !stop; i++)
			//ret[i] = mat[Integer.parseInt(v1)-1][i];
			if(mat[i][0].equals(v1)){
				for (int j = 0; j < mat[0].length; j++)
					ret[j] = mat[i][j];
				stop = true;
			}
			
		return ret;
	}
	
	public static void populateMat(ArrayList<Integer> data, int n){
		String[] a = new String[n];
		String call = "";
		String[] x, y;
		String[] sol;

		while(data.size() != 0){
			for(int i = 0; i < n; i++)
				a[i] = Integer.toString(data.remove(0));
				for(int j = 0; j < n - 1; j++)
					call += a[j] + ",";
					if(call.substring(call.length() - 1).equals(","))
						call = call.substring(0, call.length()-1);
				x = returnLine(call);
				y = returnLine(a[n-1]);
				sol = sumUpVertices(x, y);
				if(compareSolution(sol))
					solutions.add(sol[0]);
				addToSolution(sol);
				call = "";
		}
		
		/*
		while(data.size() != 0){
			if(n == 2){
				x = returnLine(Integer.toString(data.remove(0)));
				y = returnLine(Integer.toString(data.remove(0)));
			}
			else {
				x = returnLine(Integer.toString(data.remove(0)) + "," + Integer.toString(data.remove(0)));
				y = returnLine(Integer.toString(data.remove(0)));
			}*/
			
		}
	
	
	public static String[][] copyMatrix(Graph p){
		  //String[][] ret = new String[p.numVertices()-1][p.numEdges()];
		String[][] ret = new String[100][p.numEdges()];
		  //ret[0] = Integer.toString(v1); 
		  for (int i = 1, m = 0; i < p.numVertices(); i++, m++)
			  for (int j = 0, n = 0; n < p.numEdges(); j++, n++){
				  if (n == 0) ret[m][n] = Integer.toString(i); 
				  else ret[m][n] = Integer.toString(p.mat[i][j]);
			  }
		  size = p.numVertices() - 1;
		  return ret;
	  }
	
	public static void testConnectivity() throws Exception{
		String so = solutions.remove(0);
		so = so.replaceAll(",", " ");
		ArrayList<Integer> arrl = new ArrayList<Integer>();
		ArrayList<Integer> solut = new ArrayList<Integer>();
		//Extract all first positions
	
		Scanner scc = new Scanner(so);
		
		//Scanner scc = new Scanner(vertices);
		//int a, b;
		
		int index = 0;
		
		while(scc.hasNextInt()){
			arrl.add(scc.nextInt());
			index++;
		}
		
		
		
		//a = scc.nextInt();
		//b = scc.nextInt();
		
		Graph subg = new Graph(sizeV+1);
		
		for(int i = 0; i < vertices.size(); i+=2)
			for(int j = 0; j < arrl.size(); j++)
				if (arrl.get(j) == vertices.get(i)){
					solut.add(vertices.get(i));
					solut.add(vertices.get(i+1));
				}
		
		int x, y;
		
		while(solut.size() != 0){
			x = solut.remove(0);
			y = solut.remove(0);
			subg.insertEdge(x,y);
			subg.insertEdge(y,x);
		}
			
			
		//rg.insertEdge(1, 2); 
		//rg.insertEdge(2, 1); 
		//rg.insertEdge(1, 3);
		//rg.insertEdge(3, 1);
		//;
		
		//rg.insertEdge(4, 5); 
		//rg.insertEdge(5, 4); 
		
		BFS bfs = new BFS (subg);
	    bfs.buscaEmLargura();
	    //for (int v = 0; v < grafo.numVertices(); v++) {
	      //System.out.println ("d["+v+"]:" + bfs.d (v) + " -- antecessor["+v+"]:" + bfs.antecessor (v));      
	    //}
	    
	    int first = arrl.remove(0);
	    while(arrl.size()!=0){
	    	bfs.imprimeCaminho(first, arrl.remove(0));
	    }
	   
	    done = bfs.Exists;
	    
	    //Print the first smallest solution
	    if(bfs.Exists)
	    	System.out.println(so);
	}
	
	
	public static void main (String[] args) throws Exception {
		args[0] = "/Users/gesteves/Documents/workspace/WarmUP/src/in0";
		args[1] = "/Users/gesteves/Documents/workspace/WarmUP/src/out0";
		Graph g1 = GeneratingMatrix(args[0], args[1]);
		Graph g = GeneratingFocuses(args[1]);
		//for (int i : g.returnVertex(4))
			//System.out.println(i);
		String[] a1 = g.returnVertex(1);
		String[] a2 = g.returnVertex(4);
		//System.out.println(compareSolution(a1));
	    
	    mat = copyMatrix(g);
	    
	    Permutation p = new Permutation();
		int arr[] = {1, 2, 3, 4, 5};
        int r = 1;
        int n = arr.length;
        //p.printCombination(arr, n, r);
        
        do{
        	r++;
        	p.printCombination(arr, n, r); //Generating possible combinations
        	populateMat(p.aux, r); 
        	while (solutions.size() != 0)
        	    testConnectivity();
        } while (!done);
        
        //while (!done){
        	//populateMat(p.aux, r); //populate solution matriz is res for populating solutions array
        	//while (solutions.size() != 0)
        	  //  testConnectivity();
        	//r++;
        	//p.printCombination(arr, n, r);
        //}
        
       // r = 3;
        //p.printCombination(arr, n, r);
        
        //populateMat(p.aux, 3);
        
        //r = 4;
        //p.printCombination(arr, n, r);
        
        //populateMat(p.aux, 4);
        
        //r = 5;
        //p.printCombination(arr, n, r);
        
        //populateMat(p.aux, 5);
        
        //for (String s : solutions)
        	//System.out.println(s);
        
        //while (solutions.size() != 0)
    	    //testConnectivity();
    	    
    	    
    	    //writer.close();
	    
	    //for(String[] b : mat){
    	//for(String elem : b)
    		//System.out.print(elem + " ");
    	//System.out.println();
	    //}
	    
	    //for(int i = 0, k = 0; i < solutions.size();i++){
	    	//while(k<5){
	    	//System.out.print(solutions.remove(0));
	    	//k++;
	    	//}
	    	//k = 0;
	    	//System.out.println();
	    //}
	    
	    //for (int i : vertices)
	    //System.out.println(i);
	    
	    //String[] st = returnLine(5);
	    
	    //for(String s34: st)
	    	//System.out.print(s34 + " ");
    
	    
	    //int[][] ass = g1.returnList();
	    
	    //for(int[] b : mat){
	    	//for(int elem : b){
	    		//System.out.print(elem + " ");
	    	//}
	    	//System.out.println();
	    //}
	    //System.out.println(compareSolution(new String[]{"1,2", "1", "1","1","1"}));
        
	    
		//String[] res = sumUpVertices(a1, a2);
		//for (String i : res)
			//-System.out.print(i + " ");
	
		//BFS bfs = new BFS (cu);
	    //bfs.buscaEmLargura();
	    //for (int v = 0; v < grafo.numVertices(); v++) {
	      //System.out.println ("d["+v+"]:" + bfs.d (v) + " -- antecessor["+v+"]:" + bfs.antecessor (v));      
	    //}
	    //bfs.imprimeCaminho (1, 4);
	}
}