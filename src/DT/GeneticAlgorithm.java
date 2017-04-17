package DT;

import java.util.Random;

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
    public static DT swapNode(DT tree) {
    	Random rng = new Random();
    	int index = Math.abs(rng.nextInt(tree.nodeMap.size()));
		
    	if (tree.nodeMap.get(index).condition != null) {
    		tree.nodeMap.put(index,tree.getRandomCondition().setConditions(
    				tree.nodeMap.get(index).conditionTrue, tree.nodeMap.get(index).conditionFalse));
    	}
    	else {
    		tree.nodeMap.put(index,tree.getRandomBehavior().setConditions(
    				-1, -1));
    	}
    	
    	return tree;
    }
    
    public static DT mutate(DT tree) {
    	return GeneticAlgorithm.swapNode(tree);
    }
}
