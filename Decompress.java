/* Summary: This program takes in a compressed binary coded file, and outputs
 * the original text file. After decompressing, the program asks the user if
 * they would like to decompress another file. It also generates a log file
 * with information from the process.
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
              		String[] dict = new String[811];
 			   
          			//loop through and add common ASCII chars
       				int count=0;
              		for(int i=32; i<127; i++)
              		{
                  		dict[count] = Character.toString((char)i);
						count++;
              		}
  
  					//add special characters
			  		dict[count++] = "\n";
			  		dict[count++] = "\r";
			  		dict[count++] = "\t";

			  		//loop through the file to decompress
			  		//output the text that corresponds with the first code
			  		int q = binaryFile.readInt();
			  		output.print(dict[q]);
	
			        //read through binary file and apply decompression algorithm
					try
					{	
						int p = binaryFile.readInt();
						while(p != -1)
						{
							//double the list if neccessary
							if(count>=dict.length)
							{	dict = doubleList(dict);  
								doubled++;
							}
							//if p is in dict
							if(dict[p] != null)
							{
								//output p 
								//insert next code + first char of q into dict 
								String pstring = dict[p];
								output.print(pstring);
								dict[count] = dict[q] + pstring.substring(0,1);
								count++;
							}
							//if p is not in dict
							else
							{
								//output q + first char of q
								//insert q + first char of q into the dict
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
  					double finalTimeSec = finalTime/1000.0;
			        
					//write to log file
              		logFile.println("Decompression took " + finalTimeSec + " seconds");
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
            System.out.println("Do you want to decompress another file? (y or n)");
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
	/*This method will create a new list from a given list with double the
	 * size. The original values will be copied over to the beginning of the
	 * new list.
	 *
	 * @param list, the list to be doubled
	 * @returns the new list
	 */
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
