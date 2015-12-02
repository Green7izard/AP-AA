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
        this(nodeSize, new ArrayList<Link<K, V>>(nodeSize+1));
    }

    public Node(int nodeSize, List<Link<K, V>> links)
    {
        this.nodeSize = nodeSize;
        this.links = links;
        parent=null;
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
                for (Link link : next.links)
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
                next = next.getNextNode(key);
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
                else if ( (link.rightValue != null&&link.rightValue.compareTo(key) == 0) || (link.leftValue != null && link.leftValue.compareTo(key) == 0))
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
            if(link.leftValue!=null)
            {
                total++;
            }
            if (link.subnode != null)
            {
                total += link.subnode.amountOfPairs();
            }
        }
            return total;
    }

    public boolean isFull()
    {
        return links.size() == nodeSize+1;
    }

    protected boolean isOverFull(){
        return links.size()>nodeSize+1;
    }

    public Node<K,V> put(KeyValuePair<K, V> newPair)
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
                    link.leftValue.setValue(newPair.getValue());
                    return this;
                }
                // Its the Right Value
                else if (link.rightValue != null && link.rightValue.compareTo(newPair.getKey()) == 0)
                {
                    link.rightValue.setValue(newPair.getValue());
                    return this;
                }
            }
            return target.forcePut(newPair);
        }
        return null;
    }

    private Node<K,V> forcePut(KeyValuePair<K, V> newPair)
    {
        if (isFull())
        {
            insert(newPair);
            return splitNode();
        } else
        {
            insert(newPair);
            return this;
        }
    }

    private Node<K,V> splitNode()
    {
        //Only split if high enough
        if(links.size()>nodeSize/2)
        {
            //Get the middle
            int middle = links.size()/2;
            ArrayList <Link<K,V>> leftSplit = new ArrayList<Link<K, V>>(nodeSize+1);
            ArrayList <Link<K,V>> rightSplit= new ArrayList<Link<K, V>>(nodeSize+1);
            KeyValuePair<K,V> middleValue = links.get(middle).leftValue;
            //Sort the links into a left and a right side
            for(int i = 0; i<links.size();i++)
            {
                if(i<middle)
                {
                    leftSplit.add(links.get(i));
                } else if(i>=middle)
                {
                    rightSplit.add(links.get(i));
                }
            }
            //Change the state of the outer values
            leftSplit.get(leftSplit.size()-1).rightValue=null;
            rightSplit.get(0).leftValue=null;

            //Create a new right node
            Node<K,V> newRight = new Node(nodeSize, rightSplit);

            //Create a new links that will go to the parent
            Link rightLink = new Link<K,V>();
            rightLink.leftValue=middleValue;
            rightLink.subnode=newRight;

            //Node<K,V> newLeft = new Node(nodeSize, leftSplit);
            this.links = leftSplit;
            Link<K,V> leftLink = new Link();
            leftLink.rightValue= middleValue;
            leftLink.subnode=this;

            //If there is no parent, create it
            if(parent==null)
            {
                parent = new Node<K,V>(nodeSize);
            }
            //Insert into parent
            return parent.insertLinks(leftLink, rightLink, this);
        }
        return this;
    }

    public Node<K,V> getRoot()
    {
        Node<K,V> current = this;
        while(current.getParent()!=null)
        {
            current=current.getParent();
        }
        return current;
    }

    private Node<K,V> insertLinks(Link<K, V> leftLink, Link rightLink, Node<K,V> oldNode)
    {
        if(leftLink.subnode!=null)
        {
            leftLink.subnode.parent = this;
        }
        if(rightLink.subnode!=null)
        {
            rightLink.subnode.parent = this;
        }
        if(links.size()==0)
        {
            links.add(leftLink);
            links.add(rightLink);
        }
        else
        {
            int currentLocation=-1;
            for(int i=0;i<links.size();i++)
            {
                Link<K,V> link = links.get(i);
                if(link!=null)
                {
                    if (oldNode.equals(links.get(i).subnode))
                    {
                        currentLocation = i;
                        break;
                    } else if ((link.leftValue==null||link.leftValue.compareTo(leftLink.rightValue)<0)&&(link.rightValue==null||link.rightValue.compareTo(rightLink.leftValue)>0))
                    {
                        currentLocation = i;
                    }
                }
            }
            if(currentLocation==0)
            {
                Link<K,V> currentLeft = links.get(currentLocation);
                links.add(0, leftLink);
                if(currentLeft.subnode==null || currentLeft.subnode.equals(oldNode))
                {
                    currentLeft.subnode=rightLink.subnode;
                }
                else if(rightLink.subnode!=null)
                {
                    currentLeft.subnode.mergeNode(rightLink.subnode);
                }
                currentLeft.leftValue=rightLink.leftValue;
            }
            else if(currentLocation==links.size()-1){
                Link<K,V> currentRight = links.get(currentLocation);
                links.add(rightLink);
                if(currentRight.subnode==null|| currentRight.subnode.equals(oldNode))
                {
                    currentRight.subnode = leftLink.subnode;
                }
                else if(leftLink.subnode!=null)
                {
                    currentRight.subnode.mergeNode(leftLink.subnode);
                }
                currentRight.rightValue=leftLink.rightValue;
            }
            else
            {
                Link<K,V> currentHolder = links.get(currentLocation);
                Link<K,V> currentRight = links.get(currentLocation+1);
                KeyValuePair<K,V> middleGuard = currentHolder.rightValue;
                currentHolder.rightValue= leftLink.rightValue;
                currentHolder.subnode=rightLink.subnode;
                rightLink.rightValue=middleGuard;
                links.add(rightLink);
            }
        }
        Collections.sort(links);
        if(isOverFull())
        {
            return splitNode();
        }
        else{
            return this;
        }
    }

    private void mergeNode(Node toMerge)
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
            Collections.sort(links);
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
            } while (next != null && !current.equals(next));
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