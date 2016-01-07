import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Bas on 22-12-2015.
 */
public class AVLTree<K extends Comparable, V> implements Map<K, V>
{
    private AVLNode<K, V> root;

    @Override
    public int size()
    {
        if (root == null)
        {
            return 0;
        } else
        {
            return root.size();
        }
    }

    public void rebase()
    {
        if (root != null)
        {
            AVLNode<K, V> temp = root.getRoot();
            if (temp != null)
            {
                root = temp;
            }
        }
    }

    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key)
    {
        if (! isEmpty() && key != null)
        {
            try
            {
                return root.getKeyValue((K) key) != null;
            } catch (ClassCastException e)
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value)
    {
        //TODO
        return false;
    }

    @Override
    public V get(Object key)
    {
        if (! isEmpty() && key != null)
        {
            try
            {
                KeyValuePair<K, V> pair = root.getKeyValue((K) key);
                if (pair != null)
                {
                    return pair.getValue();
                }
            } catch (ClassCastException e)
            {
                return null;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value)
    {
        if (isEmpty())
        {
            root = new AVLNode<K, V>(new KeyValuePair<K, V>(key, value));
            return null;
        } else
        {
            V ret = root.insert(key, value);
            this.rebase();
            return ret;
        }
    }

    @Override
    public V remove(Object key)
    {
        throw new NotImplementedException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (Entry<? extends K, ? extends V> entry : m.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public void clear()
    {
        if (! isEmpty())
        {
            root.clearNode();
            root = null;
        }
    }

    @Override
    public Set<K> keySet()
    {
        Set<K> keySet = new HashSet<K>(size());
        if (root != null)
        {
            getKeys(root, keySet);
        }
        return keySet;
    }

    private void getKeys(AVLNode<K, V> target, Set<K> list)
    {
        list.add(target.getValue().getKey());
        if (target.leftValue != null)
        {
            getKeys(target.leftValue, list);
        }
        if (target.rightValue != null)
        {
            getKeys(target.rightValue, list);
        }
    }

    @Override
    public Collection<V> values()
    {
        Collection<V> valueSet = new HashSet<V>(size());
        if (root != null)
        {
            getValues(root, valueSet);
        }
        return valueSet;
    }

    private void getValues(AVLNode<K, V> target, Collection<V> list)
    {
        list.add(target.getValue().getValue());
        if (target.leftValue != null)
        {
            getValues(target.leftValue, list);
        }
        if (target.rightValue != null)
        {
            getValues(target.rightValue, list);
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K, V>> keySet = new HashSet<Entry<K, V>>(size());
        if (root != null)
        {
            getPairs(root, keySet);
        }
        return keySet;
    }

    private void getPairs(AVLNode<K, V> target, Set<Entry<K, V>> list)
    {
        list.add(target.getValue());
        if (target.leftValue != null)
        {
            getPairs(target.leftValue, list);
        }
        if (target.rightValue != null)
        {
            getPairs(target.rightValue, list);
        }
    }

    @Override
    public String toString()
    {
        if (isEmpty())
        {
            return " - ";
        } else
        {
            return xmlFormat(root, 0);
        }
    }

    private String xmlFormat(AVLNode<K, V> node, int level)
    {
        KeyValuePair<K, V> pair = node.getValue();
        StringBuffer retVal = new StringBuffer();
        for (int i = 0; i < level; i++)
        {
            retVal.append("   ");
        }
        retVal.append("- ");
        retVal.append(pair.getKey());
        retVal.append(" : \"");
        retVal.append(pair.getValue());
        retVal.append("\"\n");
        if (node.leftValue != null)
        {
            retVal.append(xmlFormat(node.leftValue, level + 1));
        }
        if (node.rightValue != null)
        {
            retVal.append(xmlFormat(node.rightValue, level + 1));
        }
        return retVal.toString();
    }
}
