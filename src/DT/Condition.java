package DT;


import general.Battle;

import trainers.Trainer;

/**
 * A representation of a Condition in this tree.
 */
abstract class Condition {

    // Parameter used in some conditional comparisons
    double parameter;
    // upper bound
    double upper_bound = Double.MAX_VALUE;
    // lower bound
    double lower_bound = Double.MIN_VALUE;
    // Indication of whether or not the parameter variable is used; if not,
    // then the parameter should not be mutated or modified
    boolean uses_parameter = false;

    // Dead constructor.
    Condition() {
        parameter = 0;
    }

    // Constructor used for direct instantiation (for testing).
    Condition(double parameter) {
        this.parameter = parameter;
    }

    /**
     * Update the parameter of this Condition
     */
    public void setParam(double newParam) {
        this.parameter = newParam;
    }

    // The abstract function representing the actual boolean condition of
    // this node.
    abstract boolean check_condition(Battle battle, Trainer user);

    public String toString() {
        if (uses_parameter) {
            return this.getClass().getSimpleName()
                    .substring("Condition_".length())
                    + " with parameter " + parameter;
        }
        return this.getClass().getSimpleName().substring("Condition_".length());
    }

}
