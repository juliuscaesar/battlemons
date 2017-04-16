package general;

import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.Item;
import trainers.ParalyzHealItem;
import trainers.Trainer;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;

public abstract interface Decision {

	abstract void executeDecision(Battle b, Trainer user);

	public class ChangeMonster implements Decision {

		Monster new_monster;

		public ChangeMonster(Monster new_monster) {
			this.new_monster = new_monster;
		}

		public void executeDecision(Battle b, Trainer user) {
			user.changeActive(new_monster);
		}

	}

	public class UseIceHealItem implements Decision {

		IceHealItem item_to_use;

		public UseIceHealItem(IceHealItem cureFreeze, Monster monster)
		{
			this.item_to_use = cureFreeze;

		}

		public void executeDecision(Battle b, Trainer user) {
			item_to_use.useItem(user.getActiveMonster());
		}
	}

	public class UsePoisonHealItem implements Decision {

		Antidote item_to_use;

		public UsePoisonHealItem(Antidote curePoison, Monster monster)
		{
			this.item_to_use = curePoison;

		}

		public void executeDecision(Battle b, Trainer user) {
			item_to_use.useItem(user.getActiveMonster());
		}
	}

	public class UseSleepHealItem implements Decision {

		AwakeningItem item_to_use;

		public UseSleepHealItem(AwakeningItem cureSleep, Monster monster)
		{
			this.item_to_use = cureSleep;

		}

		public void executeDecision(Battle b, Trainer user) {
			item_to_use.useItem(user.getActiveMonster());
		}
	}

	public class UseBurnHealItem implements Decision {

		BurnHealItem item_to_use;

		public UseBurnHealItem(BurnHealItem cureBurn, Monster monster)
		{
			this.item_to_use = cureBurn;

		}

		public void executeDecision(Battle b, Trainer user) {
			item_to_use.useItem(user.getActiveMonster());
		}
	}

	public class UseParalyzHealItem implements Decision {

		ParalyzHealItem item_to_use;

		public UseParalyzHealItem(ParalyzHealItem cureParalysis, Monster monster)
		{
			this.item_to_use = cureParalysis;

		}

		public void executeDecision(Battle b, Trainer user) {
			item_to_use.useItem(user.getActiveMonster());
		}
	}

	public class UseHealHPItem implements Decision {

		FreshwaterItem item_to_use;

		public UseHealHPItem(FreshwaterItem replenishHP, Monster monster)
		{
			this.item_to_use = replenishHP;

		}

		public void executeDecision(Battle b, Trainer user) {
			item_to_use.useItem(user.getActiveMonster());
		}
	}

	public class UseHealPPItem implements Decision {

		EtherItem item_to_use;
		Move move_to_replenish;

		public UseHealPPItem(EtherItem replenishPP, Move move)
		{
			this.item_to_use = replenishPP;
			this.move_to_replenish = move;
		}

		public void executeDecision(Battle b, Trainer user) 
		{   	
			item_to_use.useItemOnMove(user.getActiveMonster(), move_to_replenish);   	
		}
	}


	public class UseAttack implements Decision
	{

		Attack attack_to_use;

		public UseAttack(Attack attack)
		{

			this.attack_to_use = attack;
		}

		public void executeDecision(Battle b, Trainer user) 
		{
			user.getActiveMonster().useMove(attack_to_use);
		}
	}

	public class UseMove implements Decision {

		Move move_to_use;
		// Attack attack_to_use;

		public UseMove(Move move) {
			this.move_to_use = move;

		}

		public void executeDecision(Battle b, Trainer user) {
			System.out.println(user.name + "\'s " + user.getActiveMonster().getID() + " used " + move_to_use.toAttack());
			user.getActiveMonster().useMove(move_to_use.toAttack());
		}

	}
}
