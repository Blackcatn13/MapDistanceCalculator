/**
 * 
 */
package graph.parser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import algorithm.Position;

/**
 * @author xyz0k
 *
 */

public class ParserQuadrant {
	
	int nNodes;
	public ParserQuadrant(){
		nNodes = 0;
	}
	
	public ArrayList<Position> quadrantInit(String filename) throws Exception{
		FileInputStream fstream = new FileInputStream("heuristicInit/".concat(filename));
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader file = new BufferedReader(new InputStreamReader(in));
		ArrayList<Position> alPos = new ArrayList<Position>();
		nNodes = Integer.parseInt(file.readLine());
		String[] splitLine;
		for (int i = 0; i < nNodes; i++){
			splitLine = file.readLine().split(" ");
			alPos.add(new Position(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[0]));
		}		
		file.close();
		return alPos;
	}
}
