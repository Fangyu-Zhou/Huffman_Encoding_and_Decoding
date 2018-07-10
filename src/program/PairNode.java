package program;

public class PairNode {
	String key;
	int freq;
	PairNode leftChild;
	PairNode nextSibling;
	PairNode prev;
	PairNode left;
	PairNode right;
	
	public PairNode(String x, int y, PairNode l, PairNode r ) {
		this.key = x;
		this.freq = y;
		leftChild = null;
		nextSibling = null;
		prev = null;
		left = l;
		right = r;
	}
}
