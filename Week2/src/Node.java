
/**
 * Created by Bas on 25-11-2015.
 */
public class Node<K extends Comparable, V> implements TreeElement<K, V>
{
    private final int nodeSize;
    protected KeyValuePair<K, V>[] nodes;
    protected TreeElement<K, V>[] children;

    public Node(int maxSize)
    {
        nodeSize = maxSize;
        nodes = new KeyValuePair[nodeSize];
        children = new TreeElement[nodeSize+1];
    }

    public TreeElement<K, V> getNextTreeElement(K key)
    {
        for (int i = 0; i < nodeSize; i++)
        {
            if(nodes[i]!=null)
            {
                int comparison = nodes[i].compareTo(key);
                if (comparison == 0)
                {
                    return this;
                } else if (comparison > 0)
                {
                    return children[i];
                } else if (comparison < 0 && i == (nodeSize - 1))
                {
                    return children[i + 1];
                }
            }
            else{
                break;
            }
        }
        return null;
    }

    public int getLocationForElement(K key)
    {
        for (int i = 0; i < nodeSize; i++)
        {
            if(nodes[i]!=null)
            {
                int comparison = nodes[i].compareTo(key);
                if (comparison > 0)
                {
                    return i;
                } else if (comparison < 0 && i == (nodeSize - 1))
                {
                    return i + 1;
                }
            }
            else return i;
        }
        return -1;
    }

    public void addAtLocation(TreeElement<K, V> element, int location)
    {
        children[location]=element;
    }

    @Override
    public boolean hasChildren()
    {
        return children[0] == null;
    }

    @Override
    public TreeElement<K, V>[] getChildren()
    {
        return children;
    }

    @Override
    public boolean isEmpty()
    {
        return nodes[0] == null;
    }

    @Override
    public boolean isFull()
    {
        for (int i = 0; i < nodeSize; i++)
        {
            if (nodes[i] == null)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size()
    {
        int size = 0;
        for (int i = 0; i <= nodeSize; i++)
        {
            if (i < nodeSize)
            {
                if (nodes[i] != null)
                {
                    size += nodes[i].size();
                }
            }
            if (children[i] != null)
            {
                size += children[i].size();
            }
        }
        return size;
    }

    public boolean hasKeyInNodes(K key)
    {
        for (int i = 0; i <= nodeSize; i++)
        {
            if (i < nodeSize)
            {
                if (nodes[i] != null && nodes[i].compareTo(key) == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(K key)
    {
        TreeElement<K, V> current = this;
        do
        {
            if (current.hasChildren())
            {
                Node<K, V> curNode = (Node<K, V>) current;
                if (curNode.hasKeyInNodes(key))
                {
                    return true;
                }
                current = curNode.getNextTreeElement(key);
            } else
            {
                if (current.containsKey(key))
                {
                    return true;
                } else
                {
                    current = null;
                }
            }


        } while (current != null);
        return false;
    }

    @Override
    public TreeElement<K, V> getHolder(K key)
    {
        Node<K, V> tree = this;
        while (! tree.hasKeyInNodes(key) && tree != null)
        {
            TreeElement<K, V> element = tree.getNextTreeElement(key);
            if (element instanceof Node)
            {
                tree = (Node<K, V>) element;
            } else if (element instanceof KeyValuePair)
            {
                KeyValuePair<K, V> pair = (KeyValuePair<K, V>) element;
                if (pair.containsKey(key))
                {
                    return pair;
                }
            }
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

    public V get(K key)
    {
        KeyValuePair<K, V> element = (KeyValuePair<K, V>) getHolder(key);
        if (element != null)
        {
            return element.getValue();
        } else
        {
            return null;
        }
    }

    public void clear()
    {
        for (int i = 0; i <= nodeSize; i++)
        {
            if (i < nodeSize)
            {
                nodes[i] = null;
            }
            if (children[i] != null && children[i] instanceof Node)
            {
                ((Node<K, V>) children[i]).clear();
            }
            children[i] = null;
        }
    }

    public void addInnerNode(KeyValuePair<K, V> pair)
    {
        if (this.isFull())
        {
            throw new IllegalAccessError("Cannot add to a full node");
        } else
        {
            for (int i = 0; i < nodeSize-1; i++)
            {
                if (nodes[i] != null)
                {
                    int comparison = nodes[i].compareTo(pair);
                    if (comparison == 0)
                    {
                        nodes[i] = pair;
                        return;
                    } else if (comparison > 0)
                    {
                        for (int j = nodeSize; j > i; j--)
                        {
                            if (j < nodeSize)
                            {
                                nodes[j] = nodes[j - 1];
                            }
                            children[j] = children[j - 1];
                        }
                        nodes[i] = pair;
                        return;
                    } else if (comparison < 0 && nodes[i+1]==null)
                    {
                        nodes[i+1] = pair;
                        return;
                    }
                }
                else{
                    nodes[i]=pair;
                    return;
                }
            }
        }
    }

}
