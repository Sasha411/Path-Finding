import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;


public class Maze_algo {

	static int size;
	static int np;
	static int x1;
	static int y1;
	static int x2;
	static int y2;
	
	public Maze_algo(int size,int np,int x1,int y1,int x2,int y2) {
		// TODO Auto-generated constructor stub
		Maze_algo.size = size;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.np = np;
		
	}

	public ArrayList<ArrayList<Node>> solve() {
		// TODO Auto-generated method stub
		Comparator<Node> compare = new nodeComparator();
		PriorityQueue<Node> open = new PriorityQueue<Node>(1000,compare);
		
		//Closed HashMap storing already visited Node
		HashSet<Node> closed = new HashSet<Node>();
				
		//Store Path
		ArrayList<Node> path = new ArrayList<Node>();
		
		
		Node start = new Node(0,getDistance(new Node(0,0,x1,y1),new Node(0,0,x2,y2)),x1,y1);
		Node target = new Node(getDistance(new Node(0,0,x1,y1),new Node(0,0,x2,y2)),0,x2,y2);
		
		open.add(start);
		int ans = 0;
		closePath(closed);
		ArrayList<ArrayList<Node>> pack = new ArrayList<ArrayList<Node>>();
		ArrayList<Node> cld = new ArrayList<Node>();
		for(Node r: closed){
			cld.add(r);
		}
		Node current = null;
		while(!open.isEmpty()){
			current = open.poll();
			closed.add(current);
			
			if(current.equals(target)){
				target.parent = current;
				path = retracePath(target);
				ans = 1;
				break;
			}
			
			for(Node neighbour: getNeighbour(current)){
				if(isClosed(closed,neighbour)){
					continue;
				}
				
				int newDist = current.gCost + getDistance(current, neighbour);
				
				if(neighbour.gCost>newDist || !isPresent(open,neighbour)){
					neighbour.gCost = newDist;
					neighbour.hCost = getDistance(neighbour,target);
					neighbour.parent = current;
					
					if(!isPresent(open,neighbour)){
						open.add(neighbour);
					}
					
				}
				
			}
			
			
		}
		if(ans == 0){
			path = retracePath(current);
		}
		//Grid requirements
		pack.add(path);
		pack.add(cld);
		return pack;
		
		
		
	}
	
	private static int getDistance(Node a, Node b) {
		// TODO Auto-generated method stub
		int distX = Math.abs(a.x-b.x);
		int distY = Math.abs(a.y-b.y);
		if(distX>distY){
			return 14*distY + 10*(distX-distY);
		}
		return 14*distX + 10*(distY-distX);
	}
	
	private static void closePath(HashSet<Node> closed) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int total_closed = size*size*np;
		total_closed = total_closed/100;
		if(total_closed==0){
			total_closed++;
		}
		int i=0;
		while(i<total_closed){
			int randX = rand.nextInt(size + 1);
			int randY = rand.nextInt(size + 1);
			if(randX==0){
				randX++;
			}
			if(randY==0){
				randY++;
			}
			if(randX == x2 && randY == y2){
				i--;
				continue;
			}
			closed.add(new Node(0,0,randX,randY));
			i++;
		}
	}
	private static ArrayList<Node> retracePath(Node target) {
		// TODO Auto-generated method stub
		ArrayList<Node> path = new ArrayList<Node>();
		Node current = target;
		while(current!=null){
			path.add(current);
			current = current.parent;
		}
		Collections.reverse(path);
		return path;
	}

	private static ArrayList<Node> getNeighbour(Node current) {
		// TODO Auto-generated method stub
		ArrayList<Node> neighbours = new ArrayList<Node>();
		for(int x=-1;x<=1;x++){
			for(int y=-1;y<=1;y++){
				if(x==0 && y==0)
					continue;
				
				int X = x + current.x;
				int Y = y + current.y;
				
				if(X>0 && X<=size && Y>0 && Y<=size ){
					neighbours.add(new Node(0,0,X,Y));
				}
			}
		}
		return neighbours;
	}

	private static boolean isClosed(HashSet<Node> closed, Node neighbour) {
		// TODO Auto-generated method stub
		for(Node temp: closed){
			if(temp.x == neighbour.x && temp.y == neighbour.y){
				return true;
			}
		}
		return false;
	}

	private static boolean isPresent(PriorityQueue<Node> open, Node neighbour) {
		// TODO Auto-generated method stub
		for(Node temp: open){
			if(temp.x == neighbour.x && temp.y == neighbour.y){
				return true;
			}
		}
		return false;
	}

	
}
