
public class InternalNodeOfTree extends NodeOfTree{
	public InternalNodeOfTree()
	{
		
	}
	public InternalNodeOfTree(NodeOfTree a,NodeOfTree b)
	{
		if(a.nd_value<b.nd_value)
        {
        	this.nd_left = a;
        	this.nd_right = b;
        	
        }
        else
        {
        	this.nd_left = b;
        	this.nd_right = a;
        }
	
	
	this.nd_value = a.nd_value + b.nd_value;
	nd_root = this;
	}
	
	}
