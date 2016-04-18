import java.util.ArrayList;

public class MyBinaryTree 
{
	public MyTreeNode root;
	
	public MyBinaryTree()
	{
		root = null;
	}
	
	public void insertRoot(ArrayList<Line> lines)
	{
		root = new MyTreeNode();
		root.data = lines.get(0);
		lines.remove(0);
		popTree(root,lines);
	}
	
	public void popTree(MyTreeNode root, ArrayList<Line> lines)
	{
		ArrayList<Line> leftSide = new ArrayList<Line>();
		ArrayList<Line> rightSide = new  ArrayList<Line>();
		
		for (int i = 0; i < lines.size(); i++)
		{
			Point p0 = new Point(lines.get(i).x1,lines.get(i).y1);
			Point p1 = new Point(root.data.x1,root.data.y1);
			Point p2 = new Point(root.data.x2,root.data.y2);
			
			if(Driver.isIntersect(root.data,lines.get(i)))
		  	{
				leftSide.add(lines.get(i));
				rightSide.add(lines.get(i));
			}
		    else if(Driver.ccw(p0, p1, p2) == 2)
		    {
		    	rightSide.add(lines.get(i));
		  	}
		    else if(Driver.ccw(p0, p1, p2) == 1)
		    {
		    	leftSide.add(lines.get(i));
	        }	
		}
		
		while(leftSide.isEmpty())
		{
			root.leftChild = new MyTreeNode();
			root.leftChild.data = leftSide.get(0);
			leftSide.remove(0);
			popTree(root.leftChild,leftSide);
		}
		while(rightSide.isEmpty())
		{
			root.rightChild = new MyTreeNode();
			root.rightChild.data = rightSide.get(0);
			rightSide.remove(0);
			popTree(root.rightChild,rightSide);
		}
	}	
}
