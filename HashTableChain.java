/* Description: HashTableChain implements a HashMap to create a list of linked
 * lists. 
 * 
 * Authors: Cassidy Spencer and Madeleine Woo
 * 
 * Date: 5/10/2022
 */

import java.util.*;

public class HashTableChain <K,V> implements KWHashMap<K,V>
{	
	public static class Entry<K, V>
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
      	
		/* Override the toString method to display
		 * the key and value
		 * @return a string
		 */
		public String toString()
        {
          return key.toString() + " = " + value.toString();
        }
	}	
	  //************************************************************************
	    private LinkedList<Entry<K,V>>[] table;
	    private int numKeys;
	    private static final int CAPACITY = 811;
	    private static final double LOAD_THRESHOLD = .75;
		private int timesRehashed;
	
	//Constructor methods
	public HashTableChain()
	{
		table = new LinkedList[CAPACITY];
		numKeys = 0;
	}
	
	public HashTableChain(int cap)
	{
		table = new LinkedList[cap];
	        numKeys = 0;
	}

	/* get value for a specific key
	 * @param key is a key in the HashTableChain
	 * @return the value associated with the key or 
	 * null if the key is not found
	 */
	public V get(Object key)
	{
		int index = key.hashCode() % table.length;
	    //table wraps around to the beginning
		//if the index is negative
		if(index < 0)
			index += table.length;
	    if(table[index] == null)
			return null;

	    for(Entry<K,V> nextItem:table[index])
	    {
			if(nextItem.getKey().equals(key))
			return nextItem.getValue();
	    }

		return null;
	}

	/* Determines if the hashtable doesn't have keys
	 * @return true if table contains no key-value mappings*/
	public boolean isEmpty()
	{
		if(numKeys == 0)
			return true;
		else				
			return false;
	}

	/* Method put for class HashtableChain
	 * key-value pair is inserted in the table
	 * and numKeys is incremented. If the key is 
	 * already in the table, its value is changed to the
	 * new  val and numKeys is unchanged.
	 *
	 * @param key The key of item being inserted
	 * @param value The value of the key
	 * @return The old value associated with existing key;
	 * otherwise, null
	 */
	public V put(K key, V value) 
	{
		int index = key.hashCode() % table.length;
		if(index < 0)
			index += table.length;
		if(table[index] == null)
		{
			//create new linked list at table[index]
			table[index] = new LinkedList<Entry<K,V>>();
		}

		//search the list at table[index] to find the key
		for (Entry<K, V> nextItem : table[index])
		{
			//if the search is successful, replace the old value
			if (nextItem.getKey().equals(key))
			{
			//replace value with this key
				V oldVal = nextItem.getValue();
				nextItem.setValue(value);
				return oldVal;
			}
		}

		//if key is not in the table, addd new item
		table[index].addFirst(new Entry<>(key, value));
		numKeys++;
		if (numKeys > (LOAD_THRESHOLD * table.length))
			rehash();
			return null;
		}
	/* Retrieves the size of the hashtable
	 * @return the number of keys in the HashTableChain
	 */	
	public int size()
	{
		return numKeys;
	}

	/* Allocates a new hash table with double the capacity,
	 * reinserts each old table entry that has not been deleted
	 * into the hashtable, and references the new table instead
	 * of the original
	 */
	public void rehash()
	{
		//save temp table 
		LinkedList<Entry<K,V>>[] oldTable = table;
		table = new LinkedList[2 * oldTable.length + 1];

		numKeys = 0;

		//Reinsert items from oldTable
		for(int i=0; i<oldTable.length; i++){
			if(oldTable[i] != null){
				for(Entry<K,V> nextItem : oldTable[i]){
					put(nextItem.getKey(), nextItem.getValue());
				}
			}
		}
		timesRehashed++;

	}
	
	/* Gets the number of times the program
	 * rehashed the table
	 * @return timesRehashed
	 */
	public int getTimesRehashed()
	{	
		return timesRehashed;	
	}
	
	/* Removes a key-value pair from the 
	 * hashtable
	 * @param key the key of the object
	 * to remove
	 * @return the value of the object
	 * that has been removed
	 */
	public V remove(Object key)
	{
		int index = key.hashCode() % table.length;
		if(index < 0)
			index += table.length;

		//key is not in the table
		if(table[index] == null)
			return null;

		//search the list at table[index] to find the key
		for (Entry<K, V> nextItem : table[index])
		{
			//if we find the key, remove the entry
			if (nextItem.getKey().equals(key))
			{	
				V value = nextItem.getValue();
				table[index].remove(nextItem);
				numKeys--;
				if(table[index].size() == 0) //see if list is empty
					table[index] = null;
				return value; //return value associated with the key 
			}
		}
		return null;

	}
}

