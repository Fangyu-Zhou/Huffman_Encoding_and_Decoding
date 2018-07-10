package program;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

public class MinHeap {
	
	List<HeapNode> mHeap;
	
	//Initialize
	public MinHeap() {
		this.mHeap = new ArrayList<HeapNode>();
	}
	
	//clear
	public void clear() {
		mHeap.clear();
	}
	
	//Heapyfy up the heap
	protected void heapyUp(int ind) {
		int cur = ind;
		int p = (cur - 1) / 2;
		HeapNode temp = mHeap.get(cur);
		
		while(cur > 0) {
			int cmp = mHeap.get(p).freq;
			int res = temp.freq - cmp;
			if (res >= 0) {
				break;
			}
			else {
				mHeap.set(cur, mHeap.get(p));
				cur = p;
				p = (p - 1) / 2;
			}
		}
		mHeap.set(cur, temp);
	}
	
	//inset a value into heap
	public void insert(HeapNode data) {
		int size = mHeap.size();
		mHeap.add(data);
		heapyUp(size);
	}
	
	//get min value
	public HeapNode peek() {
		return mHeap.get(0);
	}
	
	//get value
	public HeapNode get(int index) {
		return mHeap.get(index);
	}
	
	//get size
	public int size() {
		return mHeap.size();
	}
	
	//Heapfy down the heap
	protected void heapyDown(int end){
		int cur = 0;
		int l = 2 * cur + 1;
		HeapNode temp = mHeap.get(cur);
		
		while (l < end) {
			int cmp = mHeap.get(l).freq - mHeap.get(l + 1).freq;
			if (l < end && cmp > 0) {
				l ++;
			}
			cmp = temp.freq - mHeap.get(l).freq;
			if (cmp <= 0) {
				break;
			}
			else {
				mHeap.set(cur, mHeap.get(l));
				cur = l;
				l = 2 * l + 1;
			}
		}
		
		if (l == end) {
			int cmp = temp.freq - mHeap.get(l).freq;
			if (cmp > 0) {
				mHeap.set(cur, mHeap.get(l));
				cur = l;
			}
		}
		mHeap.set(cur, temp);
	}
	
	private void deleteMin() {
		if (mHeap.isEmpty()) return;
		int size = mHeap.size();
		mHeap.set(0, mHeap.get(size - 1));
		mHeap.remove(size - 1);
		if (mHeap.size() > 1) {
			heapyDown(mHeap.size() - 1);
		}
	}
	
	//poll
	public HeapNode poll() {
		HeapNode node = peek();
		deleteMin();
		return node;
	}
}