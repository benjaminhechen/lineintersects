import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
    	File file = new File("input.txt");
    	ArrayList<Double> listInput = new ArrayList<Double>();
    	
    	try 
		{
			Scanner sc = new Scanner(file);
														
			while(sc.hasNextDouble())
			{
				listInput.add(sc.nextDouble());
			}	
			sc.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	
    	Double num = (listInput.get(0));
    	int count = 1;
    	
    	ArrayList<Point> linePoints = new ArrayList<Point>();
    	ArrayList<Line> lines = new ArrayList<Line>();
    	
    	while(count < num*4)
    	{
    		Point point = new Point(listInput.get(count),listInput.get(count+1));
    		//System.out.println(point.toString());
    		linePoints.add(point);
    		count+=2;
    	}
    	
    	int temp = 0;
    	while(temp < num)
    	{
    		Line line = new Line(linePoints.get(temp).x,linePoints.get(temp).y, linePoints.get(temp+1).x,linePoints.get(temp+1).y);
    		lines.add(line);
    		
    		temp+=2;
    	}
    	
    	ArrayList<Point> points = new ArrayList<Point>();
    	while(count < num*6-1)
    	{
    		Point point = new Point(listInput.get(count),listInput.get(count+1));
    		//System.out.println(point.toString());
    		points.add(point);
    		count+=2;
    	}
    	
    	MyBinaryTree tree = new MyBinaryTree();
    	tree.insertRoot(lines);
    	
    	System.out.println("Points 1 and 2: " + points.get(0).toString()+" and "+points.get(1).toString());
    	if (Path(tree,points.get(0)).equals(Path(tree,points.get(1))))
    	{
    		System.out.println("these two points are not separated by a line");
    	}
    	else
    	{
    		System.out.println("these two points are separated by lines : ");
    		Line testLine = new Line(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y);
    		
    		for(int i = 0; i<lines.size(); i++)
    		{
    			if(isIntersect(testLine,lines.get(i)))
    			{
    				System.out.println(lines.get(i).toString());
    			}
    		}
    	}
    	
    	System.out.println("Points 3 and 4: " + points.get(2).toString()+" and "+points.get(3).toString());
    	if (Path(tree,points.get(2)).equals(Path(tree,points.get(3))))
    	{
    		System.out.println("these two points are not separated by a line");
    	}
    	else
    	{
    		System.out.println("these two points are separated by lines : ");
    		Line testLine = new Line(points.get(2).x,points.get(2).y,points.get(3).x,points.get(3).y);
    		
    		for(int i = 0; i<lines.size(); i++)
    		{
    			if(isIntersect(testLine,lines.get(i)))
    			{
    				System.out.println(lines.get(i).toString());
    			}
    		}
    	}
    	
    	//System.out.println(points.toString());
    	//System.out.println(lines.toString());
    	//System.out.println(linePoints.toString());
    	//System.out.println(listInput.toString());
    	
    	//Point p1 = new Point(0.74,0.5);
    	//Point p2 = new Point(0.5,0);
    	//Point p3 = new Point(0.5,1);
    	
    	//System.out.println(ccw(p1,p2,p3));
    	
    	//Line line1 = new Line(0.5,1,0.5,0);
    	//Line line2 = new Line(0,0.5,1,0.5);
    	//System.out.println(isIntersect(line1,line2));
    }
   
   //public boolean process(ArrayList<Line> lines) 
    
   public static int ccw(Point p0, Point p1, Point p2) 
   {
	   //counterclockwise = 1;
	   //clockwise = 2;
	   //colinear = 3;
    	 double dx1 = p1.x - p0.x;
    	 double dy1 = p1.y - p0.y;
    	 double dx2 = p2.x - p0.x;
    	 double dy2 = p2.y - p0.y;
    	 if (dx1*dy2 > dy1*dx2) 
    		 return 1;
    	 else if (dx1*dy2 < dy1*dx2) 
    		 return 2;
    	 else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) 
    		 return 2;
    	 else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) 
    		 return 1;
    	 else 
    		 return 3;
    }
   
   public static boolean isIntersect(Line line1, Line line2)
   {
	   Point line2APoint = new Point(line2.x1,line2.y1);
	   Point line2BPoint = new Point(line2.x2,line2.y2);
	   
	   Point comparePointA = new Point(line1.x1,line1.y1);
	   Point comparePointB = new Point(line1.x2,line1.y2);
	   
	   if (ccw(line2APoint,comparePointA,comparePointB) != ccw(line2BPoint,comparePointA,comparePointB))
	   {
		   return true;
	   }
	   	   return false;
   }
   
   public static ArrayList<String> Path(MyBinaryTree tree, Point point)
   {
	   ArrayList<String>  result = new ArrayList<String>();
	   MyTreeNode currentNode = new MyTreeNode();
	   currentNode = tree.root;
	   
	   while (currentNode != null) 
	   {
		   Line currentLine = new Line(currentNode.data.x1,currentNode.data.y1,currentNode.data.x2,currentNode.data.y2);
		   Point currentLinePointA = new Point(currentNode.data.x1,currentNode.data.y1);
		   Point currentLinePointB = new Point(currentNode.data.x2,currentNode.data.y2);
		   
		   if(ccw(point,currentLinePointA,currentLinePointB) == 1)
	       {
			   result.add("cc");
			  // System.out.println("cc");
			   currentNode = currentNode.leftChild;
	       }
		   else if(ccw(point,currentLinePointA,currentLinePointB) == 2)
		   {
			   result.add("c");
			  // System.out.println("c");
			   currentNode = currentNode.rightChild;
	       }
	    }
	   
	   return result;
   }
}