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
        if (root instanceof Node)
        {
            Node<K, V> node = (Node<K, V>) root;
            return (KeyValuePair<K, V>) node.getHolder(key);
        } else if (root instanceof KeyValuePair && root.containsKey(key))
        {
            return (KeyValuePair<K, V>) root;
        } else
        {
            return null;
        }
    }


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


    public boolean isEmpty()
    {
        return root == null;
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
            if (root instanceof KeyValuePair && root.containsKey((K) key))
            {
                return ((KeyValuePair<K, V>) root).getValue();
            } else
            {
                return ((Node<K, V>) root).get((K) key);
            }
        } catch (ClassCastException e)
        {
            return null;
        }
    }


    public V put(K key, V value)
    {
        if (root == null)
        {
            root = new KeyValuePair<K, V>(key, value);
            return null;
        } else if (root instanceof KeyValuePair)
        {
            KeyValuePair<K, V> pair = (KeyValuePair<K, V>) root;
            if (pair.containsKey(key))
            {
                return pair.setValue(value);
            } else
            {
                root = mergeKeys(pair, new KeyValuePair<K, V>(key, value));
                return null;
            }
        } else if (root instanceof Node)
        {
            Node<K, V> node = (Node<K, V>) root;
            TreeElement element;
            do
            {
                element = node.getNextTreeElement(key);
                if (element == null)
                {
                    nodeMerge((Node<K, V>) root, node, new KeyValuePair<K, V>(key, value));
                    return null;
                } else if (element instanceof KeyValuePair)
                {
                    KeyValuePair<K, V> keyvalue = (KeyValuePair<K, V>) element;
                    if (keyvalue.containsKey(key))
                    {
                        return keyvalue.setValue(value);
                    } else
                    {
                        node.addAtLocation(mergeKeys(keyvalue, new KeyValuePair<K, V>(key, value)), node.getLocationForElement(key));
                        return null;
                    }
                } else if (element instanceof Node)
                {
                    node = (Node<K, V>) element;
                    if (node.hasKeyInNodes(key))
                    {
                        return ((KeyValuePair<K, V>) node.getHolder(key)).setValue(value);
                    }
                }

            } while (node != null);
        }

        return null;
    }

    private void nodeMerge(Node<K, V> oldRoot, Node<K, V> node, KeyValuePair<K, V> newPair)
    {
        //Node isnt full, fill it further
        if (! node.isFull())
        {
            node.addInnerNode(newPair);
        } else
        {
            //node is full, start splitting it!
            List<Node<K, V>> path = new ArrayList<Node<K, V>>();
            path.add(oldRoot);
            do
            {
                System.out.println("Adding " + node);
                Node<K,V> aPart = (Node<K, V>) path.get(path.size() - 1).getNextTreeElement(newPair.getKey());
                if(aPart!=null)
                {
                    path.add(aPart);
                }
            }while (!path.contains(node));
            path.remove(node);
            KeyValuePair<K, V>[] oldNodes = node.nodes;
            int number = (oldNodes.length - 1) / 2;
            int location;
            Node<K, V> newNode;
            do
            {
                System.out.println("Size = " + path.size());
                if (path.size() == 0)
                {
                    newNode = new Node<K, V>(nodeSize);
                    if (oldNodes.length % 2 == 0)
                    {//evens
                        mergeChildren(newNode, 0, node, 0, number-1);
                        newNode.addInnerNode(oldNodes[number]);
                        mergeChildren(newNode, 1, node, number, number);
                        newNode.addInnerNode(oldNodes[number + 1]);
                        mergeChildren(newNode, 2, node, number+1, oldNodes.length);

                    } else
                    {//Uneven
                        mergeChildren(newNode, 0, node, 0, number-1);
                        newNode.addInnerNode(oldNodes[number + 1]);
                        mergeChildren(newNode, 1, node, number, oldNodes.length);
                    }
                    root = newNode;
                    path.remove(root);

                } else
                {
                    newNode = path.get(path.size()-1);
                    location = newNode.getLocationForElement(oldNodes[number].getKey());
                    System.out.println(location);

                    path.remove(newNode);
                }
            }while(path.size()>0);


        }
    }

    private void mergeChildren(Node<K, V> node, int newLocation, Node<K, V> oldNode, int start, int stop)
    {
        Node<K,V> newestNode = new Node<K,V>(nodeSize);
        for(int i = start; i<=stop;i++)
        {
            if(i<oldNode.nodes.length)
            {
                newestNode.addInnerNode(oldNode.nodes[i]);
            }
            newestNode.children[i]=oldNode.children[i];
        }
        node.children[newLocation]=newestNode;
    }


    private Node<K, V> mergeKeys(KeyValuePair a, KeyValuePair b)
    {
        Node<K, V> node = new Node<K, V>(nodeSize);
        if (a.compareTo(b) > 0)
        {
            node.addInnerNode(a);
            node.addInnerNode(b);
        } else
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
        if (root instanceof Node)
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
