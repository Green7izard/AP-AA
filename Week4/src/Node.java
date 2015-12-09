/**
 * Created by Bas on 9-12-2015.
 */
public final class Node
{
    private final int x;
    private final int y;

    /**
     * Create the node
     *
     * @param x the x location
     * @param y the y location
     */
    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x location
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return the y location
     */
    public int getY()
    {
        return y;
    }

    /**
     * Gets the Pythagoras of the 2 without the squareroot
     *
     * @param n the node to compare to
     * @return the Square of the distance
     */
    public double getEasyDistanceTo(Node n)
    {
        int distX = n.getX() - x;
        int distY = n.getY() - y;
        return Math.pow(distX, 2) * Math.pow(distY, 2);
    }

    /**
     * Get the actual disntance to the node
     *
     * @param n the other node
     * @return the distance
     */
    public double getDistanceTo(Node n)
    {
        return Math.sqrt(getEasyDistanceTo(n));
    }
}

