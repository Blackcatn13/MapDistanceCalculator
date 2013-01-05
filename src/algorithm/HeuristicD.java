package algorithm;

import java.util.ArrayList;

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

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#setParams(java.util.ArrayList)
     */
    @Override
    public void setParams(ArrayList<String> filenames) {
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#calculate(graph.components.Node,
     * graph.components.Node, graph.components.Transports, java.lang.String,
     * int, int)
     */
    @Override
    public float calculate(Node s, Node d, Transports t, String lastLine,
            int maxTt, int maxLt) {
        return 0;
    }

}
