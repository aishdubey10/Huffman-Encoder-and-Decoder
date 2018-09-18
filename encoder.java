import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

public class encoder {

	Vector<String> nd_key = new Vector<String>();
	Vector<String> valueOfEn = new Vector<String>();
	StringBuilder codetable = new StringBuilder();
	LinkedHashMap<String,String> mapEn = new LinkedHashMap<String,String>();

	void nd_traverse(NodeOfTree nd_root,StringBuilder s) throws IOException
	{


		if (nd_root!=null)
		{
			if(nd_root instanceof TerminalNodeOfTree)
			{

				mapEn.put(((TerminalNodeOfTree) nd_root).nd_key, s.toString());
				codetable.append((((TerminalNodeOfTree)nd_root).nd_key + " "+ s+"\n").toString());

			}


			if(nd_root.nd_left!=null)
			{
				nd_traverse(nd_root.nd_left,s.append("0"));
				s.setLength(s.length()-1);
			}

			if(nd_root.nd_right!=null)
			{
				nd_traverse(nd_root.nd_right,s.append("1"));
				s.setLength(s.length()-1);
			}
		}

	}

	

	public static void main(String[] and_rights)
	{
		BufferedReader objBr = null;
		FileReader frobj = null;
		Vector<NodeOfTree> vctF = new Vector<NodeOfTree>();
		long alltime = 0;
	    long begintime = 0;
		long lasttime = 0;
		String NameOfFile = and_rights[0];
		try {


				objBr = new BufferedReader(new FileReader(NameOfFile));

				TableFreqBuild countOfFreq_obj = new TableFreqBuild(objBr);
				LinkedHashMap<String,Integer> mapTemp = countOfFreq_obj.mapGetter();

				vctF.add(new TerminalNodeOfTree("",0));
				vctF.add(new TerminalNodeOfTree("",0));
				vctF.add(new TerminalNodeOfTree("",0));
				
				for (Map.Entry<String, Integer> putItem : mapTemp.entrySet()) {
					vctF.add(new TerminalNodeOfTree(putItem.getKey(),putItem.getValue()));
				}


				cache4way heap = new cache4way(vctF);

				InternalNodeOfTree it = new InternalNodeOfTree();

				while (heap.sizeOfHeap() >= 5)
				{
					NodeOfTree t1 = heap.minFinding();
					NodeOfTree ndobj2 = heap.minFinding();
					it = new InternalNodeOfTree(t1,ndobj2);
					heap.addingInto(it);

				}

				File outputF = new File("code_table.txt");
				FileOutputStream outStream = new FileOutputStream(outputF);
				OutputStreamWriter outWriter = new OutputStreamWriter(outStream);



				encoder jst = new encoder();
				jst.nd_traverse(it.nd_root, new StringBuilder());
				
				lasttime = System.currentTimeMillis();
				
				alltime = (long) (alltime + ((lasttime - begintime)));
				
				outWriter.write(jst.codetable.toString());
				outWriter.close();

				StringBuilder strEnco = new StringBuilder();

				for(String nd_key : countOfFreq_obj.vctInp)	
				{

					strEnco.append(jst.mapEn.get(nd_key));

				}

				try
				{

					File fout1 = new File("encoded.bin");
					FileOutputStream outStream1 = new FileOutputStream(fout1);


					//Converting the string to bits

					if (strEnco.length()>=8)
					{
						int pos =0;
						int count = 0;
						while(pos < (strEnco.length()-7))
						{
							count++;
							byte nextByte = 0x00;
							for(int i=0;i<8 && pos+i < strEnco.length();i++)
							{
								nextByte = (byte) (nextByte <<1);
								nextByte += strEnco.charAt(pos+i) == '0'?0x0:0x1;
							}

							outStream1.write(nextByte);
							pos+=8;

						}

						strEnco.delete(0, 8*count);
					}


					outStream1.close();

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			
		
		} catch (NumberFormatException e) {

			System.out.println(e.getMessage());
		}

		
		catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (objBr != null)
						objBr.close();

					if (frobj != null)
						frobj.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		}


	}
