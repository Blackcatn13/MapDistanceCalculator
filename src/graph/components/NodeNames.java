package graph.components;

/**
 * File: NodeNames.java. Created on 07/12/2012 by Marc
 *
 * Class that represents a Node Name
 */
public class NodeNames {

    /**
     * The name.
     */
    private String name;

    /**
     * The alias of the node.
     */
    private String alias;

    /**
     * Constructor.
     *
     * @param n
     *            is the name for that node.
     * @param a
     *            is the alias name for that node.
     */
    public NodeNames(String n, String a) {
        name = n;
        alias = a;
    }

    /**
     * Default Constructor.
     */
    public NodeNames() {
        name = "";
        alias = "";
    }

    /**
     * Getter of the name.
     *
     * @return The name of that NodeName.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the alias.
     *
     * @return The alias of that NodeName.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Function to compare if a name and an alias is the same.
     *
     * @param n
     *            name to compare.
     * @param a
     *            alias to compare.
     * @return True if the name and alias is the same.
     */
    public boolean same(String n, String a) {
        return (name.toLowerCase().equals(n.toLowerCase()) && alias
                .toLowerCase().equals(a.toLowerCase()));
    }

    /**
     * Function to compare if a name is the same.
     *
     * @param n
     *            name to compare.
     * @return True if the name is the same.
     */
    public boolean sameName(String n) {
        return name.toLowerCase().equals(n.toLowerCase());
    }

    /**
     * Function to compare if an alias is the same.
     *
     * @param a
     *            alias to compare.
     * @return True if the alias is the same.
     */
    public boolean sameAlias(String a) {
        return alias.toLowerCase().equals(a.toLowerCase());
    }

    /**
     * Setter of the name.
     *
     * @param n
     *            is the new name to set.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Setter of the alias.
     *
     * @param a
     *            is the new alias to set.
     */
    public void setAlias(String a) {
        alias = a;
    }
}
