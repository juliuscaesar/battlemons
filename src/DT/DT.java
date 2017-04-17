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
		if (random) {
			this.randomBuild();
		}
		else {
			this.buildTree();
		}
		this.buildConds();
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
		this.behaviors.add(new DecisionNode(0, null, new Behavior_HealHP(),
				-1, -1));
		this.behaviors.add(new DecisionNode(0, null,
				new Behavior_SwitchToMonsterWithBestAttack(), -1, -1));

		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_SwitchToMonsterWithHighSurvivability(), -1, -1));
		
		 this.behaviors.add(new DecisionNode(0, null, new Behavior_HealPP(),
		 -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_HealStatus(), -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_InflictStatusEffect(), -1, -1));
		
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_SwitchToMonsterWithHighestHP(), -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_SwitchToMonsterWithLowestHP(), -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_SwitchToMonsterWithStrongType(), -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_SwitchToMonsterWithWeakType(), -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_UseHighestAccuracyMove(), -1, -1));
		
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_UseLowestAccuracyMove(), -1, -1));
		 this.behaviors.add(new DecisionNode(0, null, new
		 Behavior_UseLowestDamageMove(), -1, -1));
		
		 // unused conditions
		 this.conditions.add(new DecisionNode(0, new
		 Condition_IfSomeMoveHasNoPP(), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_IsOpponentStatusNormal(), null, 0, 0));

		 this.conditions.add(new DecisionNode(0, new
		 Condition_CanStatusHealItself(), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_IsOpponentStatusNotNormal(), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_IsStatusNotNormal(), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_OpponentHealthLowerThanPercent(20), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_OpponentHealthLowerThanValue(50), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_OtherMonsterCanSurviveOpponentAttack(), null, 0, 0));
		 this.conditions.add(new DecisionNode(0, new
		 Condition_HealthGreaterThanValue(50), null, 0, 0));
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
	
	public DecisionNode getRandomCondition(){
		Random rng = new Random();
		int randomIndex = Math.abs(rng.nextInt(this.conditions.size()));
		int randomIndex1 = Math.abs(rng.nextInt(this.behaviors.size()));
		int randomIndex2 = Math.abs(rng.nextInt(this.behaviors.size()));
		return this.conditions.get(randomIndex).setConditions(randomIndex1, randomIndex2);
	}
	
	public DecisionNode getRandomBehavior(){
		Random rng = new Random();
		int randomIndex = Math.abs(rng.nextInt(this.behaviors.size()));
		return this.conditions.get(randomIndex);
	}
}