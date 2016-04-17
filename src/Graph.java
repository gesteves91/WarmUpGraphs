import java.io.BufferedWriter;
import java.io.IOException;

public class Graph {
  public static class Edge {
    private int v1, v2;
    public Edge (int v1, int v2) {
      this.v1 = v1; this.v2 = v2;
    }
    public int v1 () { return this.v1; }
    public int v2 () { return this.v2; }
  }
  public int mat[][]; 
  private int numVertices;
  private int numEdges;
  private int pos[];

  public Graph (int numVertices) {
    this.mat = new int[numVertices][numVertices];
    this.pos = new int[numVertices]; 
    this.numVertices = numVertices; 
    for (int i = 0; i < this.numVertices; i++) {
      for (int j = 0; j < this.numVertices; j++) this.mat[i][j] = 0;
      this.pos[i] = -1; 
    }
  }
  public Graph (int numVertices, int numEdges) {
    this.mat = new int[numVertices][numEdges];
    this.pos = new int[numVertices]; 
    this.numVertices = numVertices; 
    this.numEdges = numEdges;
    for (int i = 0; i < this.numVertices; i++) {
      for (int j = 0; j < this.numEdges; j++) this.mat[i][j] = 0;
      this.pos[i] = -1; 
    }
  }
  public void insertEdge (int v1, int v2) {
    this.mat[v1][v2] = 1; 
  }
  public boolean existEdge (int v1, int v2) {
    return (this.mat[v1][v2] > 0);
  }
  public boolean listaAdjVazia (int v) {
	    for (int i =0; i < this.numVertices; i++)
	      if (this.mat[v][i] > 0) return false;
	    return true;
	  }
  public Edge primeiroListaAdj (int v) {
	    this.pos[v] = -1; return this.proxAdj (v);
	  }
  public Edge proxAdj (int v) {
	    this.pos[v] ++;
	    while ((this.pos[v] < this.numVertices) && 
	           (this.mat[v][this.pos[v]] == 0)) this.pos[v]++;
	    if (this.pos[v] == this.numVertices) return null;
	    else return new Edge (v, this.pos[v]);
	  }
  public String[] returnVertex(int v1){
	  String[] ret = new String[mat[v1].length];
	  ret[0] = Integer.toString(v1);
	  for (int j = 1; j < mat[v1].length; j++)
		  ret[j] = Integer.toString(mat[v1][j]);
	  return ret;
  }
  
  //New fuction at works
  //public int[][] returnList(){
	//  int[][] list = new int[numVertices+1][numVertices+1];
	 // for(int i = 1, k = 0; i < numVertices; i++, k++)
		//  for(int j = 1, m = 0; j < numVertices; j++)
			//  if(this.mat[i][j] == 1){
				//  list[k][m] = j;
				 // m++;
			 // }
				  
	//return list;
	//return list;
  //}

  public void print (BufferedWriter bw) throws IOException {
    for (int i = 1; i < this.numVertices; i++) { 
      for (int j = 1; j < this.numVertices; j++){
    	bw.write(this.mat[i][j] + "   ");
    	bw.flush();
        System.out.print (this.mat[i][j] + "   ");
      }
      bw.newLine();
      System.out.println ();
    }
  }
  
  public void printFocuses (BufferedWriter bw) throws IOException {
	    for (int i = 1; i < this.numVertices; i++) { 
	      for (int j = 1; j < this.numEdges; j++){
	        System.out.print(this.mat[i][j] + "   ");
	        bw.write(this.mat[i][j] + "   ");
	        bw.flush();
	      }
	      bw.newLine();
	      System.out.println ();
	    }
	  }
  public int numVertices () { return this.numVertices; }
  public int numEdges () { return this.numEdges; }
}

