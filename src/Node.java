
public class Node {

	int gCost;
	int hCost;
	int x;
	int y;
	Node parent;
	
	public Node(int gCost,int hCost,int x, int y) {
		// TODO Auto-generated constructor stub
		this.gCost = gCost;
		this.hCost = hCost;
		this.x = x;
		this.y = y;
		this.parent = null;
	}
	
	public int fCost(){
		return this.gCost + this.hCost;
	}
	
	@Override
	public boolean equals(Object o){
		if(o==this){
			return true;
		}
		if(!(o instanceof Node)){
			return false;
		}
		
		Node c = (Node) o;
		return (Integer.compare(c.x, x) == 0 && Integer.compare(c.y, y)==0);
	}

}
