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
	static DT bestTrainerDT = RunBattle.makeDT();

	/**
	 * Execute a set
	 * @param int n The number of this set
	 */
	private static void executeSet(int n) {
		int indexOfBest = 0;
		float lowestFitness;
		float highestFitness;
		int performedMutations = 0;

		HashMap<Integer, Float> fitnessHistory = new HashMap<Integer, Float>();
		HashMap<Integer, DT> DTHistory = new HashMap<Integer, DT>();

		if (BattleVariables.justFitness) {
			System.out.println("Running Set #" + n);
		}

		int counter = 0;
		while (counter < BattleVariables.battlesPerCycle) {
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

			DT newDT;
			/**
			 * Mutate the decision tree first and create the trainer
			 */
			if (performedMutations < BattleVariables.maxMutationsPerCycle) {
				GeneticAlgorithm ga = new GeneticAlgorithm();
				newDT = ga.mutate(bestTrainerDT);					
				performedMutations++;
			}
			else {
				newDT = RunBattle.makeDT();
			}

			// create opponent to run this battle with
			Trainer trainer1 = new Trainer("Caesar", trainer1team, trainer1items, newDT);
			Trainer trainer2 = new Trainer("Nishant", trainer2team, trainer2items);

			try {
				Battle b = new Battle(trainer1, trainer2);
				if (BattleVariables.printBattleSummary && !BattleVariables.justFitness) {
					System.out.println("----- BEGINNING THE BATTLE -----");
				}

				// Run the battle and receive the fitness float
				float fitness = b.runBattle();

				fitnessHistory.put(counter, fitness);
				DTHistory.put(counter, new DT(b.p1.getDT()));

				if (BattleVariables.printBattleSummary && !BattleVariables.justFitness) {

					System.out.println("Fitness: " + fitness);
				}
			} catch(StackOverflowError e) {
				continue;

			}
			counter++;
		}

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
	}
}
