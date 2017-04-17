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
    public static DT mutateParameters(DT tree, int index, double newParam) {
    	if (tree.nodeMap.get(index).condition.uses_parameter) {
    		tree.nodeMap.get(index).condition.setParam(newParam);
    		return tree;
    	}
    	return tree;
    }

	/**
     * Remove a node from the decision tree
     * 
     */
    public static DT removeNode(DT tree, int index) {
    	tree.nodeMap.remove(index);
    	return tree;
    }
    
    /**
     * Adds a node to the decision tree
     * 
     */
    public static DT addNode(DT tree, DecisionNode node) {
        tree.nodeMap.put(1, node);
        return tree;
    }
    
    /**
     * Swaps a node in a decision tree with the given node
     * 
     */
    public static DT swapNode(DT tree, int from, DecisionNode newNode) {
    	tree.nodeMap.replace(from, newNode);
    	return tree;
    }
}
