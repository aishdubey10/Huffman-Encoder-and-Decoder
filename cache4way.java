import java.util.Vector;
	
public class cache4way {

	    private Vector<NodeOfTree> vctList;

	    public cache4way() {

	        this.vctList = new Vector<NodeOfTree>();
	    }

	    public cache4way(Vector<NodeOfTree> pieces) {

	        this.vctList = pieces;
	        heap_making();
	    }

	    public void addingInto(NodeOfTree piece) {

	        vctList.add(piece);
	        int i = vctList.size() - 1;
	        int pr = pr(i);

	        while (pr != i && vctList.get(i).nd_value < vctList.get(pr).nd_value && pr>=3) {

	            swap(i, pr);
	            i = pr;
	            pr = pr(i);
	        }
	        
	    }

	    public void heap_making() {
	    	int beginPosition = ((vctList.size()-4) / 4) +3;

	        for (int i = beginPosition; i >= 3; i--) {
	            minHeapify(i);
	        }
	        
	        
	    }

	    public NodeOfTree minFinding() {

	        if (vctList.size() == 3) {

	            throw new IllegalStateException("MinHeap is EMPTY");
	        } else if (vctList.size() == 4) {

	        	NodeOfTree min = vctList.remove(3);
	            return min;
	        }

	        // remove the last piece ,and set it as new root
	        NodeOfTree min = vctList.get(3);
	        NodeOfTree endPiece = vctList.remove(vctList.size() - 1);
	        vctList.set(3, endPiece);

	        // bubble-down until heap property is maintained
	        minHeapify(3);

	        return min;
	    }


	    private void minHeapify(int i) {
	    	
	    	
	    	if (i<3)
	    	{
	    		return;
	    	}

	        int nd_left = nd_left(i);
	        int nd_right = nd_right(i);
	        int centre1 = centre1(i);
	        int centre2 = centre2(i);
	        int least = -1;

	        // find the least key between current node and its children.
	        if (nd_left <= vctList.size() - 1 && vctList.get(nd_left).nd_value < vctList.get(i).nd_value) {
	            least = nd_left;
	        } else {
	            least = i;
	        }
	        
	        if (centre1 <= vctList.size() - 1 && vctList.get(centre1).nd_value < vctList.get(least).nd_value) {
	            least = centre1;
	        }
	        
	        if (centre2 <= vctList.size() - 1 && vctList.get(centre2).nd_value < vctList.get(least).nd_value) {
	            least = centre2;
	        }
	        
	        if (nd_right <= vctList.size() - 1 && vctList.get(nd_right).nd_value < vctList.get(least).nd_value) {
	            least = nd_right;
	        }

	        // if the least key is not the current key then bubble-down it.
	        if (least != i) {

	            swap(i, least);
	            minHeapify(least);
	        }
	    }

	    public NodeOfTree minGetting() {

	        return vctList.get(3);
	    }

	    public boolean emptyFinding() {

	        return vctList.size() == 3;
	    }

	    private int nd_right(int i) {

	        return 4 * i - 5;
	    }

	    private int nd_left(int i) {

	        return 4 * i - 8;
	    }
	    
	    private int centre1(int i) {

	        return 4 * i - 6;
	    }
	    
	    private int centre2(int i) {

	        return 4 * i - 7;
	    }
	    

	    private int pr(int i) {

	        if ((i-3) % 4 == 1) {
	            return ((i-3) / 4)+3;
	        }

	        return ((i - 4) / 4)+3;
	    }

	    private void swap(int i, int pr) {

	    	NodeOfTree temp = vctList.get(pr);
	        vctList.set(pr, vctList.get(i));
	        vctList.set(i, temp);
	    }
	    
	    public int sizeOfHeap()
	    {
	    	return vctList.size();
	    }

	}

