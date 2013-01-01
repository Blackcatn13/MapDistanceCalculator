/**
 * File: Position.java
 * Created on 30/12/2012 by Marc
 * 
 * Class that represents a position of a node in a 2D space
 */
package algorithm;

public class Position {
    
    /**
     * Variable for the x coordinate.
     */
    private int x;
    
    /**
     * Variable for the y coordinate.
     */
    private int y;
    
    /**
     * Variable for the name.
     */
    private String name;
    
    /**
     * Default Constructor.
     */
    public Position() {
	x = 0;
	y = 0;
	name = "";
    }
    
    /**
     * Constructor with all the parameters.
     * @param nx x position of the node.
     * @param ny y position of the node.
     * @param n name of the node.
     */
    public Position(int nx, int ny, String n) {
	x = nx;
	y = ny;
	name = n;
    }
    
    /**
     * Getter of the name.
     * @return The name of the node.
     */
    public String getName() {
	return name;
    }
    
    /**
     * Getter of the x position.
     * @return The x position.
     */
    public int getX() {
	return x;
    }
    
    /**
     * Getter of the y position.
     * @return The y position.
     */
    public int getY() {
	return y;
    }
}
