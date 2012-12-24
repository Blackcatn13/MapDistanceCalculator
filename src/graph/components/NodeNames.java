/**
 * File: NodeNames.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a Node Name
 */
package graph.components;

public class NodeNames {
    
    private String Name;
    
    public NodeNames(String n) {
	Name = n;
    }
    
    public String getName() {
	return Name;
    }
    
    public boolean SameName(String n) {
	return Name.equals(n);
    }
}
