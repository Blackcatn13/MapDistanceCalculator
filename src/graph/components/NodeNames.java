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
     * The alias of the node
     */
    private String Alias;
    /**
     * Constructor.
     * @param n is the name for that node.
     * @param m is the alias name for that node.
     */
    public NodeNames(String n, String a) {
	Name = n;
	Alias = a;
    }
    
    /**
     * Default Constructor.
     */
    public NodeNames() {
	Name = "";
	Alias = "";
    }
    
    /**
     * Getter of the name.
     * @return The name of that NodeName.
     */
    public String getName() {
	return Name;
    }
    
    public String getAlias() {
    return Alias;
    }
    
    /**
     * Function to compare if a name is the same.
     * @param n name to compare.
     * @param m alias to compare.
     * @return True if the name and alias is the same.
     */
    public boolean Same(String n, String a){
    	return (Name.toLowerCase().equals(n.toLowerCase()) && Alias.toLowerCase().equals(a.toLowerCase()));
    }
    
    public boolean SameName(String n){
    	return Name.toLowerCase().equals(n.toLowerCase());
    }
    
    public boolean SameAlias(String a){
    	return Alias.toLowerCase().equals(a.toLowerCase());
    }
    
    /**
     * Setter of the name.
     * @param n is the new name to set.
     */
    public void setName(String n) {
	Name = n;
    }
    public void setAlias(String a){
    Alias = a;
    }
}
