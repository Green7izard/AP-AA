import java.util.*;

/**
 * Created by Bas on 25-11-2015.
 */
public class BTree<K extends Comparable, V> //implements Map<K, V>
{
    private final int nodeSize;

    private Node<K,V> root;


    public BTree(int size)
    {
        this.nodeSize = size;
        root = new Node<K,V>(nodeSize);
    }


    public int size()
    {
        return root.size();
    }

    public int depth()
    {
        return root.depth();
    }

    public boolean containsKey(K key)
    {
        return root.get(key)!=null;
    }

    public V get(K key)
    {
        KeyValuePair<K,V> pair = root.get(key);
        if(pair==null)
        {
            return null;
        }
        else
        {
            return pair.getValue();
        }
    }


    public void put(K key, V value)
    {
        root = root.put(new KeyValuePair<K,V>(key, value));
        rebase();
    }

    private void rebase()
    {
        root=root.getRoot();
    }


    public V remove(K key)
    {
        V result = root.remove(key);
        rebase();
        return result;
    }


    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    public Node<K,V> getRoot()
    {
            return root;
    }

}
