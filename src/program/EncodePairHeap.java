package program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncodePairHeap {
	
	public static void buildCode(Map<String, String> map, PairNode x, String s) {
	    if (!(x.left == null && x.right == null)) {  
	        buildCode(map, x.left,  s + '0');  
	        buildCode(map, x.right, s + '1');  
	    }  
	    else {  
	        map.put(x.key, s);  
	    }  
	}
	
	
	public static void main(String[] args) {
		String fileName = "C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\sample_input_large.txt";
		Map<String,Integer> huffinput= new HashMap<>();
		List<String> original = new ArrayList<String>();
		CreateFreqTable.readFileByLines(fileName, huffinput, original);
		PairHeap pheap = new PairHeap();
		long duration = 0;
		
		for (int i = 0; i < 10; i ++) {
			long startTime=System.currentTimeMillis();
			
			for (String num : huffinput.keySet()) {
				pheap.insert(new PairNode(num, huffinput.get(num), null, null));
			}
			
			while (pheap.size() > 1) {
				PairNode node1 = pheap.poll();
				PairNode node2 = pheap.poll();
				PairNode p = new PairNode("", node1.freq + node2.freq, node1, node2);
	//			p.left = node1;
	//			p.right = node2;
				pheap.insert(p);
			}
			
			long endTime=System.currentTimeMillis();
			duration += endTime - startTime;
			
		}
		
		duration = duration / 10;
		
		System.out.println(duration);
		
//		PairNode root = pheap.poll();
//		Map<String, String> map = new HashMap<String, String>();
//		buildCode(map, root, "");
//		
//		for (String num : map.keySet()) {
//			System.out.printf("\nKey: %s, Huff: %s", num, map.get(num));
//		}
    }
}
