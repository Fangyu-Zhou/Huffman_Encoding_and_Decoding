package program;


public class PairHeap {
	private PairNode root;
	private PairNode[] treeArray = new PairNode[5];
	int pairHeapSize;
	
	public PairHeap() {
		root = null;
		pairHeapSize = 0;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int size() {
		return pairHeapSize;
	}
	
	public void clear() {
		root = null;
		pairHeapSize = 0;
		
	}
	
	public void insert(PairNode newNode) {
		if (root == null) {
			root = newNode;
		}
		else {
			root = compareAndLink(root, newNode);
		}
		pairHeapSize ++;
	}
	
	private PairNode compareAndLink(PairNode first, PairNode second) {
		if (second == null) {
			return first;
		}
		
		if (second.freq < first.freq) {
			second.prev = first.prev;
            first.prev = second;
            first.nextSibling = second.leftChild;
            if (first.nextSibling != null){
            	first.nextSibling.prev = first;
            }
            second.leftChild = first;
            return second;
		}
		else {
            second.prev = first;
            first.nextSibling = second.nextSibling;
            if (first.nextSibling != null){
            	first.nextSibling.prev = first;
            }
            second.nextSibling = first.leftChild;
            if (second.nextSibling != null){
            	second.nextSibling.prev = second;
            }
            first.leftChild = second;
            return first;
		}
	}
	
	private PairNode combineSiblings(PairNode firstSibling) {
        if( firstSibling.nextSibling == null ){
        	return firstSibling;
        }
        
        int numSiblings = 0;
        
        for (;firstSibling != null; numSiblings ++) {
        	treeArray = doubleIfFull(treeArray, numSiblings);
        	treeArray[numSiblings] = firstSibling;
        	firstSibling.prev.nextSibling = null;
        	firstSibling = firstSibling.nextSibling;
        }
        
        treeArray = doubleIfFull( treeArray, numSiblings);
        treeArray[numSiblings] = null;
        
        int i = 0;
        for (; i + 1 < numSiblings; i += 2) {
        	treeArray[i] = compareAndLink(treeArray[i], treeArray[i + 1]);
        }
        
        int j = i - 2;
        if (j == numSiblings - 3) {
        	treeArray[j] = compareAndLink(treeArray[j], treeArray[j + 2]);
        }
        
        for ( ; j >= 2; j -= 2){
            treeArray[j - 2] = compareAndLink(treeArray[j-2], treeArray[j]);
        }
        return treeArray[0];
	}
	
	private PairNode[] doubleIfFull(PairNode[] array, int index) {
		if (index == array.length) {
			PairNode[] oldArray = array;
			array = new PairNode[index * 2];
			for (int i = 0; i < index; i ++) {
				array[i] = oldArray[i];
			}	
		}
		return array;
	}
	
	public PairNode poll() {
		if (isEmpty()) {
			return null;
		}
		PairNode x = root;
		if (root.leftChild == null) {
			root = null;
		}
		else {
			root = combineSiblings(root.leftChild);
		}
		pairHeapSize --;
		return x;
	}
	
    public void inorder()

    {
        inorder(root);
    }
	
    private void inorder(PairNode r) {
        if (r != null)

        {
            inorder(r.leftChild);
            System.out.print("\n" + r.key +" " + r.freq);
            inorder(r.nextSibling);
        }
    }
    
//    public static void main(String[] args) {
//		String fileName = "C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\sample_input_small.txt";
//		Map<String,Integer> huffinput= new HashMap<>();
//		CreateFreqTable.readFileByLines(fileName, huffinput);
//		PairHeap pheap = new PairHeap();
//		
//		for (String num : huffinput.keySet()) {
//			pheap.insert(new PairNode(num, huffinput.get(num)));
//		}
//		
//		while (pheap.size() > 1) {
//			PairNode node1 = pheap.poll();
//			PairNode node2 = pheap.poll();
//			PairNode p = new PairNode("", node1.freq + node2.freq);
//			p.left = node1;
//			p.right = node2;
//			pheap.insert(p);
//		}
//    }
}
