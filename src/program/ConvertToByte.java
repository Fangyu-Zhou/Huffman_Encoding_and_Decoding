package program;
import java.util.*;

public class ConvertToByte {
	public static byte[] toByte(List<String> oringal, Map<String,String> map) {
		ArrayList<Byte> bits = new ArrayList<Byte>();
		byte target = 0;
		int bitCount = 0;
		
		for (String s : oringal) {
			for (char bit : map.get(s).toCharArray()) {
				if (bit == '1') {
					target |=  1 << (7 - bitCount);
				}
				bitCount++;
				
				if (bitCount == 8) {
					bitCount = 0;
					bits.add(target);
					target = 0;
				}
			}
		}
		
		//if (bitCount != 0) bits.add(target);
		
		
		byte[] bitstring = new byte[bits.size()];
		
		for (int i = 0; i < bits.size(); i ++) {
			bitstring[i] = bits.get(i);
		}
		return bitstring;
	}
	
	public static void main (String[] args) {
//		String origin = "10000000";
//		byte[] res = toByte(origin);
//		for (int i = 0; i < res.length; i ++) {
		byte temp = 1;
		
		if (((temp << 7) & 128) != 0)
			System.out.println(temp);
		//}
		
	}
}
