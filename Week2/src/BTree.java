import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Bas on 25-11-2015.
 */
public class BTree<K extends Comparable, V> //implements Map<K, V>
{
    private final int nodeSize;
    private List<KeyValuePair<K, V>> nodes;
    private List<BTree<K, V>> subtrees;

    public BTree(int size)
    {
        this.nodeSize = size;
        nodes = new ArrayList<KeyValuePair<K, V>>(nodeSize);
        subtrees = new ArrayList<BTree<K, V>>(nodeSize + 1);
    }

    protected boolean hasKeyInNodes(K key)
    {
        for (KeyValuePair<K, V> pair : nodes)
        {
            if (pair.compareTo(key) == 0)
            {
                return true;
            }
        }
        return false;
    }

    protected BTree<K, V> getNextTree(K key)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            int comparison = nodes.get(i).compareTo(key);
            if (comparison == 0)
            {
                System.out.println("I have it! " + key);
                return this;
            } else if (comparison > 0)
            {
                return subtrees.get(i);
            } else if (comparison < 0 && i == (nodeSize - 1))
            {
                return subtrees.get(i + 1);
            }
        }
        return null;
    }

    protected KeyValuePair<K, V> getPair(K key)
    {
        BTree<K, V> tree = this;
        while (! tree.hasKeyInNodes(key) && tree != null)
        {
            tree = tree.getNextTree(key);
        }
        if (tree != null)
        {
            for (KeyValuePair<K, V> pair : tree.nodes)
            {
                if (pair.compareTo(key) == 0)
                {
                    return pair;
                }
            }
        }
        return null;
    }


    public int size()
    {
        int size = 0;
        for (BTree<K, V> subtree : subtrees)
        {
            size += subtree.size();
        }
        return nodes.size() + size;
    }


    public boolean isEmpty()
    {
        return nodes.isEmpty();
    }


    public boolean containsKey(Object aKey)
    {
        try
        {
            K key = (K) aKey;
            BTree<K, V> tree = this;
            do
            {
                if (tree.hasKeyInNodes(key))
                {
                    return true;
                } else
                {
                    tree.getNextTree(key);
                }
            } while (tree != null);
            return false;
        } catch (ClassCastException e)
        {
            return false;
        }
    }

    public boolean containsValue(Object value)
    {
        throw new UnsupportedOperationException("Cannot Search for values");
    }

    public V get(Object key)
    {
        try
        {
            return getPair((K) key).getValue();
        } catch (ClassCastException e)
        {
            return null;
        }
    }


    public V put(K key, V value)
    {
        BTree<K, V> next = this;
        BTree<K, V> tree;
        do
        {
            tree = next;
            if (tree.hasKeyInNodes(key))
            {
                return tree.getPair(key).setValue(value);
            } else if (tree.nodes.size() < nodeSize)
            {
                if (tree.nodes.size() > 0)
                {
                    for (int i = 0; i < tree.nodes.size(); i++)
                    {
                        //Add to the nodes list at a location;
                        //Shift the subtrees
                        if (tree.nodes.get(i).compareTo(key) > 0)
                        {
                            tree.nodes.add(i, new KeyValuePair<K, V>(key, value));
                            tree.subtrees.add(i, new BTree<K, V>(nodeSize));
                            return null;
                        } else if (i == tree.nodes.size() - 1)
                        {
                            tree.nodes.add(new KeyValuePair<K, V>(key, value));
                            tree.subtrees.add(new BTree<K, V>(nodeSize));
                            return null;
                        }

                    }
                } else
                {
                    tree.nodes.add(new KeyValuePair<K, V>(key, value));
                    tree.subtrees.add(new BTree<K, V>(nodeSize));
                    tree.subtrees.add(new BTree<K, V>(nodeSize));
                    return null;
                }
            }
            next = tree.getNextTree(key);
        } while (next != null && ! tree.equals(next));

        return null;
    }


    public V remove(Object key)
    {
        //TODO
        return null;
    }


    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    public void clear()
    {
        nodes.clear();
        for (BTree<K, V> subtree : subtrees)
        {
            subtree.clear();
        }
        subtrees.clear();
    }



    public V getOrDefault(Object key, V defaultValue)
    {
        V value = this.get(key);
        if (value == null)
        {
            value = defaultValue;
        }
        return value;
    }



    public boolean replace(K key, V oldValue, V newValue)
    {
        KeyValuePair<K, V> pair = getPair(key);
        if (pair != null && pair.getValue().equals(oldValue))
        {
            pair.setValue(newValue);
            return true;
        } else
        {
            return false;
        }
    }


    public V replace(K key, V value)
    {
        KeyValuePair<K, V> pair = getPair(key);
        if (pair != null)
        {
            return pair.setValue(value);
        } else
        {
            return null;
        }
    }
}
