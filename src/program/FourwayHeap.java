package program;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FourwayHeap {
	private int d;
	private int heapSize;
	private List<HeapNode> fHeap;
	
	public FourwayHeap() {
		heapSize = 0;
		d = 4;
		fHeap = new ArrayList<HeapNode>();
		fHeap.add(new HeapNode("", 0, null, null));
		fHeap.add(new HeapNode("", 0, null, null));
		fHeap.add(new HeapNode("", 0, null, null));
	}
	
	public int size() {
		return fHeap.size();
	}
	
	public HeapNode get(int ind) {
		return fHeap.get(ind);
	}
	
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	private int parent (int ind) {
		return ind / d + 2;
	}
	
	private int kthChild(int ind, int k) {
		return (ind - 2) * d - 1 + k;
	}
	
	public void insert(HeapNode node) {
		fHeap.add(node);
		heapSize ++;
		heapifyUp(heapSize + 2);// 要改
	}
	
	public void clear() {
		fHeap.clear();
		fHeap.add(new HeapNode("", 0, null, null));
		fHeap.add(new HeapNode("", 0, null, null));
		fHeap.add(new HeapNode("", 0, null, null));
		heapSize = 0;
	}
	
	public HeapNode getMin() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return fHeap.get(3);
	}
	
	public void heapifyUp(int childInd) {
		HeapNode tmp = fHeap.get(childInd);
		while (childInd > 3 && tmp.freq < fHeap.get(parent(childInd)).freq) {
			fHeap.set(childInd, fHeap.get(parent(childInd)));
			childInd = parent(childInd);
		}
		fHeap.set(childInd, tmp);
	}
	
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		
		while (k <= d && pos <= heapSize + 2) {
			if (fHeap.get(pos).freq < fHeap.get(bestChild).freq) {
				bestChild = pos;
			}
			pos = kthChild(ind, ++ k);
		}
		return bestChild;
	}
	
	public HeapNode poll() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		HeapNode node = fHeap.get(3);
		fHeap.set(3, fHeap.get(heapSize + 2));
		fHeap.remove(heapSize + 2);
		heapSize --;
		heapifyDown(3);
		return node;
	}
	
	private void heapifyDown(int ind) {
		if (isEmpty()) return;
		int child;
		HeapNode tmp = fHeap.get(ind);
		while (kthChild(ind, 1) <= heapSize + 2) {
			child = minChild(ind);
			if (fHeap.get(child).freq < tmp.freq) {
				fHeap.set(ind, fHeap.get(child));
			}
			else {
				break;
			}
			ind = child;
		}
		fHeap.set(ind, tmp);
	}
	
//	public static void main(String[] args) {
//		String fileName = "C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\sample_input_small.txt";
//		Map<String,Integer> huffinput= new HashMap<>();
//		CreateFreqTable.readFileByLines(fileName, huffinput);
//		FourwayHeap fheap = new FourwayHeap();
//		
//		for (String num : huffinput.keySet()) {
//			fheap.insert(new HeapNode(num, huffinput.get(num), null, null));
//		}
//		
//		for (int i = 3; i < fheap.size(); i ++) {
//			System.out.printf("\nKey: %s, Freq: %d", fheap.get(i).key, fheap.get(i).freq);
//		}
//		
//		
//		while (fheap.size() > 4) {
//			HeapNode node1 = fheap.poll();
//			HeapNode node2 = fheap.poll();
//			HeapNode p = new HeapNode("", node1.freq + node2.freq, node1, node2);
//			fheap.insert(p);
//			for (int i = 3; i < fheap.size(); i ++) {
//				System.out.printf("\nKey: %s, Freq: %d", fheap.get(i).key, fheap.get(i).freq);
//			}
//		}
//	}
	
}
