import java.security.KeyStore;

/**
 * Created by Bas on 25-11-2015.
 */
public class KeyValuePair<K extends Comparable, V> implements TreeElement<K, V>, KeyStore.Entry, Comparable<Comparable>
{
    //The key, should never be replaced
    private final K key;
    //The value, can be replaced
    private V value;

    /**
     * Create a new KeyValuePair
     *
     * @param key   the desired Key
     * @param value the desired value
     */
    public KeyValuePair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Comparable o)
    {
        if (o.getClass().isAssignableFrom(key.getClass()))
        {
            return key.compareTo(o);
        } else if (o.getClass().isAssignableFrom(KeyValuePair.class))
        {
            return key.compareTo(((KeyValuePair) o).getKey());
        } else
        {
            throw new IllegalArgumentException("Invalid Type of: " + o);
        }
    }

    /**
     * @return The key
     */
    public K getKey()
    {
        return key;
    }

    /**
     * @return the value
     */
    public V getValue()
    {
        return value;
    }

    /**
     * Set a new value
     *
     * @param newValue the new value
     * @return the old value
     */
    public V setValue(V newValue)
    {
        V old = value;
        value = newValue;
        return old;
    }

    @Override
    public boolean hasChildren()
    {
        return false;
    }

    @Override
    public TreeElement<K, V>[] getChildren()
    {
        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return value == null;
    }

    @Override
    public boolean isFull()
    {
        return !isEmpty();
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public boolean containsKey(K key)
    {
        try
        {
            return this.compareTo(key) == 0;
        } catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public TreeElement<K, V> getHolder(K key)
    {
        if (this.containsKey(key))
        {
            return this;
        } else
        {
            return null;
        }
    }
}
