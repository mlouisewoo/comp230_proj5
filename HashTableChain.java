/*TODO Description:
 * Authors: Cassidy Spencer and Madeleine Woo
 * Date: 4/25/2022
 */
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
    private LinkedList<Entry<K,V>>[]table;
    private int numKeys;
    private static final int CAPACITY = TDA;
    private static final double LOAD_THRESHOLD = TDA;

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
      
	  return null
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
	public V put(K key, V Value) 
	{
		int index = key.hashCode % table.length;
		if(index < 0)
			index += table.length;
		if(table[index] == null)
		{
			//create new linked list at table[index]
			table[index] = newLinkedList<>();
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
			rehash(); //TODO Rehash method? UGGH
		return null;
	}

	public V remove(K key)
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
}

