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
				}
				catch(IOException e)
				{
					badinput = true;
					System.out.println(e.getMessage());
					System.out.println("Enter proper filename");
					filename = sc.nextLine();
				}	
			}
			while(badinput);
											
			//start timer
			startTime = System.currentTimeMillis(); 

			//TODO decompress file
			//list for dict
			ArrayList<Entry<String,Integer>> dict = new ArrayList<Entry<String,Integer>>;

			//loop through and add common ASCII chars
			int count=0;
			for(i=32; i<127; i++)
			{
				Entry<String,Integer> e = new Entry<String,Integer>(Character.toString(i),count);
				dict.add(e);
				count++;
			}

			//loop through the file to compress
			//for all other codes p in the compressed file:
			//	assume q is the code
			//	procedes p
			//	for:
			//		if p in dict
			//			extract text(p) from dict
			//			output text(p)
			//			insert (next code, text(p)FC(text(p)) ) into dict
			//		else
			//			output text(q)FC(text(q))) into the dict
			logFile.println("Decompression for file " + filename);
			
			//end timerv
			finalTime = System.currentTimeMillis() - startTime;

			//write to log file
			logFile.println("Decompression took " + finalTime + " milliseconds");
			//logFile.println("The table was doubled " + *** + " times");
	
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


