import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bas on 9-12-2015.
 */
public final class Node
{
    private final int x;
    private final int y;
    private final List<Edge> edges;

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
        edges = new ArrayList<Edge>();
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
        return Math.pow(distX, 2) + Math.pow(distY, 2);
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

    public void addEdge(Edge e)
    {
        if (! edges.contains(e) && e.contains(this))
        {
            edges.add(e);
            Collections.sort(edges);
        }
    }

    public int amountOfEdges()
    {
        return edges.size();
    }

    public List<Edge> getEdges()
    {
        return edges;
    }

    public void removeEdge(Edge edge)
    {
        if (edge.getFirst().equals(this))
        {
            removeEdge(edge.getSecond());
        } else if (edge.getSecond().equals(this))
        {
            removeEdge(edge.getFirst());
        }
    }

    public void removeEdge(Node other)
    {
        for (int i = 0; i < edges.size(); i++)
        {
            Edge edge = edges.get(i);
            if (edge.contains(other))
            {
                edges.remove(i);
            }
        }
    }

    public boolean equals(Object other)
    {
        if (other instanceof Node)
        {
            Node o = (Node) other;
            return o.x == x && o.y == y;
        } else
        {
            return false;
        }
    }
}

