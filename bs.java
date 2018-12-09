import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class bs {
	
	private Graph BusLines = null;
	private int w, h, k, start, dest;
	private Stack edgeStack = new Stack();
	
	public bs(String inputFile) throws MapException, GraphException {
		int nd = 0;
		String line;
		char holder;
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			StringTokenizer st = new StringTokenizer(input.readLine());
			
			Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			BusLines = new Graph(w*h);
			//OK THESE r GOOD
			System.out.println(w);
			
			
			for(int i=0; i<(2*h)-1; i++) {
				line = input.readLine();
				
				for(int j=0; j<(2*w)-1; j++) {
				//ok holder looks good
					
					holder = line.charAt(j);
					
					// this means its printing evrything sucessfully 
					System.out.println(holder);
					
					if(holder == 'S') {
						start = nd;
						nd++;
					}
					else if(holder == 'D') {
						dest = nd; 
						nd ++;
					}
					else if(holder == '.') {
						nd++;
					}
					else {
						if((i % 2)== 0) {
							BusLines.insertEdge(BusLines.getNode(nd-1), BusLines.getNode(nd), holder);
							
							System.out.println(BusLines.getEdge(BusLines.getNode(nd-1), BusLines.getNode(nd)));
						}
						else if((j%2) == 0){
							BusLines.insertEdge(BusLines.getNode(nd-w+((j+1)/2)), BusLines.getNode(nd+((j+1)/2)), holder);
						}
						
					}
				}
			}
			input.close();
			
		}
		catch (IOException e){
			throw new MapException("The input file does not exist");
		}
		
		
	}
	
	Graph getGraph1() throws MapException {
		if (BusLines == null) {
			throw new MapException("there was an error retrieving the map");
		}
		else {
			return BusLines;
		}
	}
	
	private Iterator<GraphNode> tripHelper(GraphNode currentNode, GraphNode endNode, int busChanges) throws GraphException{
        Stack<GraphNode> path=new Stack<GraphNode>();
        Iterator<GraphNode> iter=path.iterator();
        
        currentNode.setMark(true);
        path.push(currentNode);
        
        Iterator<GraphEdge> incidents=BusLines.incidentEdges(currentNode);
        
        if(currentNode.getName()==endNode.getName()) {
            iter=path.iterator();
            return iter;
        }
        
        while(incidents.hasNext()) {
            GraphEdge edge=incidents.next();
            GraphNode nextNode=edge.secondEndpoint();
            
            if(busChanges<=k&&nextNode.getMark()==true) {
                busChanges++;
                if(tripHelper(nextNode, endNode, busChanges)!=null) {
                    return tripHelper(nextNode,endNode, busChanges);
                }
                else {
                    busChanges--;
                }				
            }			
        }
        
        return null;
    }
    
	/**
	 * A getter method that returns the graph of the map.
	 * 
	 * @return the graph of the the map
	 * @throws MapException
	 */
	public Graph getGraph() throws MapException {

		// checks if the graph exists
		if (BusLines == null) {
			throw new MapException("The graph is not defined.");
		}

		return BusLines;
	}

	/**
	 * findPath method returns the path from the starting point to the end point
	 * in terms of nodes.
	 * 
	 * @return the path from starting to end point.
	 */
	public Iterator trip() {
		Stack path = new Stack();
		try {
			// initializes path to the stack of nodes from starting point to end
			// point
			path = path(BusLines, BusLines.getNode(start), BusLines.getNode(dest));

			// checks if there is a path, otherwise return null
			if (path == null) {
				return null;
			} else {
				return path.iterator();
			}
		} catch (GraphException e) {
			System.out.println("Error: the path does not exists.");
		}
		return null;

	}

	/**
	 * A getter method that returns the path between a starting point and ending
	 * point.
	 * 
	 * @param graph
	 *            the graph of the map
	 * @param start
	 *            starting point
	 * @param end
	 *            destination point
	 * @return the path from the starting point to the end point
	 * @throws GraphException
	 */
	private Stack path(Graph graph, GraphNode start, GraphNode end) throws GraphException {
		Graph g = graph;
		GraphNode b = start;
		GraphNode e = end;
		GraphNode temp;
		Stack p;

		// mark the beginning point and push it to the stack.
		b.setMark(true);
		edgeStack.push(b);

		// check if the starting and end point are equal.
		if (b == e) {
			return edgeStack;
		}
		// iterates until it reaches the end point
		else {
			// initializes edges to the edges incident on node b
			Iterator<GraphEdge> edges = graph.incidentEdges(b);

			// iterates until it reaches the end of nodes
			while (edges.hasNext()) {
				temp = edges.next().secondEndpoint();

				// checks if the second point of the edge has been discovered
				if (temp.getMark() == false ) {
					p = path(g, temp, e);

					// if the path ends return null
					if (p != null) {
						return p;
					}
				}
			}

			edgeStack.pop();
		}
		return null;
	}


	
	public static void main (String[] args) throws MapException, GraphException {
	
		//display = new DisplayMap(args[0]);
	
	bs hi=new bs("map0.txt");
	}
}
		
//		try {
//				BusLines streetMap = new BusLines(args[0]);
//				BufferedReader in = new BufferedReader(
//							new InputStreamReader(System.in));
//				System.out.println("Press a key to continue");
//				String line = in.readLine();
//				if (line.length() != 0) display.drawRoads();
//		 }
//		 catch (GraphException e) {
//				System.out.println("Error reading input file");
//			    }
//		 catch (MapException e) {
//				System.out.println("Error reading input file");
//			    }
//		 catch (IOException in) {
//				System.out.println("Error reading from keyboard");
//			    }
//		 
//	}
//	*/
//}
