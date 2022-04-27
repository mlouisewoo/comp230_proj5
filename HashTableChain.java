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
	
	}

