/**
 * Created by Bas on 9-12-2015.
 */
public class Edge implements Comparable<Edge>
{
    final Node[] nodes;

    public Edge(Node node1, Node node2)
    {
        nodes = new Node[2];
        nodes[0] = node1;
        nodes[1] = node2;
    }

    public boolean contains(Node n)
    {
        return nodes[0].equals(n) || nodes[1].equals(n);
    }

    public boolean isEdge(Node node1, Node node2)
    {
        return contains(node1) && contains(node2);
    }

    /**
     * Gets the Pythagoras of the 2 without the squareroot
     *
     * @return the Square of the distance
     */
    public double getEasyDistance()
    {
        return nodes[0].getEasyDistanceTo(nodes[1]);
    }

    /**
     * Get the actual disntance to the node
     *
     * @return the distance
     */
    public double getDistance()
    {
        return nodes[0].getDistanceTo(nodes[1]);
    }

    @Override
    public int compareTo(Edge o)
    {
        return (int) Math.round(getEasyDistance() - o.getEasyDistance());
    }

    public boolean equals(Object other)
    {
        if(other instanceof Edge)
        {
            Edge o = (Edge) other;
            return o.isEdge(nodes[0], nodes[1]);
        }
        else
        {
            return false;
        }
    }

    public Node getFirst()
    {
        return nodes[0];
    }

    public Node getSecond()
    {
        return nodes[1];
    }
}
