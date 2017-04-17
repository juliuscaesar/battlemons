package DT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import general.Battle;
import general.BattleVariables;
import general.Decision;
import monsters.Monster;
import trainers.Trainer;

public class DT {

    // A map of DecisionNode ids to the Decision nodes themselves.
    public HashMap<Integer, DecisionNode> nodeMap;
    public final static boolean debugOut = BattleVariables.printEachTurn;

    public ArrayList<DecisionNode> conditions = new ArrayList();

    public ArrayList<DecisionNode> behaviors = new ArrayList();

    // Constructor for decision tree.
    public DT() {
        this.nodeMap = new HashMap<Integer, DecisionNode>();
        this.buildTree();
        this.buildConds();
    }

    // Constructor for decision tree including a random construction
    public DT(boolean random) {
        this.nodeMap = new HashMap<Integer, DecisionNode>();
        this.buildConds();
        if (random) {
            this.randomBuild();
        } else {
            this.buildTree();
        }
    }

    // Constructs the decisionNodes
    private void buildTree() {
        /**
         * IF health > 20 True: use strongest attack False: check heal item
         */
        this.nodeMap.put(0, new DecisionNode(0,
                new Condition_HealthGreaterThanPercent(.2), null, 3, 1));

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
    }

    private void randomBuild() {
        // add conditions
        for (int i = 0; i < this.conditions.size(); i++) {
            this.nodeMap.put(i, this.conditions.get(i));
        }

        // add behaviors
        for (int i = 0; i < this.behaviors.size(); i++) {
            this.nodeMap.put(this.nodeMap.size(), this.behaviors.get(i));
        }

        // link them all together
        for (int i = 0; i < this.nodeMap.size(); i++) {
            if (this.nodeMap.get(i).behavior != null) {
                this.nodeMap.get(i).setConditions(-1, -1);
            } else {
                Random rng = new Random();
                // int randomint1 = Math.abs(new
                // Integer(this.behaviors.size()).nextInt(this.nodeMap.size()));
                // int randomint2 = Math.abs(rng.nextInt(this.nodeMap.size()));

                int n = this.nodeMap.size() - this.behaviors.size() + 1;

                int ran0 = rng.nextInt() % n;
                int ran1 = rng.nextInt() % n;

                int randomint1 = this.behaviors.size() + ran0;
                int randomint2 = this.behaviors.size() + ran1;
                this.nodeMap.get(i).setConditions(randomint1, randomint2);
            }
        }
    }

    private void buildConds() {
        this.conditions.add(new DecisionNode(0,
                new Condition_HealthGreaterThanPercent(.2), null, 3, 1));

        this.conditions.add(new DecisionNode(0,
                new Condition_UserHasHealItem(), null, 4, 2));
        this.conditions.add(new DecisionNode(0,
                new Condition_OpponentCanKillMonster(), null, 5, 3));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseHighestDamageMove(), -1, -1));
        // this.behaviors.add(new DecisionNode(0, null, new Behavior_HealHP(),
        // -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithBestAttack(), -1, -1));

        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithHighSurvivability(), -1, -1));

        // this.behaviors.add(new DecisionNode(0, null, new Behavior_HealPP(),
        // -1, -1));
        // this.behaviors.add(new DecisionNode(0, null, new
        // Behavior_HealStatus(), -1, -1));
        // this.behaviors.add(new DecisionNode(0, null, new
        // Behavior_InflictStatusEffect(), -1, -1));

        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithHighestHP(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithLowestHP(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithStrongType(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithWeakType(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseHighestAccuracyMove(), -1, -1));

        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseLowestAccuracyMove(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseLowestDamageMove(), -1, -1));

        // unused conditions
        this.conditions.add(new DecisionNode(0,
                new Condition_IfSomeMoveHasNoPP(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_IsOpponentStatusNormal(), null, 0, 0));

        this.conditions.add(new DecisionNode(0,
                new Condition_CanStatusHealItself(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_IsOpponentStatusNotNormal(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_IsStatusNotNormal(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_OpponentHealthLowerThanPercent(20), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_OpponentHealthLowerThanValue(50), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_OtherMonsterCanSurviveOpponentAttack(), null, 0,
                0));
        this.conditions.add(new DecisionNode(0,
                new Condition_HealthGreaterThanValue(50), null, 0, 0));

        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithHighSurvivability(), -1, -1));

        this.behaviors.add(new DecisionNode(0, null, new Behavior_HealPP(), -1,
                -1));
        this.behaviors.add(new DecisionNode(0, null, new Behavior_HealStatus(),
                -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_InflictStatusEffect(), -1, -1));

        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithHighestHP(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithLowestHP(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithStrongType(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_SwitchToMonsterWithWeakType(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseHighestAccuracyMove(), -1, -1));

        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseLowestAccuracyMove(), -1, -1));
        this.behaviors.add(new DecisionNode(0, null,
                new Behavior_UseLowestDamageMove(), -1, -1));

        // unused conditions
        this.conditions.add(new DecisionNode(0,
                new Condition_IfSomeMoveHasNoPP(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_IsOpponentStatusNormal(), null, 0, 0));

        this.conditions.add(new DecisionNode(0,
                new Condition_CanStatusHealItself(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_IsOpponentStatusNotNormal(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_IsStatusNotNormal(), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_OpponentHealthLowerThanPercent(20), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_OpponentHealthLowerThanValue(50), null, 0, 0));
        this.conditions.add(new DecisionNode(0,
                new Condition_OtherMonsterCanSurviveOpponentAttack(), null, 0,
                0));
        this.conditions.add(new DecisionNode(0,
                new Condition_HealthGreaterThanValue(50), null, 0, 0));
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
        this.printTree();
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

    public DecisionNode getRandomCondition() {
        Random rng = new Random();
        int randomIndex = Math.abs(rng.nextInt(this.conditions.size()));
        return this.conditions.get(randomIndex);
    }

    public DecisionNode getRandomBehavior() {
        Random rng = new Random();
        int randomIndex = Math.abs(rng.nextInt(this.behaviors.size()));
        return this.behaviors.get(randomIndex);
    }

    // Starts a recursive print of the tree
    public String printTree() {
        if (nodeMap.size() == 0) {
            return "tree is empty";
        }
        return printTree("", 0, true);
    }

    public String printTree(String prefix, int id, boolean isTrueChild) {

        String result = "\n" + prefix + (isTrueChild ? "True [" : "False [")
                + id + "]: ";

        DecisionNode node = nodeMap.get(id);

        if (node == null) {

            result += "null";

        } else if (node.condition != null) { // this is a condition

            // Get Condition name
            result += "Condition " + node.condition.toString();

            // Print the true child
            if (nodeMap.containsKey(node.conditionTrue)) {
                result += printTree(prefix + "  ", node.conditionTrue, true);
            } else {
                result += "\n" + prefix + "  No valid true child set (id: "
                        + node.conditionTrue + ")";
            }

            // Print the false child
            if (nodeMap.containsKey(node.conditionFalse)) {
                // Recurse
                result += printTree(prefix + "  ", node.conditionFalse, false);
            } else {
                result += "\n" + prefix + "  No valid false child set (id: "
                        + node.conditionFalse + ")";
            }
        } else if (node.behavior != null) { // this is a behavior
            result += "Behavior - " + node.behavior.toString();
        } else { // this is nothing?
            result += "!!!behavior and condition are null!!!";
        }

        return result;
    }
}
