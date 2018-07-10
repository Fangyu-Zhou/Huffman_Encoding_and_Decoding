package program;

public class HeapNode {
	
		HeapNode left;
		HeapNode right;
		String key;
		int freq;
		
		HeapNode (String x, int y, HeapNode l, HeapNode r) {
			this.key = x;
			this.freq = y;
			this.left = l;
			this.right = r;
		}
}
