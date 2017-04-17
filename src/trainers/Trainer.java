package trainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DT.Behavior_SwitchToMonsterWithBestAttack;
import DT.DT;
import general.Attack;
import general.Battle;
import general.BattleVariables;
import general.Decision;
import general.MonsterID;
import monsters.Monster;
import monsters.MonsterSet;
import moves.MoveSet;

/**
 * Trainer Class.
 * 
 * A Trainer is someone that have a List of Monsters, a List of Items, and can make 3 types of decision: { Attack, Use Item, Switch Monster }.
 * The Trainer has a Active Monster, that is currently in battle. The Active Monster is the only Monster that the Trainer can use to Attack
 * or that can be Attacked.
 * 
 * A Trainer can only take one decision per turn. So, on each turn the trainer can either attack with this active monster, switch its active monster
 * or use an item on any of his monsters.
 * 
 * Upon taking fatal damage, a Trainer's active monster's alive state is changed to [Dead] and the active Monster is forcefully changed to another
 * monster owned by the Trainer.
 * A Trainer can not switch to a [Dead] Monster.
 * A Trainer will lose a Battle if all his Monsters are [Dead].
 * 
 */
public class Trainer {
	public Map<MonsterID, Monster> monsters; // List of this Trainer's Monsters.
	private List<MonsterID> order; // List in order of this Trainer's Monsters.
	public Monster active; // The First Monster, The one in-battle.
	public String name; // Trainer's Name.
	public List<Item> items; // List of this Trainer's items.
	public Decision decision; // The decision made by the AI for this player.
	private DT trainerAI = new DT();

	/**
	 * Default Constructor for the Trainer.
	 * It will receive a Name, a List of its Monsters, a List of Items.
	 * The List of Monsters should contain 6 Monsters.
	 * No rules for the List of Items.
	 * 
	 * The List of Monsters will be shuffled, and the first Monster in the shuffled list,
	 * will become the Active Monster.
	 * 
	 * @param name is the Trainer's Name.
	 * @param monsters is the List of Trainer's Monsters.
	 * @param itens is the List of the Trainer's Items.
	 */
	public Trainer(String name, List<Monster> monsters, List<Item> itens) {
		this.name = name;
		this.items = itens;
		if (monsters.size() == 0) {
			throw new IllegalArgumentException(
					"Trainer can't have no Monsters.");
		}
		Collections.shuffle(monsters);
		this.monsters = new HashMap<>();
		this.order = new ArrayList<>();
		for (Monster m : monsters) {
			this.monsters.put(m.getID(), m);
			this.order.add(m.getID());
		}
		active = monsters.get(0);
	}

	/**
	 * This method will clear the List of Monster, and nullify the Active Monster.
	 * After calling this Method, the Trainer can't make any decision. Unless the Trainer is initialized again.
	 */
	public void clearMonsters() {
		this.monsters.clear();
		this.order.clear();
		this.active = null;
	}

	/**
	 * Add more Monsters to the List of Monsters for the Trainer.
	 * If the List of Monster's for Trainer is 6 or Higher, this method wont do anything.
	 * 
	 * @param m is the Monster to be added.
	 */
	public void addMonster(Monster m) {
		if (this.monsters.size() < 6) {
			this.monsters.put(m.getID(), m);
			this.order.add(m.getID());
			if (this.active == null) {
				this.active = m;
			}
		}
	}

	public List<Monster> listMonsters() {
		List<Monster> list = new ArrayList<>();
		for (MonsterID m : order) {
			list.add(monsters.get(m));
		}
		return list;
	}

	/**
	 * @return the List of Items that this Trainer have.
	 */
	public List<Item> listItems() {
		return items;
	}

	/**
	 * This method will show on the Console, all of this Trainer's Monsters.
	 */
	public void DisplayListOfMonsters() {
		System.out.print(this.name + "\'s team: ");

		for (int i = 0; i < (listMonsters().size()); i++) {
			System.out.print(listMonsters().get(i).getID() + ", ");
		}

		System.out.println("");
	}

	/**
	 * Change the Monster in-battle.
	 *
	 * @param m
	 *            the new Active Monster.
	 */
	public void changeActive(Monster m) {
		if (m.isAlive()) {
			this.active = monsters.get(m.getID());
		} else {
			throw new IllegalArgumentException("Can't select a dead Monsters.");
		}
	}

	/**
	 * This method will make the Trainer make a Decision based on the Battle.
	 * 
	 * @param battle is the Battle that the Trainer is currently at.
	 * @return the Decision that the Trainer should make.
	 */
	public Decision makeDecision(Battle battle) {
		decision = trainerAI.makeDecision(battle, this, 0);
		if (decision == null) {
			if (BattleVariables.printEachTurn) {
				System.out.println("Trainer " + name + " couldn't make a decision...");
			}
		} else {
			if (BattleVariables.printEachTurn) {
				System.out.println("Trainer " + name + "'s decision is "
						+ decision.toString() + ".");
			}
		}
		return decision;
	}

	/**
	 * @return the Active Monster for the Trainer.
	 */
	public Monster getActiveMonster() {
		return active;
	}

	/**
	 * @return the decision this player should make when received the Battle State for the last time.
	 */
	public Decision getDecision() {
		return decision;
	}

	/**
	 * Check if Trainer has any Monsters that are still alive.
	 * @return false if all of the Trainer's Monsters are dead, true if else.
	 */
	public boolean hasAliveMonster() {
		for (Monster m : monsters.values()) {
			if (m.isAlive()) return true;
		}
		return false;
	}

	/**
	 * This method will execute the "Switch Monster" decision.
	 * @param b is the currently Battle the Trainer is in.
	 */
	public void chooseNewMonster(Battle b) {

		List<Monster> choices = new ArrayList<Monster>();

		for (Monster m : monsters.values()) {
			if (m.isAlive()) {
				choices.add(m);
			}
		}

		if (choices.size() == 1) {
			changeActive(choices.get(0));
		} else {
			new Behavior_SwitchToMonsterWithBestAttack().execute(b, this)
			.executeDecision(b, this);
		}
		if (BattleVariables.printEachTurn) {
			System.out.println(name + " sent out " + active.getID().toString()
					+ ".");
		}
	}

	/**
	 * This method will return the sum of the percentage HP for all monster of this Trainer.
	 * 
	 * @return the sum of the percentage HP for all monster of this Trainer.
	 */
	public float percentOfAllMonsters() {
		float percent = 0;
		for (Monster m : monsters.values()) {
			percent += m.getPercentHP();
		}
		return percent;
	}

	// only for testing
	public void testSetCurrentActiveMonster(MonsterID id)
	{
		active = MonsterSet.getMonster(id);
	}

}
