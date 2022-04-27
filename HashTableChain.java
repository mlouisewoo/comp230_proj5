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
	  //************************************************************************
      private LinkedList<Entry<K,V>>[]table;
      private int numKeys;
      private static final int CAPACITY = TDA;
      private static final double LOAD_THRESHOLD = TDA;

      //should we put entry class in HashTableChain like he has it?

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
}

