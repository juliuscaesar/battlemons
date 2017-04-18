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
	 * Swaps true and false conditions
	 * 
	 */
	public static DT swapConditions(DT tree) {
		tree.nodeMap.get(0).setConditions(tree.nodeMap.get(0).conditionFalse, 
				tree.nodeMap.get(0).conditionTrue);
		return tree;
	}

	/**
	 * Swaps a node in a decision tree with the given node
	 * 
	 */
	public DT swapNode(DT tree) {
		Random rng = new Random();
		int index = Math.abs(rng.nextInt(tree.nodeMap.size()));


		if (tree.nodeMap.get(index).condition != null) {
			DecisionNode node = tree.getRandomCondition();
			node.setConditions(tree.nodeMap.get(index).conditionTrue, 
					tree.nodeMap.get(index).conditionFalse);
			if (!tree.nodeMap.containsKey(node.conditionTrue)) {
				//node.setConditions(tree.getRandomBehavior(), node.conditionFalse);
			}

			if (!tree.nodeMap.containsKey(node.conditionFalse)) {
				//node.setConditions(node.conditionTrue, tree.getRandomBehavior());
			}
			if (tree.nodeMap.get(index).condition != null) {

				tree.nodeMap.put(index, node);
			}
		}
		else {
			tree.nodeMap.put(index,tree.getRandomBehavior().setConditions(
					-1, -1));
		}
		return tree;
	}

	public DT mutate(DT tree) {
		//return this.swapConditions(tree);
		return this.swapNode(tree);
	}
}
