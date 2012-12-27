package algorithm;

import graph.components.Graph;
import graph.components.Node;

public class Heuristic1 extends Heuristic{

    enum names {n1, n2, n3, n4, n5, n6};
    @Override
    public void init(Graph g) {
	this.setGraph(g);
    }

    @Override
    public float Calculate(Node s, Node d) {
	if(s.costTo(d) != -1) {
	    return s.costTo(d);
	}
	return 5;
    }

}
