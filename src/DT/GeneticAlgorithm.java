package DT;


/**
 * Genetic Algorithm that performs mutations on the
 * decision tree
 * 
 */
public class GeneticAlgorithm {
    /**
     * Mutate the parameters of a node
     * 
     */
    public static void mutateParameters(DT tree, int index, double newParam) {
    	if (tree.nodeMap.get(index).condition.uses_parameter) {
    		tree.nodeMap.get(index).condition.setParam(newParam);
    	}
    }

	/**
     * Remove a node from the decision tree
     * 
     */
    public static void removeNode(DT tree, int index) {
    	tree.nodeMap.remove(index);
    }
    
    /**
     * Adds a node to the decision tree
     * 
     */
    public static void addNode(DT tree, DecisionNode node) {
        tree.nodeMap.put(tree.nodeMap.size(), node);
    }
    
    /**
     * Swaps a node in a decision tree with the given node
     * 
     */
    public static void swapNode(DT tree, int from, DecisionNode newNode) {
    	tree.nodeMap.replace(from, newNode);
    }
}
