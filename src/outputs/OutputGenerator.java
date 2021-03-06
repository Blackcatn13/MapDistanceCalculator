package outputs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import algorithm.AStar;
import algorithm.HeuristicD;
import algorithm.HeuristicE;
import algorithm.HeuristicL;
import algorithm.InfoPath;

import graph.components.Graph;
import graph.components.Transports;
import graph.parser.ParserGraph;
import algorithm.Heuristic;
import outputs.Time;

/**
 * @author xyz0k
 *
 */
public class OutputGenerator {

    private static Graph graph;
    private static ArrayList<InfoPath> nodes;
    private static ParserGraph pG;
    private static AStar astar;
    private static Heuristic h;
    private static ArrayList<Heuristic> hList;
    private static Time time;

    /**
     * Default constructor.
     */
    public OutputGenerator(){
        hList = new ArrayList<Heuristic>();
    }

    /**
     * Method that generate TXT output file.
     * @param filename
     */
    public void generateTXT(String filename, Graph graph, ArrayList<Heuristic> hList){
        FileWriter fstream;
        try {
            fstream = new FileWriter("fileOutputs/".concat(filename));
            BufferedWriter file = new BufferedWriter(fstream);
            ArrayList<String> actNodes = new ArrayList<String>();
            ArrayList<String> allNodes = new ArrayList<String>();
            String iniNode;
            String dashed = "------------------------------------------------------";
            float averageTime;
            float averageNVisited;
            float actTime;
            float maxTime;
            float finAverageTime = 0;
            float finAverageNVisited = 0;
            float finMaxTime = 0;
            ArrayList<InfoPath> maxNodePath = null;
            ArrayList<ArrayList<Float>> dijList = null;
            ArrayList<ArrayList<Float>> nodijList = null;
            int iter;
            int finIter = 0;
            int maxTransfers = 20;
            int maxLines = 20;
            int maxPathVisited = 0;
            float maxCost = 0;
            boolean dijkstra = false;
            BitSet b = new BitSet(3);
            //Set of all transport combination.
            //boolean[][] init = new boolean[][]{{false,false,true},{false,true,false},{false,true,true},{true,false,false},{true,false,true},{true,true,false},{true,true,true}};
            //int[][] maxTraLin = new int[][]{{2,0},{4,0},{0,1},{0,3},{0,5},{2,2},{3,5}};
            boolean[][] init = new boolean[][] {{true, true, true}};
            int[][]maxTraLin = new int[][] {{5,5}, {20,20},{40, 40},{80, 80}};
            b.clear();
            for (Heuristic h : hList){
                for(int ii = 0; ii< maxTraLin.length; ii++){
                    maxTransfers = maxTraLin[ii][0];
                    maxLines = maxTraLin[ii][1];
                    file.write("Heuristic usage: ");
                    file.write(String.valueOf(h.getClass().getName()));
                    if (h.getClass().getName().equals("algorithm.HeuristicD")){
                        dijkstra = true;
                        dijList = new ArrayList<ArrayList<Float>>();
                        for(int i = 0; i < graph.getGraphSize(); i++) {
                            dijList.add(new ArrayList<Float>());
                        }
                    }
                    else {
                        nodijList = new ArrayList<ArrayList<Float>>();
                        for(int i = 0; i < graph.getGraphSize(); i++) {
                            nodijList.add(new ArrayList<Float>());
                        }
                    }
                    file.newLine();
                    file.write(dashed);
                    file.newLine();
                    for(int k = 0; k < init.length; k++){
                        b.set(0,init[k][0]);
                        b.set(1,init[k][1]);
                        b.set(2,init[k][2]);
                        averageTime = 0;
                        averageNVisited = 0;
                        actTime = 0;
                        maxTime = 0;
                        iter = 0;
                        for (int i = 0; i < graph.getGraphSize(); i++){
                            actNodes.add("N".concat(String.valueOf(i+1)));
                        }
                        System.out.println("Graph size :" + graph.getGraphSize());
                        int aux = graph.getGraphSize() - 1;
                        for (int i = 0; i < graph.getGraphSize(); i++){
                            if(actNodes.size() > 0){
                                iniNode = actNodes.get(0);
                                actNodes.remove(0);
                                for (int j = 0; j < actNodes.size(); j++){
                                    astar = new AStar(graph,graph.getNodebyAlias(iniNode), graph.getNodebyAlias(actNodes.get(j)), h, maxTransfers, maxLines);
                                    time = new Time();
                                    time.setScale("millisecond");
                                    nodes = astar.getPath(b);
                                    if (dijkstra && nodes.size()>=2){
                                        dijList.get(i).add(nodes.get(nodes.size()-2).getCost());
                                    }
                                    if(!dijkstra && nodes.size()>=2){
                                        System.out.println("Iter num: " + (i*59+j));
                                        float eff  = (dijList.get(i).get(j)/nodes.get(nodes.size()-2).getCost())*100;
                                        System.out.println("D cost: " + dijList.get(i).get(j) + " ND cost: " + nodes.get(nodes.size()-2).getCost() + " %Eff: " + eff);
                                         nodijList.get(i).add(eff);
                                    }
                                    actTime = time.elapsedTime();
                                    if (maxTime < actTime){
                                        maxTime = actTime;
                                    }
                                    if (finMaxTime < maxTime){
                                        finMaxTime = maxTime;
                                        maxNodePath = nodes;
                                        maxPathVisited = astar.getVisitedNodes();
                                        if (!nodes.isEmpty())
                                            maxCost = nodes.get(nodes.size() - 2).getCost();
                                    }
                                    averageTime += actTime;
                                    averageNVisited += astar.getVisitedNodes();
                                    iter++;
                                }
                            }
                        }
                        finAverageTime += averageTime/iter;
                        finAverageNVisited += averageNVisited/iter;
                        finIter++;
                    }
                    file.write("Average calculate path time: " + String.valueOf(finAverageTime/finIter));
                    file.write(" average num nodes visited: " + String.valueOf((int) finAverageNVisited/finIter));
                    file.newLine();
                }
                if (dijkstra) {
                file.write("Dijkstra heuristic has 100% effectivity");
                }
                else{
                    int averageCost = 0;
                    int counter = 0;
                    for(ArrayList<Float> a : nodijList) {
                        for (Float cost : a){
                            averageCost += cost;
                            counter++;
                        }
                    }
                    file.write("This heuristic has an average of: " + averageCost/counter + "% effectivity");
                }
                file.newLine();
                file.write("Max calculate path: " + String.valueOf(finMaxTime));
                file.write(" num nodes visited: " + String.valueOf(maxPathVisited));
                file.newLine();
                file.write("Path of max details");
                file.newLine();
                file.write("\t" + "Node start: " + maxNodePath.get(0).getSNode().getAlias());
                file.newLine();
                file.write("\t" + "Node end: " + maxNodePath.get(maxNodePath.size()-1).getSNode().getAlias());
                file.newLine();
                for (int i = 0; i < maxNodePath.size()-1; i++){
                    InfoPath ip = maxNodePath.get(i);
                    file.write("\t" + ip.getSNode().getName()+ " ("+ip.getSNode().getAlias() + ")" + " to " + maxNodePath.get(i+1).getSNode().getName()+ " (" + maxNodePath.get(i+1).getSNode().getAlias()+ ")");
                    file.newLine();
                }
                file.write("\tTotal cost: " + maxCost);
                file.newLine();
                file.newLine();
                if (!dijkstra)
                    nodijList.clear();
                else
                    dijkstra = false;
            }
            file.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Launch the application.
     * @throws Exception 
     */
    public static void main(String[] args){
        String city = "ciutat60";
        OutputGenerator oG = new OutputGenerator();
        pG = new ParserGraph();
        graph = pG.parseTxtFilewLines(city.concat(".txt"));
        h = new HeuristicD();
        h.init(graph);
        hList.add(h);
        h = new HeuristicE();
        ArrayList<String> names = new ArrayList<String>();
        names .add("Bus60Quadrant.txt");
        names.add("Subway60Quadrant.txt");
        names.add("Walk60Quadrant.txt");
        names.add("Relations.txt");
        h.init(graph);
        h.setParams(names);
        hList.add(h);
        h = new HeuristicL();
        names.clear();
        names.add("S1.txt");
        names.add("S3.txt");
        names.add("S4.txt");
        names.add("S6.txt");
        names.add("B2.txt");
        names.add("B3.txt");
        names.add("B5.txt");
        names.add("B6.txt");
        h.init(graph);
        hList.add(h);
        oG.generateTXT(city.concat("output.txt"), graph, hList);
    }

    /**
     * Function to write the path info in the Text Panel.
     *
     * @param path
     *            The Array with the path info.
     * @param b
     *            The type of transports used.
     * @param alias
     *            If the info is displayed by the alias or the name of the node.
     */
}