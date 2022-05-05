/*TODO Description:
 * Authors: Cassidy Spencer and Madeleine Woo
 * Date: 4/25/2022
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
      	
		public String toString()
        {
          return key.toString() + " = " + value.toString();
        }
	}	
	  //************************************************************************
	    private LinkedList<Entry<K,V>>[] table;
	    private int numKeys;
	    private static final int CAPACITY = 571;
	    private static final double LOAD_THRESHOLD = .75;

	//Consrtuctor methods
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

	//get value for a specific key
	public V get(Object key)
	{
	      int index = key.hashCode() % table.length;
	      if(index<0)
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

	/*return true if table contains no key-value mappings*/
	public boolean isEmpty()
	{
		if(numKeys == 0)
			return true;
		else				
			return false;
	}

	/* Method put for class HashtableChain
	 * key-value pair is insirted in the table
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
	
	public int size()
	{
		/*int count = 0;
		for(int i=0; i<table.length; i++){
			for (Entry<K,V> nextItem : table[i]){
					count++;
				}
			}

			return count;
		}*/
		return numKeys;
	}

	//rehash method
	public void rehash()
	{
		/* 1. allocate a new hash table with 2xcapacity
		 * 2. reinsert each old table entry that has not been deleted into the
		 * hash table
		 * 3. reference the new table instead of the original
		 */ 

		//save temp table 
		LinkedList<Entry<K,V>>[] oldTable = table;
		table = new LinkedList[2 * oldTable.length + 1];

		//Reinsert items from oldTable
		for(int i=0; i<oldTable.length; i++){
			if(oldTable[i] != null){
				for(Entry<K,V> nextItem : table[i]){
					put(nextItem.getKey(), nextItem.getValue());
				}
			}
		}

	}
	
	public V remove(Object key)
	{ return null; }	
	//remove method
	/*	public V remove(Object key)
		{	
			K key = (K)key;
			int index = key.hasCode() % table.length;
			if(index < 0)
				index += table.length;
			//key is not in the table
			if(table[index] == null)
				return null;
			
			Entry<K, V> current = table[index].head;
			
			if (current.getKey().equals(key)) 
			{
				table[index].head = current.next;
				if (table[index].size == 0)
				{
					//DELETED?
					table[index] = null;
				}	
				numKeys--;
			}
			else
			{
				while (current.hasNext())
				{
					if (current.next.getKey().equals(key))
					{
						current.next = current.next.next;
						numkeys--;
						return current.next.getValue();
					}
					current = current.next;
				}
			}

		}*/

		/*public V remove(K key)
		{
			int index = key.hasCode() % table.length;
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
					table[index].remove(nextItem);
					numKeys--;
					if(table[index].size == 0) //TODO trying to see if list is empty
						table[index] = null;
					return value; //TODO return value associated with the key 
				}
				return null;
			}

		}*/
}

