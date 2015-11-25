import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by Bas on 25-11-2015.
 */
public class BTree<K extends Comparable, V> //implements Map<K, V>
{
    private final int nodeSize;
    private TreeElement root;

    public BTree(int size)
    {
        this.nodeSize = size;
    }

    protected KeyValuePair<K, V> getPair(K key)
    {
        if(root instanceof Node)
        {
            Node<K,V> node = (Node<K, V>) root;
            return (KeyValuePair<K, V>) node.getHolder(key);
        }
        else if(root instanceof KeyValuePair && root.containsKey(key))
        {
            return (KeyValuePair<K, V>) root;
        }
        else return null;
    }


    public int size()
    {
        if(root == null)
        {
            return 0;
        }else
        {
            return root.size();
        }
    }


    public boolean isEmpty()
    {
        return root ==null;
    }


    public boolean containsKey(Object aKey)
    {
        try
        {
            return root.containsKey((Comparable) aKey);
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
            if(root instanceof KeyValuePair && root.containsKey((K) key))
            {
                return ((KeyValuePair<K,V>)root).getValue();
            }
            else{
                return ((Node<K,V>) root).get((K) key);
            }
        } catch (ClassCastException e)
        {
            return null;
        }
    }


    public V put(K key, V value)
    {
        if(root==null)
        {
            root = new KeyValuePair<K, V>(key, value);
            return null;
        }
        else if(root instanceof KeyValuePair)
        {
            KeyValuePair<K,V> pair = (KeyValuePair<K, V>) root;
            if(pair.containsKey(key))
            {
                return pair.setValue(value);
            }
            else
            {
                root =  mergeKeys(pair, new KeyValuePair<K, V>(key, value));
                return null;
            }
        }
        else if(root instanceof Node){
            Node<K,V> node = (Node<K, V>) root;
            TreeElement element;
            do{
                element = node.getNextTreeElement(key);
                if(element ==null)
                {
                    node.addAddLocation(new KeyValuePair<K,V>(key, value),node.getLocationForElement(key));
                    return null;
                }
                else if(element instanceof KeyValuePair)
                {
                    KeyValuePair<K,V> keyvalue = (KeyValuePair<K, V>) element;
                    if(keyvalue.containsKey(key))
                    {
                        return keyvalue.setValue(value);
                    }
                    else
                    {
                        node.addAddLocation(mergeKeys(keyvalue, new KeyValuePair<K,V>(key, value)),node.getLocationForElement(key));
                        return null;
                    }
                }
                else if(element instanceof Node)
                {
                    node= (Node<K, V>) element;
                    if(node.hasKeyInNodes(key))
                    {
                       return ((KeyValuePair<K,V>)node.getHolder(key)).setValue(value);
                    }
                }

            }while(node!=null);
        }

        return null;
    }

    private Node<K,V> mergeKeys(KeyValuePair a, KeyValuePair b)
    {
        Node<K,V> node= new Node<K,V>(nodeSize);
        if(a.compareTo(b)>0)
        {
            node.addInnerNode(a);
            node.addInnerNode(b);
        }
        else
        {
            node.addInnerNode(b);
            node.addInnerNode(a);
        }
        return node;
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
        if(root instanceof Node)
        {
            ((Node) root).clear();
        }
        root = null;
    }


    public Set<K> keySet()
    {
        //TODO
        return null;
    }


    public Collection<V> values()
    {
        //TODO
        return null;
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


    public void forEach(BiConsumer<? super K, ? super V> action)
    {

    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
    {

    }


    public V putIfAbsent(K key, V value)
    {
        return null;
    }


    public boolean remove(Object key, Object value)
    {
        //TODO
        return false;
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
