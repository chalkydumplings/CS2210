import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class bb {
	
	private Graph BusLines;
	private int w, h, k, start, dest;
	private char currentLine, previousLine;
	Stack<GraphNode> path = new Stack<GraphNode>();
	
	public bb(String inputFile) throws MapException, GraphException {
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
			
			for(int i=0; i<(2*h)-1; i++) {
				line = input.readLine();
				for(int j=0; j<(2*w)-1; j++) {
					holder = line.charAt(j);
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
	
	Graph getGraph() throws MapException {
		if (BusLines == null) {
			throw new MapException("there was an error retrieving the map");
		}
		else {
			return BusLines;
		}
	}
	
	private Iterator<GraphNode> tripHelper(GraphNode current, GraphNode dest, int busChange) throws GraphException {
	
		Iterator<GraphNode> trip = path.iterator();
		Iterator<GraphEdge> incidents = BusLines.incidentEdges(current);
		
		current.setMark(true);
		if(path.isEmpty() || path.peek() != current) {
			path.push(current);
		}
		previousLine = currentLine;
		
		if(current.getName() == dest.getName()) {
			trip = path.iterator();
			return trip;
		}
		
		while(incidents.hasNext()) {
			GraphNode next = incidents.next().secondEndpoint();
			currentLine = BusLines.getEdge(current, next).getBusLine();
			
			if(previousLine == currentLine && next.getMark() == false) {
				return tripHelper(next, dest, busChange);
			}
			else if((busChange+1)<k && next.getMark() == false) {
				busChange++;
				return tripHelper(next,dest,busChange);
			}
		}
		
		path.pop();
		currentLine = previousLine;
		return tripHelper(path.peek(), dest, busChange);
		
		
	}
    
    public Iterator<GraphNode> trip() throws GraphException{
    	currentLine = BusLines.getEdge(BusLines.getNode(start), BusLines.getNode(start+1)).getBusLine();
        Iterator<GraphNode> route=tripHelper(BusLines.getNode(start), BusLines.getNode(dest),0);
        return route;
    }
}
