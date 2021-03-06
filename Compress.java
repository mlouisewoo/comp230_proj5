/* Summary: This program compresses a text file and writes the compressed
 * version to a new binary file.
 * 
 * Authors: Cassidy Spencer and Madeleine Woo
 * 
 * Date: 5/10/2022
 */
import java.util.*;
import java.io.*;

public class Compress
{
	public static void main(String[] args)
	{
		//Declare variables
		Boolean rerun = false;
		Scanner sc = new Scanner(System.in);
		int rehashed = 0;
		long startTime, finalTime, size, newSize;
		String filename = args[0];
		BufferedReader readFile;
		ObjectOutputStream compFile;
		PrintWriter logFile;
		String input;

		do 
		{
			Boolean badinput = false;
			do
			{
				try
				{
					//open connection to file for reading
					readFile = new BufferedReader(new FileReader(filename));
					
					//create compressed file and log file
					compFile = new ObjectOutputStream(new FileOutputStream(filename + ".zzz"));
					compFile.reset();
					logFile = new PrintWriter(new FileWriter(filename + ".zzz.log"));

					badinput = false;
											
					//start timer
					startTime = System.currentTimeMillis(); 

					// get size of OG file
					File file = new File(filename);
					size = file.length();

					//hash table object
					HashTableChain<String, Integer> dict = new HashTableChain<String, Integer>();

					//loop through and add common ASCII chars
					int count=0;
					for(int i=32; i<127; i++)
					{
						dict.put(Character.toString((char)i), count);
						count++;
					}

					//add /n, /r, and /t characters
					dict.put("\n", count++);
					dict.put("\r", count++);
					dict.put("\t", count++);

					//loop through the file to compress
					String[] prefix = new String[1];
					prefix[0] = "";
					int character = readFile.read();
					int value = 0;
					prefix[0] += Character.toString((char)character);
					while(character != -1)
					{
						//if prefix is in dict
						if(dict.get(prefix[0]) != null) 
						{
							value = dict.get(prefix[0]);
							character = readFile.read();
							prefix[0] += Character.toString((char)character);
						}
						//if prefix is not in the dictionary
						else if (dict.get(prefix[0]) == null)
						{
							dict.put(prefix[0], count);
							count++;
							//add the next smallest prefix value to compress file
							compFile.writeInt(value);	
							String temp = prefix[0];
							//add the last letter of the old prefix to the
							//beginning of the new prefix
							prefix[0] = temp.charAt(temp.length()-1) + "";
						}
					}

					logFile.println("Compression of " + filename);

					//end timer
					finalTime = System.currentTimeMillis() - startTime;
					double finalTimeSeconds = finalTime/1000.0;

					//get size of NEW file
					File newFile = new File(filename + ".zzz");
					newSize = newFile.length();

					//write to log file
					logFile.println("Compressed from " + size + " to " + newSize);
					logFile.println("Compression took " + finalTimeSeconds + " seconds");
					logFile.println("The dictionary contains " + dict.size() + " total entries");
					logFile.println("The table was rehashed " + dict.getTimesRehashed() + " times");

					//close file connections
					readFile.close();
					compFile.close();
					logFile.close();

  				}//end try
  				catch(FileNotFoundException e)
				{
					System.out.println("Cannot find file. Please enter a proper filename.");
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


