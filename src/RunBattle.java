import general.Battle;
import general.BattleVariables;
import trainers.Trainer;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.Item;
import java.util.*;

/**
 * Runs a Battle and prints the resulting fitness float
 */
public class RunBattle {
	
	

	public static void main(String[] args) {

		/**
		 * Create empty teams and item lists for now
		 */
		List<Monster> trainer1team = 
				new ArrayList<Monster>(
						Arrays.asList(MonsterSet.getRandomMonster(),
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(),
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster()));
		List<Monster> trainer2team = 
				new ArrayList<Monster>(
						Arrays.asList(MonsterSet.getRandomMonster(),
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster()));


		List<Item> trainer1items = new ArrayList<Item>();
		List<Item> trainer2items = new ArrayList<Item>();

		// create trainers to run this battle with
		Trainer trainer1 = new Trainer("Caesar", trainer1team, trainer1items);
		Trainer trainer2 = new Trainer("Nishant", trainer2team, trainer2items);

		/**
		 * Create this battle instance
		 */
		Battle b = new Battle(trainer1, trainer2);
		
		if (BattleVariables.printBattleSummary && !BattleVariables.justFitness) {
			System.out.println("----- BEGINNING THE BATTLE -----");
			// display some initial information to console
			trainer1.DisplayListOfMonsters();
			trainer2.DisplayListOfMonsters();
		}
		// Run the battle and receive the fitness float
		float fitness = b.runBattle();
		
		if (BattleVariables.printBattleSummary) {
			System.out.println("Fitness: " + fitness);
		}
	}
}
