package program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.File;   
import java.io.BufferedWriter;   
import java.io.FileWriter; 

public class Encode4way {
	
		public static void buildCode(Map<String, String> map, HeapNode x, String s) {
		    if (!(x.left == null && x.right == null)) {  
		        buildCode(map, x.left,  s + '0');  
		        buildCode(map, x.right, s + '1');  
		    }  
		    else {  
		        map.put(x.key, s);  
		    }  
		}
		
	public static void main(String[] args) {
//		String fileName = "C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\sample_input_large.txt";
		String fileName = "sample_input_large.txt";
		Map<String,Integer> huffinput= new HashMap<>();
		List<String> original = new ArrayList<String>();
		CreateFreqTable.readFileByLines(fileName, huffinput, original);
		FourwayHeap fheap = new FourwayHeap();
		//long duration = 0;
		
		//for (int i = 0; i < 10; i ++) {
		
			long startTime=System.currentTimeMillis();
			
			for (String num : huffinput.keySet()) {
				fheap.insert(new HeapNode(num, huffinput.get(num), null, null));
			}
					
			while (fheap.size() > 4) {
				HeapNode node1 = fheap.poll();
				HeapNode node2 = fheap.poll();
				HeapNode p = new HeapNode("", node1.freq + node2.freq, node1, node2);
				fheap.insert(p);
			}
			
			long endTime=System.currentTimeMillis();
			System.out.println(endTime - startTime);
		//	duration += endTime - startTime;
		//	fheap.clear();
		//}
		
		//duration = duration / 10;
		//System.out.println(duration);
		
		
		
//		HeapNode root = fheap.poll();
//		
//		Map<String, String> map = new HashMap<String, String>();
//		buildCode(map, root, "");
////		for (String num : map.keySet()) {
////			System.out.printf("\nKey: %s, Huff: %s", num, map.get(num));
////		}
//		//Set<String> set = new HashSet<String>(map.values());
//		try {
////			File writename = new File("C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\code_table.txt");
//			File writename = new File("resource\\code_table.txt");
//			writename.createNewFile();
//			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
//			for (String num : map.keySet()) {
//				out.write(num + " " + map.get(num) + "\n");
//				out.flush();
//			}
//			out.close();
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
		
		
		
		
//		for (String num : set) {
//			System.out.println(num);
//		}
		
		//10110000010110110001
	}
}
