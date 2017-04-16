package DT;

import java.util.HashMap;
import general.Battle;
import general.Decision;
import trainers.Trainer;

public class DT {

    // A map of DecisionNode ids to the Decision nodes themselves.
    public HashMap<Integer, DecisionNode> nodeMap;
    public final static boolean debugOut = true;

    // Constructor for decision tree.
    public DT() {
        this.nodeMap = new HashMap<Integer, DecisionNode>();
        this.buildTree();
    }

    // Constructs the decisionNodes
    private void buildTree() {

        /**
         * IF health > 20 True: use strongest attack False: check heal item
         */
        this.nodeMap.put(0, new DecisionNode(0,
                new Condition_HealthGreaterThanPercent(.2f), null, 3, 1));

        /**
         * IF have heal item True: use it False: check if opponent can kill
         * monster
         */
        this.nodeMap.put(1, new DecisionNode(0,
                new Condition_UserHasHealItem(), null, 4, 2));

        /**
         * if opponent can kill monster True: switch to monster with best attack
         * False: use strongest attack
         */
        this.nodeMap.put(2, new DecisionNode(0,
                new Condition_OpponentCanKillMonster(), null, 5, 3));

        // decisions
        this.nodeMap.put(3, new DecisionNode(0, null,
                new Behavior_UseHighestDamageMove(), -1, -1));
        this.nodeMap.put(4, new DecisionNode(0, null, new Behavior_HealHP(),
                -1, -1));
        this.nodeMap.put(5, new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithBestAttack(), -1, -1));

        // DECISIONS NOT USED
        // this.nodeMap.put(14, new DecisionNode(0, null, new
        // Behavior_ChangeToMonsterWithHighSurvivability(), -1, -1));
        //
        // this.nodeMap.put(16, new DecisionNode(0, null, new Behavior_HealPP(),
        // -1, -1));
        // this.nodeMap.put(17, new DecisionNode(0, null, new
        // Behavior_HealStatus(), -1, -1));
        // this.nodeMap.put(18, new DecisionNode(0, null, new
        // Behavior_InflictStatusEffect(), -1, -1));
        //
        // this.nodeMap.put(20, new DecisionNode(0, null, new
        // Behavior_SwitchToMonsterWithHighestHP(), -1, -1));
        // this.nodeMap.put(21, new DecisionNode(0, null, new
        // Behavior_SwitchToMonsterWithLowestHP(), -1, -1));
        // this.nodeMap.put(22, new DecisionNode(0, null, new
        // Behavior_SwitchToMonsterWithStrongType(), -1, -1));
        // this.nodeMap.put(23, new DecisionNode(0, null, new
        // Behavior_SwitchToMonsterWithWeakType(), -1, -1));
        // this.nodeMap.put(24, new DecisionNode(0, null, new
        // Behavior_UseHighestAccuracyMove(), -1, -1));
        //
        // this.nodeMap.put(26, new DecisionNode(0, null, new
        // Behavior_UseLowestAccuracyMove(), -1, -1));
        // this.nodeMap.put(27, new DecisionNode(0, null, new
        // Behavior_UseLowestDamageMove(), -1, -1));
        //
        // // unused conditions
        // this.nodeMap.put(9, new DecisionNode(0, new
        // Condition_IfSomeMoveHasNoPP(), null, 0, 0));
        // this.nodeMap.put(4, new DecisionNode(0, new
        // Condition_IsOpponentStatusNormal(), null, 0, 0));
        // this.nodeMap.put(5, new DecisionNode(0, new
        // Condition_BestAttackHasLowPP(), null, 0, 0));
        // this.nodeMap.put(6, new DecisionNode(0, new
        // Condition_CanStatusHealItself(), null, 0, 0));
        // this.nodeMap.put(7, new DecisionNode(0, new
        // Condition_IsOpponentStatusNotNormal(), null, 0, 0));
        // this.nodeMap.put(8, new DecisionNode(0, new
        // Condition_IsStatusNotNormal(), null, 0, 0));
        // this.nodeMap.put(10, new DecisionNode(0, new
        // Condition_OpponentHealthLowerThanPercent(20), null, 0, 0));
        // this.nodeMap.put(11, new DecisionNode(0, new
        // Condition_OpponentHealthLowerThanValue(50), null, 0, 0));
        // this.nodeMap.put(12, new DecisionNode(0, new
        // Condition_OtherMonsterCanSurviveOpponentAttack(), null, 0, 0));
        // this.nodeMap.put(13, new DecisionNode(0, new
        // Condition_HealthGreaterThanValue(50), null, 0, 0));
    }

    /**
     * Run through the decision tree and make a decision
     * 
     * @param battle
     * @param trainer
     * @param i
     * @return
     */
    public Decision makeDecision(Battle battle, Trainer trainer, int i) {

        String prefix = "  ";
        if (debugOut)
            System.out.println("Making Decision for " + trainer.name);

        DecisionNode current = this.nodeMap.get(0);

        while (current.behavior == null) {

            if (current.condition.check_condition(battle, trainer)) {
                if (debugOut)
                    System.out
                            .println(prefix + "Condition "
                                    + current.condition.toString()
                                    + " evaluated true.");
                current = nodeMap.get(current.conditionTrue);
            } else {
                if (debugOut)
                    System.out.println(prefix + "Condition "
                            + current.condition.toString()
                            + " evaluated false.");
                current = nodeMap.get(current.conditionTrue);
            }
            prefix += "  ";
        }

        if (debugOut)
            System.out.println("  Behavior " + current.behavior.toString()
                    + " selected.");
        return current.behavior.execute(battle, trainer);
    }

    // PRIVATE BOOLEAN
    /**
     * Execution on this node. Checks if the condition evaluates to true or
     * false, and executes on the appropriate child node.
     */
    // T execute(Battle battle, Trainer user) {
    // if (check_condition(battle, user)) {
    // System.out.println(name + " evaluated to true; executing on "
    // + true_child.name);
    // return true_child.execute(battle, user);
    // } else {
    // System.out.println(name + " evaluated to false; executing on "
    // + false_child.name);
    // return false_child.execute(battle, user);
    // }
    // }

}