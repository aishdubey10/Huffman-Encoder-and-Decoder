import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class decoder {

	public static void main(String and_rights[]){
		final String fileAsBinary = and_rights[0];
		final String fileAsDecoded = "decoded.txt";
		final String fileAsCodeTable = and_rights[1];

		//Read code table and create hash table				
		FrequencyTableElem nd_rootnode = new FrequencyTableElem(-1,0);	//nd_root node
		FrequencyTableElem nd_current = null;
		
		try {
			File file = new File(fileAsCodeTable);
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				nd_current = nd_rootnode;
				
				if(line.trim().equals("")){
					continue;
				}
				
				String[] divide = line.split(" ");
				
				String code = divide[1];
				String pict = divide[0];
				
				for(int i=0; i<code.length(); i++){
					char c = code.charAt(i);
					if(c=='0'){
						if(nd_current.getChildLf()==null){
							FrequencyTableElem nd_new;
							if(i==code.length()-1){	//leaf node
								nd_new = new FrequencyTableElem(Integer.parseInt(pict),0);
							}
							else{
								nd_new = new FrequencyTableElem(-1,0);
							}
							
							nd_current.setChildLf(nd_new);
							nd_current = nd_new;
						}
						else{
							nd_current = nd_current.getChildLf();
						}
					}
					else{		//got 1
						if(nd_current.getChildRg()==null){
							FrequencyTableElem nd_new;
							if(i==code.length()-1){	//leaf node
								nd_new = new FrequencyTableElem(Integer.parseInt(pict),0);
							}
							else{
								nd_new = new FrequencyTableElem(-1,0);
							}
							
							nd_current.setChildRg(nd_new);
							nd_current = nd_new;
						}
						else{
							nd_current = nd_current.getChildRg();
						}
					}
				}
				
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		FileInputStream objFInp =null;
		byte[] ArrOfBytes =null;
		try {
			File file = new File(fileAsBinary);
			
			//init array with file length
			ArrOfBytes = new byte[(int) file.length()];

			objFInp = new FileInputStream(file);
			objFInp.read(ArrOfBytes); //read file into bytes[]
			objFInp.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder codeLight = new StringBuilder("");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(fileAsDecoded);	
			bw = new BufferedWriter(fw);
			//fw.write("");
			
			
			for (byte b : ArrOfBytes) {
				codeLight.append(Integer.toBinaryString(b & 255 | 256).substring(1));					
				nd_current = nd_rootnode;
				int codelen = codeLight.length();
				int remtill=-1;
				for(int i=0; i<codelen;i++){
					if(codeLight.charAt(i)=='0'){
						nd_current=nd_current.getChildLf();
						if(nd_current.getChildLf()==null && nd_current.getChildRg()==null){	//leaf node
							bw.write(nd_current.getPutItem()+"\n");
							remtill=i;
							nd_current=nd_rootnode;
						}
					}
					else{
						nd_current=nd_current.getChildRg();
						if(nd_current.getChildLf()==null && nd_current.getChildRg()==null){	//leaf node
							bw.write(nd_current.getPutItem()+"\n");
							remtill=i;
							nd_current=nd_rootnode;
						}
					}
					
				}
				codeLight.delete(0, remtill+1);
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				bw.flush();
				fw.flush();
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	}
	

