import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bas on 26-11-2015.
 */
public class Node<K extends Comparable, V>
{

    private final int nodeSize;
    private Node parent;
    private List<Link<K, V>> links;

    public Node(int nodeSize)
    {
        this.nodeSize = nodeSize;
        links = new ArrayList<Link<K, V>>(this.nodeSize);
        parent = null;
    }

    public Node getParent()
    {
        return parent;
    }

    public List<Link<K, V>> getLinks()
    {
        return links;
    }

    public KeyValuePair<K, V> get(K key)
    {
        if (key != null)
        {
            Node<K, V> next = this;
            do
            {
                //Check if i have it
                for (Link link : links)
                {
                    //Its the left Value
                    if (link.leftValue != null && link.leftValue.compareTo(key) == 0)
                    {
                        return link.leftValue;
                    }
                    // Its the Right Value
                    else if (link.rightValue != null && link.rightValue.compareTo(key) == 0)
                    {
                        return link.rightValue;
                    }
                }
                //Otherwise get the next
                next = getNextNode(key);
            } while (next != null);
        }
        return null;
    }

    public Node<K, V> getNextNode(K key)
    {
        if (key != null)
        {
            for (Link<K, V> link : links)
            {
                //If left or right is free (ends)
                if ((link.leftValue == null && link.rightValue.compareTo(key) > 0) || (link.rightValue == null && link.leftValue.compareTo(key) < 0))
                {
                    return link.subnode;
                }
                //If it is between two values
                else if (link.leftValue != null && link.rightValue != null && link.rightValue.compareTo(key) > 0 && link.leftValue.compareTo(key) < 0)
                {
                    return link.subnode;

                }
                // If its equals to
                else if (link.leftValue != null && link.rightValue != null && (link.rightValue.compareTo(key) == 0 || link.leftValue.compareTo(key) == 0))
                {
                    return this;
                }
            }
        }
        return null;
    }

    /**
     * The largest depth of the tree
     *
     * @return 1 if no children, other wise 1 + the largest depth
     */
    public int depth()
    {
        int largest = 0;
        for (Link link : links)
        {
            if (link.subnode != null)
            {
                int current = link.subnode.depth();
                if (current > largest)
                {
                    largest = current;
                }
            }
        }
        return largest + 1;
    }

    /**
     * @return The amount of Nodes in the tree
     */
    public int size()
    {
        int total = 0;
        for (Link link : links)
        {
            if (link.subnode != null)
            {
                total += link.subnode.size();
            }
        }
        return total + 1;
    }

    /**
     * @return The amount of KeyValuePairs in the tree
     */
    public int amountOfPairs()
    {
        int total = 0;
        for (Link link : links)
        {
            if (link.subnode != null)
            {
                total += link.subnode.amountOfPairs();
            }
        }
        if (links.size() == 0)
        {
            return total;
        } else
        {
            return total + links.size() - 1;
        }
    }

    public boolean isFull()
    {
        return links.size() - 1 == nodeSize;
    }

    public V put(KeyValuePair<K, V> newPair)
    {
        if (newPair != null && newPair.getKey() != null)
        {
            Node<K, V> target = getLastNode(newPair.getKey());
            //Check if i have it, then it should replace
            for (Link<K, V> link : links)
            {
                //Its the left Value
                if (link.leftValue != null && link.leftValue.compareTo(newPair.getKey()) == 0)
                {
                    return link.leftValue.setValue(newPair.getValue());
                }
                // Its the Right Value
                else if (link.rightValue != null && link.rightValue.compareTo(newPair.getKey()) == 0)
                {
                    return link.rightValue.setValue(newPair.getValue());
                }
            }
            target.forcePut(newPair);
        }
        return null;
    }

    private void forcePut(KeyValuePair<K, V> newPair)
    {
        if (isFull())
        {
            splitNode(newPair);
        } else
        {
            insert(newPair);
        }
    }

    private void splitNode(KeyValuePair<K, V> newPair)
    {
        //TODO
    }

    private void insert(KeyValuePair<K, V> newPair)
    {
        if (links.size() == 0)
        {
            Link<K, V> newLink = new Link<K, V>();
            Link<K, V> newLink2 = new Link<K, V>();
            newLink.rightValue = newPair;
            newLink2.leftValue = newPair;
            links.add(newLink);
            links.add(newLink2);
            return;
        } else
        {
            for (int i = 0; i < links.size(); i++)
            {
                Link<K, V> current = links.get(i);
                if (current.rightValue != null && current.rightValue.compareTo(newPair) > 0 && (current.leftValue == null || current.leftValue.compareTo(newPair) < 0))
                {
                    //insert left
                    Link<K, V> newLink = new Link<K, V>();
                    if (current.leftValue != null)
                    {
                        newLink.leftValue = current.leftValue;
                    }
                    current.leftValue = newPair;
                    newLink.rightValue = newPair;
                    links.add(newLink);
                    Collections.sort(links);
                    return;
                } else if (current.leftValue != null && current.leftValue.compareTo(newPair) < 0 && current.rightValue == null || current.rightValue.compareTo(newPair) > 0)
                {
                    //insert right
                    Link<K, V> newLink = new Link<K, V>();
                    if (current.rightValue != null)
                    {
                        newLink.rightValue = current.rightValue;
                    }
                    current.rightValue = newPair;
                    newLink.leftValue = newPair;
                    links.add(newLink);
                    Collections.sort(links);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Cant insert the pair here");
    }

    private Node<K, V> getLastNode(K key)
    {
        if (key != null)
        {
            Node<K, V> current;
            Node<K, V> next = this;
            do
            {
                current = next;
                next = current.getNextNode(key);
            } while (next != null || current.equals(next));
            return current;

        }
        return null;
    }

    public V remove(K key)
    {
        //TODO
        return null;
    }

    public static class Link<K extends Comparable, V> implements Comparable<Link<K, V>>
    {
        int position;
        Node<K, V> subnode;
        KeyValuePair<K, V> leftValue;
        KeyValuePair<K, V> rightValue;

        @Override
        public int compareTo(Link<K, V> o)
        {
            if (o == null)
            {
                return 1;
            } else
            {
                if (rightValue != null && o.rightValue != null && rightValue.compareTo(o.rightValue) >= 0)
                {
                    return 1;
                } else if (leftValue != null && o.leftValue != null && leftValue.compareTo(o.leftValue) >= 0)
                {
                    return 1;
                } else if (rightValue == null && o.leftValue == null)
                {
                    return 1;
                } else if (rightValue != null && o.rightValue != null && rightValue.compareTo(o.rightValue) <= 0)
                {
                    return - 1;
                } else if (leftValue != null && o.leftValue != null && leftValue.compareTo(o.leftValue) <= 0)
                {
                    return - 1;
                } else if (leftValue == null && o.rightValue == null)
                {
                    return - 1;
                } else
                {
                    return 0;
                }
            }
        }
    }

}
