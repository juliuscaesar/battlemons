import general.Battle;
import general.BattleVariables;
import general.MonsterID;
import trainers.Trainer;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.Item;
import DT.DT;
import DT.GeneticAlgorithm;

import java.util.*;

/**
 * Runs a Battle and prints the resulting fitness float
 */
public class RunBattle {
	static float bestFitness = 0;
	static float worstFitness = 100;
	static DT bestTrainerDT = RunBattle.makeDT();

	/**
	 * Execute a set
	 * @param int n The number of this set
	 */
	private static void executeSet(int n) {

		// Contains results of every battle run during this iteration
		HashMap<Integer, Float> fitnessHistory = new HashMap<Integer, Float>();
		HashMap<Integer, DT> DTHistory = new HashMap<Integer, DT>();

		if (BattleVariables.justFitness) {
			System.out.println("Running Set #" + n);
		}

		// Run battles over and over and over again
        int battleNum = 0;
		while (battleNum < BattleVariables.battlesPerCycle) {
		    
		    // Setup our starting teams and items
			List<Monster> trainer1team = 
					new ArrayList<Monster>(
							Arrays.asList(MonsterSet.getMonster(MonsterID.Adnocana),
									MonsterSet.getMonster(MonsterID.Armordillo), 
									MonsterSet.getMonster(MonsterID.Boomtu), 
									MonsterSet.getMonster(MonsterID.Bulblight),
									MonsterSet.getMonster(MonsterID.Carrotay), 
									MonsterSet.getMonster(MonsterID.Emberfly)));
			List<Monster> trainer2team = 
					new ArrayList<Monster>(
							Arrays.asList(MonsterSet.getMonster(MonsterID.Adnocana),
									MonsterSet.getMonster(MonsterID.Armordillo), 
									MonsterSet.getMonster(MonsterID.Boomtu), 
									MonsterSet.getMonster(MonsterID.Bulblight),
									MonsterSet.getMonster(MonsterID.Carrotay), 
									MonsterSet.getMonster(MonsterID.Emberfly)));

			List<Item> trainer1items = new ArrayList<Item>();
			List<Item> trainer2items = new ArrayList<Item>();

			DT playerDT = new DT(bestTrainerDT);
			
			// Copy and mutate our decision tree
			int performedMutations = 0;
			while (performedMutations < BattleVariables.maxMutationsPerCycle) {
                GeneticAlgorithm ga = new GeneticAlgorithm();
                playerDT = ga.mutate(playerDT);                    
                performedMutations++;
			}

			// Create our trainers
			Trainer trainer1 = new Trainer("Caesar", trainer1team, trainer1items, playerDT);
			Trainer trainer2 = new Trainer("Nishant", trainer2team, trainer2items);

			try {
			    // Build the battle and print our header
				Battle b = new Battle(trainer1, trainer2);
				if (BattleVariables.printBattleSummary && !BattleVariables.justFitness) {
					System.out.println("----- BEGINNING THE BATTLE -----");
				}

				// Run the battle and receive the fitness float
				float fitness = b.runBattle();

				// Save our fitness and our decision tree
				fitnessHistory.put(battleNum, fitness);
				DTHistory.put(battleNum, playerDT);

				if (BattleVariables.printBattleSummary && !BattleVariables.justFitness) {
					System.out.println("Fitness: " + fitness);
				}
			} catch(StackOverflowError e) {
			    System.out.println(e.toString());
				continue;
			}
			
			battleNum++;
		}
		
		if (BattleVariables.printDTs) {
			System.out.println("Current best DT:");
			System.out.println(bestTrainerDT.printTree());
		}
		
		// Update our fitness and save our result for the next cycle
		if (BattleVariables.justFitness) {
			RunBattle.getWorst(fitnessHistory, DTHistory);
			// TODO make sure to take this outside if because it wont work
			// if justfitness isnt true
			RunBattle.bestTrainerDT = new DT(RunBattle.getBest(fitnessHistory, DTHistory));


			System.out.println("-------------------------------------------------------");
		}
	}

	private static DT makeDT() {
		if (BattleVariables.randomDT) {
			DT tree = new DT(true);
			System.out.println(tree.printTree());
			return new DT(true);
		}
		else {
			new DT().printTree();
			return new DT();
		}
	}

	/**
	 * Returns the worst Trainer from a set
	 */
	public static DT getWorst(HashMap<Integer, Float> map, HashMap<Integer, DT> DTHistory) {
		Map.Entry<Integer, Float> minEntry = null;

		for (Map.Entry<Integer, Float> entry : map.entrySet())
		{
			if (minEntry == null || entry.getKey().compareTo(minEntry.getKey()) < 0)
			{
				minEntry = entry;
			}
		}

		if (BattleVariables.justFitness) {
			System.out.println("Lowest fitness: " + minEntry.getValue());
			if (minEntry.getValue() < RunBattle.worstFitness) {
				RunBattle.worstFitness = minEntry.getValue();
			}
			//System.out.println("Worst team: " + 
			//trainerHistory.get(minEntry.getKey()).listMonsters().toString());
		}
		return DTHistory.get(minEntry.getKey());
	}

	/**
	 * Returns the best Trainer from a set
	 */
	public static DT getBest(HashMap<Integer, Float> map,  HashMap<Integer, DT> DTHistory) {
		Map.Entry<Integer, Float> maxEntry = null;

		for (Map.Entry<Integer, Float> entry : map.entrySet())
		{
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			{
				maxEntry = entry;
			}
		}


		if (BattleVariables.justFitness) {
			if (maxEntry.getValue() > RunBattle.bestFitness) {
				RunBattle.bestFitness = maxEntry.getValue();
			}
			System.out.println("Highest fitness: " + maxEntry.getValue());
			System.out.println("Best fitness so far: " + RunBattle.bestFitness);
			//System.out.println("Best team: " + 
			//trainerHistory.get(maxEntry.getKey()).listMonsters().toString());
		}
		return DTHistory.get(maxEntry.getKey());
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(",--.           ,--.   ,--. ,--.                                   ");
		System.out.println("|  |-. ,--,--,-'  '-,-'  '-|  |,---.,--,--,--.,---.,--,--, ,---.  ");
		System.out.println("| .-. ' ,-.  '-.  .-'-.  .-|  | .-. |        | .-. |      (  .-'  ");
		System.out.println("| `-' \\ '-'  | |  |   |  | |  \\   --|  |  |  ' '-' |  ||  .-'  `) ");
		System.out.println("`---' `--`--' `--'   `--' `--'`----`--`--`--'`---'`--''--`----'  ");
		System.out.println("");
		System.out.println("");
		System.out.println("Battles per cycle: " + BattleVariables.battlesPerCycle);
		System.out.println("Max # of mutations per cycle: " + BattleVariables.maxMutationsPerCycle);
		System.out.println("");
		System.out.println("BEGINNING SETS....");
		System.out.println("");
		System.out.println("-------------------------------------------------------");


		int n = 1;
		while (n <= BattleVariables.numberOfSets) {
			RunBattle.executeSet(n);
			n++;
		}

		System.out.println("Done");
		System.out.println("Mutation results in a range from: " + worstFitness + " to " + bestFitness);
	}
}
