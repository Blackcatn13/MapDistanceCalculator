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
	
	ArrayList<String> strList;
	String str;

	/**
	 * Constructor of the class.
	 * @param graph graph to analyze node names.
	 */
	public StringMatch(Graph graph){
		initStrGraph(graph);
	}
	
	/**
	 * 
	 * @return
	 */
	public String stringMatching(String inputStr){
		int minValue = 1000;
		int distAct;
		String strMin = null;
		for (String strAct : strList){
			distAct = distLevenshtein(inputStr, strAct);
			if (distLevenshtein(inputStr, strAct) < minValue){
				minValue = distAct;
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
		int [][] distance = new int[inputChar.length+1][nodeChar.length+1];
		int cost = 1;
		for (int i=0; i<inputChar.length+1; i++){
			distance[i][0]=i;
		}
		for(int i=0; i<nodeChar.length+1; i++){
			distance[0][i]=i;
		}
		for(int i=1; i<inputChar.length; i++){
			for(int j=1; j<nodeChar.length; j++){
				if (inputChar[i] == nodeChar[j]) cost = 0;
				else cost = 1;
				distance[i][j] = Math.min(distance[i-1][j]+1, Math.min(distance[i][j-1]+1, distance[i-1][j-1]+cost));
			}
		}
		return distance[inputChar.length][nodeChar.length];
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
		strList = new ArrayList<String>();
		for (Node node : graph.getNodes()){
			strList.add(node.getName().toLowerCase());
		}
	}
}
