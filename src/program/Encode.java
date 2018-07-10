package program;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedOutputStream; 
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Encode {
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
		MinHeap mheap = new MinHeap();
		
//		long duration = 0;;
//		
//		for (int i = 0; i < 10; i ++) {
//		
//			long startTime=System.currentTimeMillis();
			
			for (String num : huffinput.keySet()) {
				mheap.insert(new HeapNode(num, huffinput.get(num), null, null));
			}
			
			while (mheap.size() > 1) {
				HeapNode node1 = mheap.poll();
				HeapNode node2 = mheap.poll();
				HeapNode p = new HeapNode("", node1.freq + node2.freq, node1, node2);
				mheap.insert(p);
			}
//			long endTime=System.currentTimeMillis();
//			
//			duration += endTime - startTime;
//			mheap.clear();
//			System.out.println(endTime - startTime);
//		}
//		
//		duration = duration / 10;
//		System.out.println(duration);
		
		
		HeapNode root = mheap.poll();
		
		Map<String, String> map = new HashMap<String, String>();
		buildCode(map, root, "");
		
//		for (String num : map.keySet()) {
//			System.out.printf("\nKey: %s, Huff: %s", num, map.get(num));
//		}
		try {
//			File writename = new File("C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\code_table.txt");
			File writename = new File("code_table.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for (String num : map.keySet()) {
				out.write(num + " " + map.get(num) + "\n");
				out.flush();
			}
			out.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		//Set<String> set = new HashSet<String>(map.values());
		
//		String encodename="C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\encoded.bin";
		String encodename="encoded.bin";
		
//		String temp = "";
		try  {  
            DataOutputStream out=new DataOutputStream(  
                                 new BufferedOutputStream(  
                                 new FileOutputStream(encodename)));  
//            for (String key : original) { 
//            	temp += map.get(key);
////            	out.writeBytes(map.get(key));
////            	byte[] strings = ConvertToByte.toByte(map.get(key));
////            	out.write(strings);
//            }
            byte[] strings = ConvertToByte.toByte(original, map);
        	out.write(strings);
            out.close();  
        } catch (Exception e)  {  
            e.printStackTrace();  
        }
	}
}
