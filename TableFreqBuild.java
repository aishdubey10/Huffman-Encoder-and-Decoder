import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Vector;

public class TableFreqBuild {
	
	private BufferedReader objBr;
	public Vector<String> vctInp = new Vector<String>();
	public LinkedHashMap<String, Integer> countOfFreqMap = new LinkedHashMap<String, Integer>();
	
	public  TableFreqBuild(BufferedReader b) throws IOException, NumberFormatException
	
	{
		objBr = b;
		produce_countOfFreqTable();
		
	}
	
	private void produce_countOfFreqTable() 
	{
		String presentStr;
		
		try {
			while ((presentStr = objBr.readLine()) != null) {
				
				if(presentStr.trim().equals(""))
				{
					continue;
				}
				else if(Integer.parseInt(presentStr.trim()) < 0 || Integer.parseInt(presentStr.trim()) > 999999)
				{
					throw new NumberFormatException();
				}
				else
				{
					vctInp.add(presentStr.trim());
				}
				
				if (countOfFreqMap.containsKey(presentStr.trim()))
					{
						
					countOfFreqMap.put(presentStr.trim(),countOfFreqMap.get(presentStr.trim()) + 1);
					}
				else
				{
					countOfFreqMap.put(presentStr.trim(),1);
				}
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid Input : The vctInp should be integers between 0 to 999999");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	LinkedHashMap<String, Integer> mapGetter()
	{
		return countOfFreqMap;
	}
	


}
