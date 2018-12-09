
/*
 * GraphEdge Class
 * @author Julie Winer
 * @studentNumber 250 866 434
 */



public class GraphEdge
{
	//once again i did protected to be fancy
	protected GraphNode firstEndpoint; // is java camel case i forgot lol
	protected GraphNode secondEndpoint;
	protected char BUSline; 

	//	constructor
//	first two param  = endpoints of the edge. last parameter is the bus line to
//	 bus line has a name that consists of 2
//	a single character: Either a digit or a letter, except ’S’ and ’D’ . F  case matters,
	GraphEdge(GraphNode u, GraphNode v, char busLine)
	{ 
		this.firstEndpoint=u;
		this.secondEndpoint=v;
		this.BUSline=busLine;
		
		
	}  

//Returns the first endpoint of the edge.
	GraphNode firstEndpoint() 
	{
		return this.firstEndpoint;
		
	}
	
// Returns the second endpoint of the edge.
	 GraphNode secondEndpoint() 
	 {
		return this.secondEndpoint;
		 
	 }
	 
//	 Returns the name of the busLine to which the edge belongs.
	char getBusLine()
	{
		return this.BUSline;
		
	}
	
	

	public static void main(String[] args)
	{


	}

}
