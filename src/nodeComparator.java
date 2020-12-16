import java.util.Comparator;


public class nodeComparator implements Comparator<Node> {

	public int compare(Node a, Node b) {
		// TODO Auto-generated method stub
		if(a.fCost()==b.fCost()){
			if(a.hCost>b.hCost){
				return 1;
			}
			else{
				return -1;
			}
		}
		if(a.fCost()>b.fCost()){
			return 1;
		}
		else{
			return -1;
		}
		
	}
	
}
