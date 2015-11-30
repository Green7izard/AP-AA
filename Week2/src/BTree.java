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
        return root.get(key).getValue();
    }


    public V put(K key, V value)
    {
        V result = root.put(new KeyValuePair<K,V>(key, value));
        rebase();
        return result;
    }

    private void rebase()
    {
        Node<K,V> current = root;
        while(root.getParent()!=null)
        {
            current=root.getParent();
        }
        root=current;
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
