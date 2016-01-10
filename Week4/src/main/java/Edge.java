/**
 * Created by Bas on 9-12-2015.
 */
public class Edge implements Comparable<Edge>
{
    final Node[] nodes;
    final double distance;

    public Edge(Node node1, Node node2)
    {
        nodes = new Node[2];
        nodes[0] = node1;
        nodes[1] = node2;
        node1.addEdge(this);
        node2.addEdge(this);
        distance = node1.getDistanceTo(node2);
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
     * Get the actual disntance to the node
     *
     * @return the distance
     */
    public double getDistance()
    {
        return distance;
    }

    @Override
    public int compareTo(Edge o)
    {
        double diff = distance - o.distance;
        if(diff>0)
        {
            return Math.max(1, (int) Math.round(diff));
        } else if(diff<0)
        {
            return Math.min(-1, (int)Math.round(diff));
        }
        else{
            return 0;
        }

    }

    public boolean equals(Object other)
    {
        if (other instanceof Edge)
        {
            Edge o = (Edge) other;
            return o.isEdge(nodes[0], nodes[1]);
        } else
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
