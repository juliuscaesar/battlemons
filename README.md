# Battlemons


Our goal with Battlemons is to explore how a Pokémon-like game can be 1) implemented with a simple Decision Tree algorithm and 2) how the performance of the “player” AI Decision Tree can be changed with various methods of genetic mutation.

To do this we will create roughly 30 Battlemons with an associated type (we will be basing our types - as well as moves, etc on those features in in the original Pokémon Generation 1 games). Our player will be given a team of 6 battlemons and will go up against teams continuously generated until the player’s team loses.


The Battlemons project is organized in packages back on functionality. They include:
damage: where our damage calculations are stored and referenced.
DT: where all of our Decision Tree, conditions, and behaviors are stored.
Monsters: which includes the data for all of our Battlemons.
Moves: which includes the data for all of the moves in the game
Trainers: which stores trainer user information and items.
General: includes primitive enums for moves, battlemons, and status inflictions. More importantly contains the Battle.java class where the battle is ran and BattleVariables.java which stores variables used throughout the execution such as number of battles per set, maximum number of mutations per set, and Decision Tree generation settings.
default package: Stores the RunBattle.java file which is the main runner wrapper that also controls mutation and executes sets of battles.

### Tools and libraries used?
We used Java. No libraries were used outside of the standard Java util libraries.

### Videos (url of video) for the demo of your project
https://www.youtube.com/watch?v=R3Qoss_IrZY 

### Execution Instructions
Simply run the RunBattle.java class in the default package to run the project. If you’d like to modify your run parameters such as how many battles are ran per set, mutations per set, etc., you may do so in general/BattleVariables.java
If you’d like to turn on the every turn printing to see how the battle functionality works, it is strongly recommended you limit the program to a small number of battles (only 1 recommended) and only one set. By doing so, you can watch the two player clients make decisions and take turns.

### Discuss what you learned through the project
We certainly furthered our understanding of both decision trees and genetic mutation algorithms. One takeaway was the unexpected influence of random number generators in the program. On testing without mutation, before everything was standardized and random number generation was controlled, there was a huge potential difference between our lowest and highest fitness values.

Another significant takeaway is how hard it is to improve the fitness of a Pokémon battle just by mutating the decision tree. Imagine this scenario: A trainer goes against the Elite 4 and doesn't revive Pokémon in between battles. The only way they get through successfully is if they have an advantage over their opponents - whether their Pokémon are higher level, have been specifically trained to acquire higher stats, or if their team has been specifically tailored to the Pokémon that they know they’ll face. Differing strategies in how they make battle choices are certainly influential, but they can only get you so far. In this case, if you get through two teams (the equivalent of a fitness function value of 12 in our algorithm, roughly the equivalent of the average highest fitness value we have seen) of more or less equal stats and moves, that's actually a really good fitness already! Out of curiosity, we experimented with changing stats - and even a 20% increase in attack stats returned a fitness of 40 once.

Our results were further influenced by the fact that our starting decision tree - the base that we iterated on to achieve our results - was already extremely effective. It was based on the “winning strategy” used by a competitive Pokémon player, and what we found was… that it was hard to actually improve on it. Starting with a very well performing decision tree and mutating it only created decision trees that were either just as effective as the original - or worse. This doesn’t mean that there’s no merit to the idea - but that an effective genetic algorithm would have to encompass more than the turn-to-turn decisions (potentially reaching back to the original Pokémon selection process).
In conclusion - our results, on the whole, said more about the effectiveness of our original decision tree than they did our genetic algorithm.

### Selected Mutation Results
Example 1: successful mutation resulting in a higher fitness function value (rare)
Battles per cycle: 3
	Max # of mutations per cycle: 5
	-------------------------------------------------------
	Done
	Without mutation the range was: 5.5333333 to 5.5583334
	Mutation results in a range from: 5.182857 to 11.0

Example 2: Beginning above average performing decision tree which mutating only decreases the performance of (most common)
	Battles per cycle: 1000
	Max # of mutations per cycle: 10
	-------------------------------------------------------
	Done
	Without mutation the range was: 11.0 to 11.0
	Mutation results in a range from: 5.1 to 11.0







# Selected Decision Trees and Results
Example 1: The standard Decision Tree first used by the player and also consistently used by the opponent
True [0]: Condition HealthGreaterThanPercent with parameter 0.2
  True [3]: Behavior - UseHighestDamageMove
  False [1]: Condition UserHasHealItem
    True [4]: Behavior - HealHP
    False [2]: Condition OpponentCanKillMonster
      True [5]: Behavior - SwitchToMonsterWithBestAttack
      False [3]: Behavior - UseHighestDamageMove

Highest fitness after 100 battles: 11.0

---------------------------------------------------------------------------------------------------------
Example 2: A resulting Decision Tree of the previous one after 9 sets of mutations
True [0]: Condition CanStatusHealItself
  True [3]: Behavior - UseHighestDamageMove
  False [1]: Condition IsOpponentStatusNotNormal
    True [4]: Behavior - UseLowestAccuracyMove
    False [2]: Condition IsOpponentStatusNotNormal
      True [5]: Behavior - SwitchToMonsterWithLowestHP
      False [3]: Behavior - UseHighestDamageMove


Highest fitness after 100 battles: 10.496296
---------------------------------------------------------------------------------------------------------
Example 3: A resulting Decision Tree of the original one after 17 sets of mutations
True [0]: Condition IfSomeMoveHasNoPP
  True [3]: Behavior - UseHighestDamageMove
  False [1]: Condition HealthGreaterThanValue with parameter 50.0
    True [4]: Behavior - UseLowestDamageMove
    False [2]: Condition CanStatusHealItself
      True [5]: Behavior - SwitchToMonsterWithBestAttack
      False [3]: Behavior - UseHighestDamageMove

Highest fitness after 100 battles: 11.0
---------------------------------------------------------------------------------------------------------
Example 4: A simple randomly generated Decision Tree
True [0]: Condition HealthGreaterThanPercent with parameter 0.2
  True [37]: Behavior - SwitchToMonsterWithWeakType
  False [36]: Behavior - UseHighestAccuracyMove

Highest fitness after 100 battles: 5.4914284



