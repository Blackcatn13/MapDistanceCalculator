/**
 * 
 */
package window;

import java.util.ArrayList;

import graph.components.Graph;
import graph.components.Node;

/**
 * @author xyz0k
 *
 */
public class StringMatch {
	
	Graph myGraph;
	ArrayList<String> strList;
	String str;

	/**
	 * Constructor of the class.
	 * @param graph graph to analyze node names.
	 */
	public StringMatch(Graph graph){
		myGraph = graph;
		strList = new ArrayList<String>();
		initStrGraph(myGraph);
	}
	
	/**
	 * 
	 * @return
	 */
	public String stringMatching(String inputStr){
		int minValue = 1000;
		String strMin = null;
		for (String strAct : strList){
			if (distLevenshtein(inputStr, strAct) < minValue){
				minValue = distLevenshtein(inputStr, strAct);
				strMin = strAct;
			}
		}
		return strMin;
	}
	
	/**
	 * Method that calculates the distance of Levenshtein.
	 * @param inputStr String input of the final user.
	 * @param nodeStr String name of one node.
	 * @return
	 */
	private int distLevenshtein(String inputStr, String nodeStr){
		return distLevenshtein (inputStr.toCharArray(), nodeStr.toCharArray());
	}
	
	/**
	 * Override of distLevenshtein method in order to user char arrays.
	 * @param inputChar char array input of the final user.
	 * @param nodeChar char array name of one node.
	 * @return
	 */
	private int distLevenshtein(char[] inputChar, char[] nodeChar) {
		
		return 0;
	}

	/**
	 * Method to check if the input string has been written correctly.
	 * @param inputStr input of the final user.
	 * @return boolean to determinate if string is correct or not.
	 */
	public boolean stringMatchName (String inputStr){
		for (String strAct : strList){
			if(strAct.equals(inputStr)) return true;
		}
		return false;
	}

	/**
	 * Method to create a list of all of the names of the nodes of the graph.
	 * @param graph selected graph to check the node names.
	 */
	private void initStrGraph(Graph graph) {
		strList = null;
		for (Node node : graph.getNodes()){
			strList.add(node.getName());
		}
	}
}
