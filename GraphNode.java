/*
 * GraphNode Class
 * @author Julie Winer
 * @studentNumber 250 866 434
 */
import java.util.*;
import java.io.*;
//for the most part this class seems pretty easy 
public class GraphNode
{	
	protected int Name;
	protected boolean marked; // varibale to check if node has been visited
							// honestly just made it protected to be fancy for no reason
	
	
	//constructor creates an unmarked node
	//The name of a node is an integer value between 0 and n−1,
	// n is the number of nodes in the graph.
	//A node can be marked with a value that is either true or false using method setMark. This
	//is useful when traversing the graph to know which vertices have already been visited.
	GraphNode(int name)
	
	{
		this.Name=name;
		this.marked=false;
		
		
	} 
	
	
	// Marks the node with the specified value.
	void setMark(boolean mark)
	{
		
		this.marked=mark;
	}
	
	
	//Returns the value with which the node has been marked.
	boolean getMark() 
	{
		return this.marked;
	} 
	 
	//Returns the name of the node.
	int getName()
	{
		return this.Name;
	}
	

	

	//let test this shit
	public static void main(String[] args) 
	
	{
		
		

		
	}

}
