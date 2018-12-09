import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class b {
	
	private Graph BusLines = null;
	private int w, h, k, start, dest;
	
	public b(String inputFile) throws MapException, GraphException {
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
						System.out.println(start);
						nd++;
					}
					else if(holder == 'D') {
						
						dest = nd; 
						System.out.println(dest);
						nd ++;
					}
					else if(holder == '.') {
						
						nd++;
					}
					
						else {
							if((i % 2)== 0) {
								if (holder != ' ')
									BusLines.insertEdge(BusLines.getNode(nd-1), BusLines.getNode(nd), holder);
							}
							else {
								if (holder != ' ') {
									BusLines.insertEdge(BusLines.getNode(nd-w), BusLines.getNode(nd), holder);
								}
								else if (holder == ' ' && (j%2) == 0)
									nd++;
							}
						}
					}
					if (i%2 == 1)
						nd = nd-w;
				}
				input.close();
				
			
						
					
		
			System.out.println(nd);
			
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
	
	public static void main(String[] args) throws MapException, GraphException
	{
		BusLines newBus = new BusLines("map0.txt");
		
	String hi= "ILOVEJOHNSNOW";
	//ahh ok so it stops at one 
		//System.out.println(hi.substring(0,1));
		
		b hii=new b("map0.txt");
		
		System.out.println(hii.getGraph());
		
	}

	public Iterator<GraphNode> trip() {
		// TODO Auto-generated method stub
		return null;
	}
}
