/* Summary:
* TODO
* Authors: Cassidy Spencer and Madeleine Woo
* Date: 4/23/2022
*/
import java.util.*;
import java.io.*;

public class Decompress
{
	public static void main(String[] args)
	{
		//Declare variables
		Boolean rerun = false;
          	Scanner sc = new Scanner(System.in);
          	long startTime, finalTime;
          	String filename = args[0];
			String input = "";
			int doubled = 0;	
		do
		{
			Boolean badinput = false;
			do
			{
				try
                {
					//open connection to file for reading
					ObjectInputStream binaryFile = new ObjectInputStream(new FileInputStream(filename));
					 
                    //create decompressed file and log file
                    String newFile = filename.substring(0,filename.length() - 4);
                    PrintWriter output = new PrintWriter(new FileWriter(newFile));
                    PrintWriter logFile = new PrintWriter(new FileWriter(newFile + ".log"));
  
          			badinput = false;
                  
 
              		//start timer
             	 	startTime = System.currentTimeMillis();
  
              		//list for dict
              		String[] dict = new String[300];
 			   
          			//loop through and add common ASCII chars
       				int count=0;
              		for(int i=32; i<127; i++)
              		{
                  		dict[count] = Character.toString((char)i);
                  		System.out.print(dict[count] + " ");
						count++;
              		}
  
			  		dict[count++] = "\n";
			  		dict[count++] = "\r";
			  		dict[count++] = "\t";

			  		//loop through the file to decompress
			  		//output the text that corresponds with the first code
			  		//String q = dict[binaryFile.nextInt()];
			  		int q = binaryFile.readInt();
					System.out.println("\n" + q);
			  		output.print(dict[q]);
	
			        //read through binary file and apply decompression algorithm
					try
					{	
						int p = binaryFile.readInt();
						while(p != -1)
						{
							if(count>dict.length)
							{	dict = doubleList(dict);  
								doubled++;
							}
							//if p is in dict
							if(dict[p] != null)
							{
								  //String pstring = dict[p];
								  String pstring = dict[p];
								  output.print(pstring);
								  dict[count] = dict[q] + pstring.substring(0,1);
								  System.out.println("At 79: count = "+count+" q = "+q);
								  count++;
							}
							//if p is not in dict
							else
							{
								//p = q + q.substring(0,1);
								String qstring = dict[q];
								output.print(qstring + qstring.substring(0,1));
								dict[count] = qstring + qstring.substring(0,1);
								count++;
							}

							//reassign q to p
							q = p;
							p = binaryFile.readInt();
						}	  	 
					}//end try
					catch(EOFException e)
					{
						System.out.println("End of reading from file");
					}
					
			  		logFile.println("Decompression for file " + filename);
  
              		//end timer
              		finalTime = System.currentTimeMillis() - startTime;
  
			        //write to log file
              		logFile.println("Decompression took " + finalTime + " milliseconds");
              		logFile.println("The table was doubled " + doubled + " times");
  					
					//close file connections
					output.close();
					logFile.close();
					binaryFile.close();
					
				}//end try
				catch(FileNotFoundException e)
				{
					System.out.println("File not found. Please enter a valid filename");
					badinput = true;
					filename = sc.nextLine();
				}
                catch(IOException e)
				{
                    System.out.println(e.getMessage());
					System.exit(1);
                }
 			}
            while(badinput);
		  
            //ask user for rerun
            System.out.println("Do you want to compress another file? (y or n)");
            input = sc.nextLine();
  
			//validation    
			while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"))
		    {
				System.out.println("INCORRECT!!! Please put y or n.");
				input = sc.nextLine();
		    }
			
			//check for rerun
            if(input.equalsIgnoreCase("y"))
            {
				rerun = true;
				System.out.println("\nEnter the new file name: ");
                filename = sc.nextLine();
			}
            else
            	rerun = false;
		}
       	while(rerun);
	}
	public static String[] doubleList(String[] list)
	{
		//declare list with 2times capacity
		int size = list.length;
		String[] newList = new String[size*2];
		
		for(int i=0; i<size; i++)
		{
			newList[i] = list[i];
		}
		return newList;





	}
} 
