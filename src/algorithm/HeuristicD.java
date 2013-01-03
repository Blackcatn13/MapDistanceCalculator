package algorithm;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;

public class HeuristicD extends Heuristic {

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#init(graph.components.Graph)
     */
    @Override
    public void init(Graph g) {
        this.setGraph(g);
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#Calculate(graph.components.Node,
     * graph.components.Node)
     */
    @Override
    public float calculate(Node s, Node d) {
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#Calculate(graph.components.Node,
     * graph.components.Node, graph.components.Transports)
     */
    @Override
    public float calculate(Node s, Node d, Transports t) {
        return 0;
    }

}
