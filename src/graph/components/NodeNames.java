/**
 * File: NodeNames.java
 * Created on 07/12/2012 by Marc
 * 
 * @author Marc
 * Class that represents a Node Name
 */
package graph.components;

public class NodeNames {
    /**
     * The name
     */
    private String Name;
    
    /**
     * Constructor.
     * @param n is the name for that node.
     */
    public NodeNames(String n) {
	Name = n;
    }
    
    /**
     * Default Constructor.
     */
    public NodeNames() {
	Name = "";
    }
    
    /**
     * Getter of the name.
     * @return The name of that NodeName.
     */
    public String getName() {
	return Name;
    }
    
    /**
     * Function to compare if a name is the same.
     * @param n name to compare.
     * @return True if the name is the same.
     */
    public boolean SameName(String n) {
	return Name.toLowerCase().equals(n.toLowerCase());
    }
    
    /**
     * Setter of the name.
     * @param n is the new name to set.
     */
    public void setName(String n) {
	Name = n;
    }
}
