package DT;

import DT.Condition;
import DT.Behavior;

/**
 * Class representation of a node in the decision tree. 
 * A node either has a condition or an action but never both.
 * If a node has a behavior it does not have valid ids for conditionTrue 
 * and conditionFalse as they are not needed.
 * For a condition node, they have ids of subsequent nodes based on the 
 * result of the evaluation of their condition.
 */
public class DecisionNode {

    // id of the node
    public int id;

    // condition of the node
    public Condition condition;

    // behavior of the node
    public Behavior behavior;

    // Resulting node if condition is true
    public int conditionTrue = -1;

    // Resulting node if condition is false
    public int conditionFalse = -1;

    // Constructor to set the properties of the node
    public DecisionNode(int id, Condition condition, Behavior behavior, 
    		int condTrue, int condFalse){
        this.condition = condition;
        this.behavior = behavior;
        this.id = id;
        this.conditionTrue = condTrue;
        this.conditionFalse = condFalse;
    }
}