package program;
import java.io.*;
import java.util.*;
public class CreateFreqTable {

	public static void readFileByLines(String fileName, Map<String,Integer> huffinput, List<String> list) {
		File file = new File(fileName);
		BufferedReader reader = null;
		
		try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                //line++;
            	list.add(tempString);
                if (huffinput.containsKey(tempString)) {
                	huffinput.put(tempString, huffinput.get(tempString) + 1);
                }
                else {
                	huffinput.put(tempString, 1);
                }
                
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

//	public static void main(String[] args) {
//		String fileName = "C:\\Users\\candy\\OneDrive - University of Florida\\ADS\\project\\sample_input_small.txt";
//		Map<String,Integer> huffinput= new HashMap<>();
//		readFileByLines(fileName, huffinput);
//		for (String num : huffinput.keySet()) {
//			System.out.printf("\nKey: %s, Value: %d", num, huffinput.get(num));
//		}
//	}
}
