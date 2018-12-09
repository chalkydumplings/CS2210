import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileReader;


// ok hi 
public class BusLine2 {
	
	private Graph BusLines = null;
	private int w, h, k;
	static GraphNode start;
	GraphNode dest;
	
	public BusLine2(String inputFile) throws MapException, GraphException {
		int nd = 0;
		String line;
		char holder;
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			//use string tokenizer
			line = input.readLine();
//			w = Integer.valueOf(input.readLine());
//			h = Integer.valueOf(input.readLine());
//			k = Integer.valueOf(input.readLine());
			
			
				
			
			Scanner scanFirst = new Scanner(line);
			String[] firstLine = scanFirst.nextLine().split(" ");
			
			
			w=Integer.parseInt(firstLine[1]);
			h=Integer.parseInt(firstLine[2]);
			k=Integer.parseInt(firstLine[3]);
			BusLines = new Graph(w*h);
			
			//System.out.println(w);
			
			for(int i=0; i<(2*h)-1; i++) {
				line = input.readLine();
				for(int j=0; j<(2*w)-1; j++) {
					holder = line.charAt(j);
					System.out.println(holder);
					if(holder == 'S') {
						
						start = BusLines.getNode(nd);
						//System.out.println(start.getName());
						nd++;
					}
					else if(holder == 'D') {
						dest = BusLines.getNode(nd); 
						nd++;
					}
					else if(holder == '.') {
						nd++;
					}
					else {
						while(BusLines!=null) {
						if((i % 2)== 0) {
							if (holder != ' ')
								BusLines.insertEdge(BusLines.getNode(nd-1), BusLines.getNode(nd), holder);
						}
						else {
							if (holder != ' ') {
								BusLines.insertEdge(BusLines.getNode(nd-w), BusLines.getNode(nd), holder);
							}
							else 
								if (holder == ' ' && (j%2) == 0)
								nd++;
						}
					}
				}
				if (i%2 == 1)
					nd = nd-w;
			}
			input.close();
			
			}
	}
		catch (IOException e){
			throw new MapException("The input file does not exist");
		}
		
		
	}
	
	Graph getGraph() throws MapException {
		if (BusLines == null)  //should we find another exception
		{
			throw new MapException("there was an error retrieving the map");
		}
		else {
			return BusLines;
		}
	}
	
	Iterator<GraphNode> trip() 
	{
		return null;
		
	}
//	
	public static void main (String[] args) throws MapException, GraphException {
		DisplayMap display;
		//display = new DisplayMap(args[0]);
	

BusLine2 hi= new BusLine2("map0.txt");
		
	}
	
}
