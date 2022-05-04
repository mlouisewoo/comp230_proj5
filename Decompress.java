<<<<<<< HEAD
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


=======
/* Summary:
  2  * TODO
  3  * Authors: Cassidy Spencer and Madeleine Woo
  4  * Date: 4/23/2022
  5  */
  6 import java.util.*;
  7 import java.io.*;
  8 
  9 public class Decompress
 10 {
 11     public static void main(String[] args)
 12     {
 13         //Declare variables
 14         Boolean rerun = false;
 15         Scanner sc = new Scanner(System.in);
 16         long startTime, finalTime, doubled;
 17         String filename = args[0];
 18 
 19         do
 20         {
 21             Boolean badinput = false;
 22             do
 23             {
 24                 try
 25                 {
 26                     //open connection to file for reading
 27                     ObjectInputStream binaryFile = new ObjectInputStream(new FileInputStream(filename));
 28 
 29                     //create decompressed file and log file
 30                     String newFile = filename.substring(0,filename.length() - 4);
 31                     PrintWriter output = new PrintWriter(new FileWriter(newFile));
 32                     PrintWriter logFile = new PrintWriter(new FileWriter(newFile + ".log"));
 33 
 34                     badinput = false;
 35                 }
 36                 catch(IOException e)
 37                 {
 38                     badinput = true;
 39                     System.out.println(e.getMessage());
 40                     System.out.println("Enter proper filename");
 41                     filename = sc.nextLine();
 42                 }
 43             }
 44             while(badinput);
 45 
 46             //start timer
 47             startTime = System.currentTimeMillis();
 48 
 49             //TODO decompress file
 50             //list for dict
 51             ArrayList<Entry<String,Integer>> dict = new ArrayList<Entry<String,Integer>>;
 52 
 53             //loop through and add common ASCII chars
 54             int count=0;
 55             for(i=32; i<127; i++)
 56             {
 57                 Entry<String,Integer> e = new Entry<String,Integer>(Character.toString(i),count);
 58                 dict.add(e);
 59                 count++;
 60             }
 61 
 62             //loop through the file to compress
 63             //for all other codes p in the compressed file:
 64             //  assume q is the code
 65             //  procedes p
 66             //  for:
 67             //      if p in dict
 68             //          extract text(p) from dict
 69             //          output text(p)
 70             //          insert (next code, text(p)FC(text(p)) ) into dict
 71             //      else
 72             //          output text(q)FC(text(q))) into the dict
 73             logFile.println("Decompression for file " + filename);
 74 
 75             //end timerv
 76             finalTime = System.currentTimeMillis() - startTime;
 77 
 78             //write to log file
 79             logFile.println("Decompression took " + finalTime + " milliseconds");
 80             //logFile.println("The table was doubled " + *** + " times");
 81 
 82             //ask user for rerun
 83             System.out.println("Do you want to compress another file? (y or n)");
 84             input = sc.nextLine();
 85 
 86             //validation    
 87             while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"))
 88                 {
 89                     System.out.println("INCORRECT!!! Please put y or n.");
 90                     input = sc.nextLine();
 91                 }
 92 
 93             //check for rerun
 94             if(input.equalsIgnoreCase("y"))
 95             {
 96                 rerun = true;
 97                 System.out.println("\nEnter the new file name: ");
 98                 filename = sc.nextLine();
 99             }
100             else
101                 rerun = false;
102         }
103         while(rerun);
104     }
105 }
106 
>>>>>>> b80345c4f5736726798771dc11ffc66b4bce4e3c
