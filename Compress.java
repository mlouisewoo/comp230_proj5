/* Summary:
 * TODO
 * Authors: Cassidy Spencer and Madeleine Woo
 * Date: 4/23/2022
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

		do 
		{
			Boolean badinput = false;
			do
			{
				try
				{
					//open connection to file for reading
					BufferedReader readFile = new BufferedReader(new FileReader(filename));
					
					//create compressed file and log file
					PrintWriter compFile = new PrintWriter(new FileWriter(filename + ".zzz");
					PrintWriter logFile = new PrintWriter(new FileWriter(filename + ".zzz.log"));

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
				//if doesnt work use System.nanoTime()

			// get size of OG file
			size = filename.length();
			
			//TODO compress file
			logFile.println("Compression of " + filename);
			
			//end timerv
			finalTime = System.currentTimeMillis() - startTime;

			//get size of NEW file
			newSize = compFile.length();

			//write to log file
			logFile.println("Compressed from " + size + " to " + size);
			logFile.println("Compression took " + finalTime + " milliseconds");
			//TODO: logFile.println("The dictionary contains " + *** + "total
			//entries");
			//logFile.println("The table was rehashed " + *** + " times");
	
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


