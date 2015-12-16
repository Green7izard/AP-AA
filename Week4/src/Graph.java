import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bas on 9-12-2015.
 */
public class Graph
{

    public static final int defaultNodes = 10;
    public static final int defaultSize = 20;
    private final int width;
    private final int height;
    private List<Node> nodes;

    public Graph(List<Node> nodes, int width, int height)
    {
        this.nodes = nodes;
        this.width = width;
        this.height = height;
    }

    public Graph(int size, int maxX, int maxY)
    {
        this(generateRandom(size, maxX, maxY), maxX, maxY);
    }

    public Graph(int size)
    {
        this(size, size * 2, size * 2);
    }

    public Graph()
    {
        this(defaultNodes, defaultSize, defaultSize);
    }

    public static List<Node> generateRandom(int size, int maxX, int maxY)
    {
        List<Node> nodes = new ArrayList<Node>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++)
        {
            Node newNode;
            do
            {
                newNode = new Node(rand.nextInt(maxX), rand.nextInt(maxY));
            } while (nodes.contains(newNode));
            nodes.add(newNode);
        }
        return nodes;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    public int getNodesNumber()
    {
        return nodes.size();
    }
}
