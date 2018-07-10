package program;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decode {
	public static void buildDecodeTree(TreeNode root, Map<String, String> map) {
		TreeNode temp = root;
		String sub;
		for (String key : map.keySet()) {
			sub = "";
			for (char ch : map.get(key).toCharArray()) {
				if (ch == '0') {
					sub += "0";
					if (temp.left == null) {
						TreeNode node = new TreeNode(key, sub);
						temp.left = node;
						temp = temp.left;
					}
					else {
						temp = temp.left;
					}
				}
				else {
					sub += '1';
					if (temp.right == null) {
						TreeNode node = new TreeNode(key, sub);
						temp.right = node;
						temp = temp.right;
					}
					else {
						temp = temp.right;
					}
				}
			}
			temp = root;
		}
		//return root;
	}
	
	public static void readAndBuildMap (String fileName, Map<String, String> map) {
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
                String[] line = tempString.split(" ");
                map.put(line[0], line[1]);
                
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void readBin(List<String> decodedata, byte[] reader, TreeNode root) {
		Byte temp = 0;
		TreeNode cur = root;
		
			for (int j = 0; j < reader.length; j ++) {
				temp = reader[j];
				int wei = 128;				
				for (int i = 0; i < 8; i ++) {
					if (cur.left == null && cur.right == null) {
						decodedata.add(cur.key);
						cur = root;
					}
				
						if ((temp & wei) == 0) {
							cur = cur.left;
						}
						else {
							cur = cur.right;
						}
					
					wei = wei >> 1;
				}
			}
			decodedata.add(cur.key);

            
	} 
		

	
	
	
	public static void main(String[] args) {
		String fileName = "resource\\code_table.txt";
		Map<String,String> map= new HashMap<>();
		readAndBuildMap(fileName, map);
		
//		for (String key : map.keySet()) {
//			System.out.println(key + " " + map.get(key));
//		}
		
		TreeNode root = new TreeNode("", "");
		buildDecodeTree(root, map);
//		System.out.println();
		//System.out.println(root.right.right.right.key);
		
		String encodedFileName =  "resource\\encoded.bin";
		List<String> decodedata = new ArrayList<String>();
		File enCodeFile = new File(encodedFileName);
		
		byte[] reader = new byte[(int)enCodeFile.length()];
		try  {  
			DataInputStream in = new DataInputStream(  
                    new BufferedInputStream(  
                    new FileInputStream(encodedFileName)));
			in.read(reader);
		}
		catch (Exception e)  {  
            e.printStackTrace();  
        }
		
		readBin(decodedata, reader, root);
		
		try {
			File writename = new File("resource\\jieguoda.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for (int i = 0; i < decodedata.size(); i ++) {
				out.write(decodedata.get(i) + "\n");
				out.flush();
			}
			out.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
			System.out.println(decodedata.get(decodedata.size() - 1));
		
	}
}
