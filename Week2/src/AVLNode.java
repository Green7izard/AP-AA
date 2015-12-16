/**
 * Created by Bas on 16-12-2015.
 */
public class AVLNode<K extends Comparable, V>
{
    private final KeyValuePair<K, V> value;
    protected AVLNode<K, V> leftValue;
    protected AVLNode<K, V> rightValue;
    protected AVLNode<K, V> parent;

    public AVLNode(KeyValuePair<K, V> pair)
    {
        this.value = pair;
    }

    public KeyValuePair<K, V> getValue()
    {
        return value;
    }

    protected AVLNode<K, V> getNextNode(K key)
    {
        int comparison = value.compareTo(key);
        if (comparison > 0)
        {
            return leftValue;
        } else if (comparison < 0)
        {
            return rightValue;
        } else
        {
            return this;
        }
    }

    private AVLNode<K, V> getLastNode(K key)
    {
        if (key != null)
        {
            AVLNode<K, V> current;
            AVLNode<K, V> next = this;
            do
            {
                current = next;
                next = current.getNextNode(key);
            } while (next != null && ! current.equals(next));
            return current;
        }
        return null;
    }

    public V insert(K key, V value)
    {
        AVLNode<K, V> last = getLastNode(key);
        int comparison = last.value.getKey().compareTo(key);
        if (comparison == 0)
        {
            return last.value.setValue(value);
        } else if (comparison > 0)
        {
            last.leftValue = new AVLNode<K, V>(new KeyValuePair<K, V>(key, value));
            last.leftValue.parent = last;
        } else if (comparison < 0)
        {
            last.rightValue = new AVLNode<K, V>(new KeyValuePair<K, V>(key, value));
            last.rightValue.parent = last;
        }
        last.rebalance();
        return null;
    }

    public void rebalance()
    {
        AVLNode<K, V> current = this;
        while (current != null)
        {
            int balanceFactor = current.balanceFactor();
            if (balanceFactor > 1)
            {
                current.rotateRight();
            } else if (balanceFactor < - 1)
            {
                current.rotateLeft();
            }
            current = current.parent;
        }
        while(getRoot().topDownBalance());
    }

    private boolean topDownBalance()
    {
        int balanceFactor = balanceFactor();
        boolean found =false;
        if (balanceFactor > 1)
        {
            rotateRight();
            return true;
        } else if (balanceFactor < - 1)
        {
            rotateLeft();
            return true;
        }
        if(leftValue!=null)
        {
            found = found ||leftValue.topDownBalance();
        }
        if(rightValue!=null)
        {
            found = found ||rightValue.topDownBalance();
        }
        return found;
    }

    private void rotateLeft()
    {
        AVLNode<K, V> oldRight = this.rightValue;
        if (oldRight != null)
        {
            AVLNode<K, V> oldRightsLeft = oldRight.leftValue;
            if(oldRightsLeft!=null)
            {

                oldRight.rotateRight();
                //rightValue.rebalance();
                oldRight = this.rightValue;
                oldRightsLeft = oldRight.leftValue;
            }
            rightValue = oldRightsLeft;
            if(rightValue!=null)
            {
                rightValue.parent=this;
            }
            if(leftValue!=null)
            {
                leftValue.parent=this;
            }
            oldRight.leftValue = this;
            oldRight.parent = this.parent;
            if (parent != null)
            {
                if (parent.leftValue != null && equals(parent.leftValue))
                {
                    parent.leftValue = oldRight;
                } else if (parent.rightValue != null && equals(parent.rightValue))
                {
                    parent.rightValue = oldRight;
                }
            }
            parent = oldRight;
        }
    }

    private void rotateRight()
    {
        AVLNode<K, V> oldLeft = this.leftValue;
        if (oldLeft != null)
        {
            AVLNode<K, V> oldLeftsRight = oldLeft.rightValue;
            if(oldLeft.rightValue!=null)
            {
                oldLeft.rotateRight();
                //rightValue.rebalance();
                oldLeft = this.leftValue;
                oldLeftsRight = oldLeft.rightValue;
            }
            leftValue = oldLeftsRight;
            if(rightValue!=null)
            {
                rightValue.parent=this;
            }
            if(leftValue!=null)
            {
                leftValue.parent=this;
            }
            oldLeft.rightValue = this;
            oldLeft.parent = this.parent;
            if (parent != null)
            {
                if (parent.leftValue != null && equals(parent.leftValue))
                {
                    parent.leftValue = oldLeft;
                } else if (parent.rightValue != null && equals(parent.rightValue))
                {
                    parent.rightValue = oldLeft;
                }
            }
            parent = oldLeft;
        }
    }

    public KeyValuePair<K, V> getKeyValue(K key)
    {
        if (key == null)
        {
            return null;
        } else
        {
            AVLNode<K, V> last = getLastNode(key);
            if (last.value.compareTo(key) == 0)
            {
                return last.value;
            } else
            {
                return null;
            }
        }
    }

    public V get(K key)
    {
        KeyValuePair<K, V> pair = getKeyValue(key);
        if (pair != null)
        {
            return pair.getValue();
        } else
        {
            return null;
        }
    }

    public AVLNode<K, V> getRoot()
    {
        AVLNode<K, V> current = this;
        while (current.parent != null)
        {
            current = current.parent;
        }
        return current;
    }

    public int size()
    {
        int size = 1;
        if (leftValue != null)
        {
            size += leftValue.size();
        }
        if (rightValue != null)
        {
            size += rightValue.size();
        }

        return size;
    }

    /**
     * @return Negative number if the right is bigger, positive if left is bigger
     */
    public int balanceFactor()
    {
        int leftSize = 0;
        int rightSize = 0;
        if (leftValue != null)
        {
            leftSize = leftValue.size();
        }
        if (rightValue != null)
        {
            rightSize = rightValue.size();
        }
        return leftSize - rightSize;
    }
}
