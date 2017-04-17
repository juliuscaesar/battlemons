package DT;


import general.Battle;
import general.Decision;

import trainers.Trainer;

/**
 * An abstract representation of a Behavior (or Leaf) in this tree. Implementing
 * subclasses will determine what the actual behavior of this node is.
 */
abstract class Behavior {

    // Constructor.
    Behavior() {
    }

    abstract Decision execute(Battle battle, Trainer user);

    public void setParam(double newParam) {

    }

    public String toString() {
        return this.getClass().getSimpleName().substring("Behavior_".length());
    }
}