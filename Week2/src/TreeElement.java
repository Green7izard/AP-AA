/**
 * Created by Bas on 25-11-2015.
 */
public interface TreeElement<K extends Comparable, V>
{
    /**
     * Return true if it has childeren
     * @return true if it has childeren, false if it doesnt have any childeren or is a leaf
     */
    boolean hasChildren();

    /**
     * Returns the full list of childeren it has
     * could contain null values
     * @return list of values if it has childeren, null if it does not have any
     */
    TreeElement<K,V>[] getChildren();

    /**
     * @return True if it holds any values
     */
    boolean isEmpty();

    /**
     * @return True if it is full
     */
    boolean isFull();

    /**
     * @return the value of the children, or 1 if its a leaf
     */
    int size();

    /**
     * If it contains the key
     * @param key the desired key
     * @return true if it does
     */
    boolean containsKey(K key);

    /**
     * Gets the elements that holds the key
     * @param key the desired key
     * @return the element that holds it
     */
    TreeElement<K,V> getHolder(K key);
}
