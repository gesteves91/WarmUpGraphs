import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class wup {
	//Readline prompt option
	static BufferedReader in = new BufferedReader (
			new InputStreamReader (System.in));
	
	static ArrayList<Integer> ary = new ArrayList<Integer>();
	static ArrayList<String> focuses = new ArrayList<String>();

	static Map<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
	
	static int sizeV, sizeE;
	
	//ReadInput
	public static String readInputFile(String file) throws FileNotFoundException, IOException{
		String entireFile;
		Boolean collect = false;
		
		try(BufferedReader br = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/TP_Grafos/src/input.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    
		    while (line != null) {
		    	sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        if(line != null && line.length() == 1)
		        	collect = true;
		        if(collect)
		        	focuses.add(line);
		    }  
		    entireFile = sb.toString();
		}
		return entireFile;
	}

	//Generating Matrix
	public static void GeneratingMatrix() throws FileNotFoundException, IOException{
		//Store Sizes of vertices and edges
				int sizeE;
				//Call the function to read the input file
				String file = readInputFile("");
				
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
				Graph g = new Graph (sizeV+1, sizeE);
				
				
				for(int i=0; i<sizeE;i++){
					x = ary.remove(0);
					y = ary.remove(0);
					g.insertEdge(x,y);
					g.insertEdge(y, x);
				}

				g.print (); 
	}
	
	public static void GeneratingFocuses() throws FileNotFoundException, IOException{
		//Store Sizes of vertices and edges
		int sizeF = Integer.parseInt(focuses.remove(0));
		
		//sizeF = ary.remove(0);
		
		int j = 1;
		
		//Size and Edges are summed by 1 because a matrix in Java starts at 0
		//0 will be desconsidered as long as it is not important here
		Graph g = new Graph (6, sizeF+1);
		Scanner sc = new Scanner(focuses.get(0));
		
		for (int i = 0; i < focuses.size() - 1; i++){
			sc = new Scanner(focuses.get(i));
			while(sc.hasNextInt())
				g.insertEdge(j, sc.nextInt());
			j++;
		}
		
			
		//g.insertEdge(1, 1);
		//g.insertEdge(1, 2);
		//g.insertEdge(2, 1);
		//g.insertEdge(3, 2);
		//g.insertEdge(4, 2);
		//g.insertEdge(4, 3);
		//g.insertEdge(5, 4);
		
	
		

		g.printFocuses ();  in.readLine();
	}
	
	public static void main (String[] args) throws Exception {
		GeneratingMatrix();
		GeneratingFocuses();
	}
}

/*    Grafo grafoT = grafo.grafoTransposto();
grafoT.imprime ();  in.readLine();
Grafo.Aresta a = lerAresta ();
if (grafo.existeAresta (a.v1 (), a.v2 ())) 
System.out.println ("Aresta ja existe");
else {
grafo.insereAresta (a.v1 (), a.v2 (), a.peso ()); // @{\it Duas chamadas porque}@    
grafo.insereAresta (a.v2 (), a.v1 (), a.peso ()); // @{\it grafo n\~ao direcionado}@    
}
grafo.imprime (); in.readLine();
 */    
/*System.out.print ("Lista adjacentes de: "); 
int v1 = Integer.parseInt (in.readLine());
if (!g.listaAdjEmpty (v1)) {
	Graph.Edge adj = g.firstAdjMatrix(v1);
	while (adj != null) {
		System.out.println ("  " + adj.v2 ());
		adj = g.proxAdj (v1);       
	}
	System.out.println (); in.readLine();
}
System.out.println ("Retira aresta: ");
Graph.Edge a = lerAresta ();
if (g.existEdge(a.v1 (), a.v2 ())) {
	g.retiraAresta (a.v1 (), a.v2 ()); // @{\it Duas chamadas porque}@
	g.retiraAresta (a.v2 (), a.v1 ()); // @{\it grafo n\~ao direcionado}@
}
else System.out.println ("Aresta nao existe");
g.print (); in.readLine();
System.out.print ("Existe aresta: "); a = lerAresta ();
if (g.existEdge (a.v1 (), a.v2 ())) 
	System.out.println ("  Sim");
else System.out.println ("  Nao");*/
