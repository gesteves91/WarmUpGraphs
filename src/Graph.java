public class Graph {
  public static class Edge {
    private int v1, v2;
    public Edge (int v1, int v2) {
      this.v1 = v1; this.v2 = v2;
    }
    public int v1 () { return this.v1; }
    public int v2 () { return this.v2; }
  }
  private int mat[][]; 
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
    this.mat = new int[numVertices][numVertices];
    this.pos = new int[numVertices]; 
    this.numVertices = numVertices; 
    this.numEdges = numEdges;
    for (int i = 0; i < this.numVertices; i++) {
      for (int j = 0; j < this.numVertices; j++) this.mat[i][j] = 0;
      this.pos[i] = -1; 
    }
  }
  public void insertEdge (int v1, int v2) {
    this.mat[v1][v2] = 1; 
  }
  public boolean existEdge (int v1, int v2) {
    return (this.mat[v1][v2] > 0);
  }
  public boolean listaAdjEmpty (int v) {
    for (int i =0; i < this.numVertices; i++)
      if (this.mat[v][i] > 0) return false;
    return true;
  }

  public Edge retiraAresta (int v1, int v2) {
    if (this.mat[v1][v2] == 0) return null;
    else {
    	Edge aresta = new Edge (v1, v2);
      this.mat[v1][v2] = 0; return aresta;
    }
  }
  public void print () {
    //System.out.print ("   ");
    //for (int i = 0; i < this.numVertices; i++) 
      //System.out.print (i + "   "); 
    //System.out.println ();
    for (int i = 1; i < this.numVertices; i++) { 
      //System.out.print (i + "  ");
      for (int j = 1; j < this.numVertices; j++)
        System.out.print (this.mat[i][j] + "   ");
      System.out.println ();
    }
  }
  
  public void printFocuses () {
	    //System.out.print ("   ");
	    //for (int i = 0; i < this.numVertices; i++) 
	      //System.out.print (i + "   "); 
	    //System.out.println ();
	    for (int i = 1; i < this.numVertices; i++) { 
	      //System.out.print (i + "  ");
	      for (int j = 1; j < this.numEdges; j++)
	        System.out.print (this.mat[i][j] + "   ");
	      System.out.println ();
	    }
	  }
  public int numVertices () { return this.numVertices; }
}

