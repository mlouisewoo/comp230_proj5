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
		long startTime, finalTime;

		do 
		{
			Boolean badinput = false;
			do
			{
				try
				{
					String filename = args[0];
	
					//call log file method
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
			
			//TODO start timer
			//TODO get size of OG file
			//TODO compress file
			//TODO end timer
			//TODO get size of NEW file

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
				rerun = true;
			else
				rerun = false;
		}
		while(rerun);		
	}
}

private static class Entry<K, V>
{
	//the key
	private final K key;
    //the value
	private V value;
			 
    /*Create a new key-value pair.
	 * @param key The key
     * @param value The value
	 */
	public Entry(K key, V value)
	{
		this.key = key;
        this.value = value;
    }
						   
    /* Return the key
	 * @return The key
	 */
    public K getKey()
    {
		return key;
	}
									   
	/* Return the value
	 * @return The value
     */
    public V getValue()
	{
		return value;
	}
												   
	/* Set the value
	 * @param val New value
     * @return Old value
     */
	public V setValue(V newVal)
	{
		V oldVal = value;
		value = newVal;
		return oldVal;
	}
}
