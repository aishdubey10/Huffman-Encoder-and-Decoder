
public class FrequencyTableElem {
	private FrequencyTableElem child_nd_left;
	private FrequencyTableElem child_nd_right;
	private int countOfFreq;
	private int putItem;
	
	public FrequencyTableElem(int putItem, int countOfFreq){
		this.putItem=putItem;
		this.countOfFreq=countOfFreq;
		this.child_nd_left=null;
		this.child_nd_right=null;
	}
	
	public FrequencyTableElem getChildLf() {
		return child_nd_left;
	}

	public void setChildLf(FrequencyTableElem child_nd_left) {
		this.child_nd_left = child_nd_left;
	}

	public FrequencyTableElem getChildRg() {
		return child_nd_right;
	}

	public void setChildRg(FrequencyTableElem child_nd_right) {
		this.child_nd_right = child_nd_right;
	}

	public int getPutItem() {
		return putItem;
	}

	public void setPutItem(int putItem) {
		this.putItem = putItem;
	}

	public int getCountOfFreq() {
		return countOfFreq;
	}

	public void setCountOfFreq(int countOfFreq) {
		this.countOfFreq = countOfFreq;
	}
	
	@Override
	public String toString(){
		return (putItem+":"+countOfFreq+"::left="+child_nd_left+"...right="+child_nd_right);
	}
	
}
