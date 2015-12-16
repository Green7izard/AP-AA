import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame
{

    private static final int DRAW_SCALE = 40;
    private Canvas canvas;
    private Graph currentGraph;
    private ReverseDelete solver;

    private Display()
    {
        super("Graph Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentGraph = new Graph();
        solver = new ReverseDelete(currentGraph);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton nextStep = new JButton("Next!");
        nextStep.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                solver.takeStep();
                redraw();
            }
        });
        panel.add(nextStep);

        JButton complete = new JButton("Auto Solve!");
        complete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                solver.autoComplete();
                redraw();
            }
        });
        panel.add(complete);

        JButton reset = new JButton("Reset!");
        reset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGraph = new Graph();
                solver = new ReverseDelete(currentGraph);
                redraw();
            }
        });
        panel.add(reset);

        canvas = new Canvas();
        canvas.setSize(Graph.defaultSize * DRAW_SCALE, Graph.defaultSize * DRAW_SCALE);
        panel.add(canvas);


        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    //Use ReverseDelete
    public static void main(String[] args)
    {
        System.out.println("Hello World!");

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                final Display display = new Display();
                display.redraw();
            }
        });
    }

    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);
        //redraw();
    }

    private void redraw()
    {
        canvas.getGraphics().setColor(Color.WHITE);
        canvas.getGraphics().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawEdges();
        drawNodes();
    }

    private void drawNodes()
    {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.RED);
        int reduction = DRAW_SCALE / 2;
        for (Node node : currentGraph.getNodes())
        {
            g.fillOval(node.getX() * DRAW_SCALE - reduction, node.getY() * DRAW_SCALE - reduction, DRAW_SCALE, DRAW_SCALE);
        }
    }

    private void drawEdges()
    {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.BLACK);
        for (Edge edge : solver.getEdges())
        {
            g.drawLine(edge.getFirst().getX() * DRAW_SCALE, edge.getFirst().getY() * DRAW_SCALE, edge.getSecond().getX() * DRAW_SCALE, edge.getSecond().getY() * DRAW_SCALE);
            g.drawLine(edge.getFirst().getX() * DRAW_SCALE + 1, edge.getFirst().getY() * DRAW_SCALE, edge.getSecond().getX() * DRAW_SCALE + 1, edge.getSecond().getY() * DRAW_SCALE);
            g.drawLine(edge.getFirst().getX() * DRAW_SCALE, edge.getFirst().getY() * DRAW_SCALE + 1, edge.getSecond().getX() * DRAW_SCALE, edge.getSecond().getY() * DRAW_SCALE + 1);
        }
    }


}
