package DT;

import java.util.List;

import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Element;
import general.Status;
import monsters.Monster;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
import trainers.Trainer;

/**
 * An abstract representation of a Behavior (or Leaf) in this tree.
 * Implementing subclasses will determine what the actual behavior of this
 * node is.
 */
abstract class Behavior {

	// Constructor.
	Behavior() {
	}
	
	abstract Decision execute(Battle battle, Trainer user);

	public void setParam(double newParam) {

	}
}