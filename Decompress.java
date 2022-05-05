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
          	long startTime, finalTime, doubled;
          	String filename = args[0];
		
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
              				ArrayList<String> dict = new ArrayList<String>();
 			   
              				//loop through and add common ASCII chars
              				int count=0;
              				for(i=32; i<127; i++)
              				{
                  				Entry<String,Integer> e = new Entry<String,Integer>(Character.toString(i),count);
                  				dict.add(e);
                  				count++;
              				}
  
			  		dict.add("/n", count++);
			  		dict.add("/r", count++);
			  		dict.add("/t", count++);

			  		//loop through the file to decompress
			  		//output the text that corresponds with the first code
			  		//String q = dict[binaryFile.nextInt()];
			  		int q = binaryFile.readInt();
			  		newFile.print(q);
	
			                //read through binary file and apply decompression algorithm
					try
					{	
						while(int p = binaryFile.readInt() != -1)
						{  
							//if p is in dict
							if(dict.length >  p)
							{
								  String pstring = dict[p];
								  newFile.print(pstring);
								  dict.add(dict[q] + pstring.substring(0,1));
							}
							//if p is not in dict
							else
							{
								//p = q + q.substring(0,1);
								String qstring = dict[q];
								newFile.print(qstring + qstring.substring(0,1));
								dict.add(qstring + qstring.substring(0,1));
							}

							//reassign q to p
							q = p;
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
              				//TODO logFile.println("The table was doubled " + *** + " times");
  					
					//TODO close file connections
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
} 
