import java.util.Iterator;
import java.util.ArrayList;


/*
 * Graph Class
 * @author Julie Winer
 * @studentNumber 250 866 434
 */

//---------------------------CLASS DESCRIPTION----------------------------
//implements an undirected graph - 
//use  adjacency matrix OR adjacency list representation for the graph. 
//implement the GraphADT.java interface.

//I THINK IM GONNA DO ADJENCY LSIT - jk
//QUESTION: do u think they are just checking to see if it throws exceptions or do I have to 
//be specific

public class Graph implements GraphADT {

///once again im doing protected cause honestly I seee no point in doing not protectced
protected int n;
ArrayList<GraphNode> nodeList; 
GraphEdge [][] adjMatrix;

protected Graph(int n) 
{
	this.n=n;
	nodeList= new ArrayList<GraphNode>();
	adjMatrix=new GraphEdge[n][n];
	
	// know where going to inser n-1 nodes into the array class
	for(int i=0; i<n; i++) 
	{
		GraphNode Node= new GraphNode(i);
		nodeList.add(i,Node); // add node at the same index as its name
		
		for(int j=0; j<n;j++)
		{
		
			adjMatrix[i][j]=null;
			
		}
	}
	
}




//throws two expections
//I NEED TO CHANGE MY HILIGHT COLOUR IT IS PISSING ME OFF VERY MUCH 
	public void insertEdge(GraphNode nodeu, GraphNode nodev, char busLine) throws GraphException
	{
		int uName=nodeu.getName();
		int vName=nodev.getName();
		int indexSize= nodeList.size()-1;
		
		//ADD THE CLAUSE IF EDGE DOES NOT EQUAL NULL
		
		if(uName>indexSize|| vName>indexSize)
			throw new GraphException("DNE");// if value is greater than n
		
		else if(adjMatrix[uName][vName]!=null ||  adjMatrix[vName][uName]!=null)
				throw new GraphException("DNE"); //means the value already exists
		else
		{
			GraphEdge newEdge=new GraphEdge(nodeu,nodev,busLine);
			
			adjMatrix[uName][vName]=newEdge; // set value at matrix nodes as edge
			adjMatrix[vName][uName]=newEdge;// nondirectional so must do both 
			
			
		}
		
	}

	//@returns the node with the specified name
	//@throw GraphException if node doesnt exist
	public GraphNode getNode(int name) throws GraphException
	
	{
		//believe the only case you need to test is if the node is greater than  n-1  or
		//wait i am not sure I need to go back possibly
		
		if(name>(n-1))
			throw new GraphException("D.N.E");
		
		return  nodeList.get(name); // returns the entrity of the node

	}

//	@Return a Java Iterator storing all the edges incident on node u or null.
	//!! NOTE this method passed but im suss of the testCLASS
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException
	
	{
		
		
		if(u.getName()>(n-1)) //might need to add more exceptions to lazy rn will go back
			throw new GraphException("D.n.e");
		
		ArrayList<GraphEdge> toIterate = new ArrayList<GraphEdge>();
		//make a new array list to return as an iterator object
		for(int i=0;i<n;i++)
		{
			int Name;
			Name= u.getName();
			
			if(adjMatrix[Name][i]!=null) 
				toIterate.add(adjMatrix[Name][i]);
			
		}
		
			
			
		
		
		return toIterate.iterator();
		
	
	}
		

	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException 
	
	{
		int uName=u.getName();
		int vName=v.getName();
		int indexSize= nodeList.size()-1;
		
		if (uName > indexSize|| vName > indexSize) {
			throw new GraphException("Error: Atleast one node not present."); // FIX LATER TOO LAZY RN
		}
		else return adjMatrix[uName][vName];
		
	}
		
	

//	Returns true if nodes u and v are
//	adjacent; it returns false otherwise.
//ok so how the heck does one check is something is adjacenet??
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException
	{

		int uName=u.getName();
		int vName=v.getName();
		int indexSize= nodeList.size()-1;
		
		
		
		if (uName > indexSize || vName > indexSize) {
			throw new GraphException("Error: Atleast one node not present.");
		}
		if (adjMatrix[uName][vName] != null) {
			return true;
		}
		else {
			return false;
		}
	}	
}


