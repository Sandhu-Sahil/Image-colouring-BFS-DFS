import java.awt.Color;
import java.util.*;
import java.util.Iterator;

public class GraphAlgorithms{

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/	

	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour)	
	{
		LinkedList<PixelVertex> visited = new LinkedList<PixelVertex>();
		LinkedList<PixelVertex> stack = new LinkedList<PixelVertex>();
		stack.push(v);
		while(!stack.isEmpty())
		{
			PixelVertex cur = stack.pop();
			if(!visited.contains(cur))
			{
				visited.add(cur);
				writer.setPixel(cur.getX(), cur.getY(), fillColour);
				Iterator<PixelVertex> it = cur.getNeighbours().iterator();
				while(it.hasNext())
				{
					PixelVertex n = it.next();
					stack.push(n);
				}
			}
		}
	}


	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	private static LinkedList<PixelVertex> explored = new LinkedList<PixelVertex>();

	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour)
	{
		writer.setPixel(v.getX(), v.getY(), fillColour);
		// TODO: implement this method
		explored.add(v);
		LinkedList<LinkedList<PixelVertex>> level = new LinkedList<LinkedList<PixelVertex>>();
		level.add(new LinkedList<PixelVertex>());			// L0
		level.get(0).add(v);
		int i = 0;
		while (!level.get(i).isEmpty())
		{
			level.add(new LinkedList<PixelVertex>());		// L(i+1)
			int num = level.get(i).size();
			for(int j = 0; j < num; j++)
			{
				PixelVertex w = level.get(i).get(j);
				LinkedList<PixelVertex> nbrs = w.getNeighbours();
				int nd = w.getDegree();
				for(int k = 0; k < nd; k++)
				{
					PixelVertex a = nbrs.get(k);
					if(!explored.contains(a))
					{
						explored.add(a);
						writer.setPixel(a.getX(), a.getY(), fillColour);
						level.get(i+1).add(a);
					}
				}
			}
			i++;
		}
		explored.clear();
	}
	
}