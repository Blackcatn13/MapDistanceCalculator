package algorithm;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;

public class HeuristicD extends Heuristic{

    enum names {n1, n2, n3, n4, n5, n6};
    @Override
    public void init(Graph g) {
	this.setGraph(g);
    }

    @Override
    public float Calculate(Node s, Node d) {
	return 0;
    }

    @Override
    public float Calculate(Node s, Node d, Transports t) {
	return 0;
    }

}
