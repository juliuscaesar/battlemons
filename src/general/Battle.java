package general;

import java.util.Random;

import monsters.Monster;
import monsters.MonsterSet;
import trainers.Trainer;

/**
 * Class representing a single instance of a battle. Battles consist of two
 * trainers - two AIs - that battle indefinitely until the "player" AI loses.
 * The "player" loses when they run out of alive Monsters.
 * 
 * At the end of the battle, a fitness value representing the AI's performance
 * is returned.
 *
 */
public class Battle {

    // "our" AI
    public Trainer p1;
    // the enemy AI
    public Trainer p2;

    // A random number generator for team selection
    Random rng;
    // Text output for debugging.
    TextOutput textOutput = new TextOutput();

    public int defeated; // FIXME this does nothing right now

    /**
     * Constructor.
     * 
     * @param p1
     *            The "player" AI
     * @param p2
     *            The "enemy" AI
     */
    public Battle(Trainer p1, Trainer p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.rng = new Random(0); // TODO should this be unique per genetic
        // mutation? or always 0?
        this.defeated = 0;
    }

    /**
     * Runs a simulation of this battle. The battle will run indefinitely, until
     * the player AI loses.
     * 
     * @return A fitness value, representing the performance of p1's AI.
     */
    public float runBattle() {

        // Run until broken out of
        while (true) {

            System.out.println("\n----- BEGIN TURN -----");
            System.out.println(p1.name + "\'s active Battlemon: "
                    + p1.getActiveMonster().getID() + " ("
                    + p1.getActiveMonster().getHP() + "/"
                    + p1.getActiveMonster().getMaxHP() + ")");
            System.out.println(p2.name + "\'s active Battlemon: "
                    + p2.getActiveMonster().getID() + " ("
                    + p2.getActiveMonster().getHP() + "/"
                    + p2.getActiveMonster().getMaxHP() + ")");

            // Make the AI decisions HERE. Neither AI will know what the other
            // has chosen until these decisions are executed
            System.out.println("\n");
            Decision player1decision = p1.makeDecision(this);
            Decision player2decision = p2.makeDecision(this);

            // Determine move order
            Trainer order[] = getTurnOrder();

            System.out.println("\nTurn order: 1) " + order[0].name + ", 2) "
                    + order[1].name);

            // Do the battle turns! If either of them return a false, the battle
            // is over, and we need to break
            if (!takeTurn(order[0], order[1])) break;
            if (!takeTurn(order[1], order[0])) break;
        }

        return calculateFitness(); // TODO fitness
    }

    /**
     * Determines what order the Trainers will take their turns in, based on the
     * Decisions taken and the stats of their active monsters.
     * 
     * @return A two-element array, with one reference to p1 and one reference
     *         to p2, in the order that their turns will be taken.
     */
    public Trainer[] getTurnOrder() {

        // Player 1 isn't using a move, but Player 2 is - Player 1 goes
        // first
        if (!(p1.getDecision() instanceof Decision.UseMove)
                && p2.getDecision() instanceof Decision.UseMove) {
            return new Trainer[] { p1, p2 };
        }
        // Player 2 isn't using a move, but Player 1 is - Player 2 goes
        // first
        else if (!(p2.getDecision() instanceof Decision.UseMove)
                && p1.getDecision() instanceof Decision.UseMove) {
            return new Trainer[] { p2, p1 };
        }
        // Neither player is using a move - just go sequentially, it's
        // entirely arbitrary
        else if (!(p1.getDecision() instanceof Decision.UseMove)
                && !(p2.getDecision() instanceof Decision.UseMove)) {
            return new Trainer[] { p1, p2 };
        }
        // Both players are using moves - so whichever monster has the
        // highest speed moves first
        else {

            // Player 1's monster has the higher speed - they go first
            if (p1.getActiveMonster().getSpeed() > p2.getActiveMonster()
                    .getSpeed()) {
                return new Trainer[] { p1, p2 };
            }
            // Player 2's monster has the higher speed - they go second
            else if (p2.getActiveMonster().getSpeed() > p1.getActiveMonster()
                    .getSpeed()) {
                return new Trainer[] { p2, p1 };
            }
            // It's a tie - flip a coin!
            else {
                return rng.nextBoolean() ? new Trainer[] { p1, p2 }
                        : new Trainer[] { p2, p1 };
            }
        }
    }

    /**
     * Method for one trainer to take their turn, and execute their AI. Covers
     * application of status effects
     * 
     * @param user
     *            The trainer taking their turn.
     * @param opponent
     *            The trainer NOT taking their turn.
     * @return
     */
    public boolean takeTurn(Trainer user, Trainer opponent) {

        System.out.println("\nTrainer " + user.name + " is taking their turn!");

        // Advance status effects
        user.getActiveMonster().updateStats();

        // Player is not using a move
        if (!(user.getDecision() instanceof Decision.UseMove)) {

            user.getDecision().executeDecision(this, user);
            textOutput.printStuffToConsole(user, opponent);

        } else { // Player is using a move

            // If the user can move, take their turn
            if (user.getActiveMonster().canMove()) {

                // Do the thing!
                user.getDecision().executeDecision(this, user);

                // produce output as text
                textOutput.printStuffToConsole(user, opponent);

                // Check monsters to see who died
                if (!checkMonster(user)) {
                    return false;
                }
                if (!checkMonster(opponent)) {
                    return false;
                }

            } else { // The user can't move
                System.out.println(user.name + "'s monster can't move!");
            }
        }

        // Apply damage from status effects, if there is any
        user.getActiveMonster().applyStatusDamage();

        // Check user's monster again to see if they died from status
        // effects
        if (!checkMonster(user)) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether or not the given trainer's monster is alive, and finds a
     * new one if possible. If there are no new monsters, and this is the Enemy
     * AI (p2), then a new team is generated - if this is the PLAYER AI (p1),
     * then the function returns false, and the battle ends.
     * 
     * @param t
     *            The trainer whose team we're checking.
     * @return True if the battle is to continue; false if the battle is to end.
     *         The battle only ends if p1's team has died.
     */
    boolean checkMonster(Trainer t) {

        // Check if the current monster is alive
        if (!t.getActiveMonster().isAlive()) { // If not, try to find a new one

            System.out.println(t.name + "'s "
                    + t.getActiveMonster().getID().toString() + " fainted.");

            // Check for any alive monsters
            if (t.hasAliveMonster()) {
                // If we've found some, choose the best one
                t.chooseNewMonster(this);
            }
            // If we don't have any...
            else {

                // If this is the "enemy" AI, just make up some more monsters
                if (t.equals(p2)) {
                    System.out
                            .println("Player 2 has no more monsters; creating a new team...");
                    generateNewEnemyMonsters();
                    t.chooseNewMonster(this);
                }
                // If this is "good" AI, it's game over
                else {
                    System.out.println("Player 1 has no more monsters...");
                    return false;
                }
            }
        }
        // If the monster's still alive, or we found a new one, NBD
        return true;
    }

    /**
     * Returns the active monster for user's opponent.
     * 
     * @param user
     *            The current Trainer.
     * @return user's opponent's monster.
     */
    public Monster getOpponentsMonster(Trainer user) {
        return p1 == user ? p2.getActiveMonster() : p1.getActiveMonster();
    }

    /**
     * Generates a new team of monsters for p2. Monsters are chosen at random,
     * using this.rng.
     */
    private void generateNewEnemyMonsters() {
        p2.clearMonsters();

        for (int i = 0; i < 6; i++) {
            // TODO this rng needs to be moved to a static space
            p2.addMonster(MonsterSet.getRandomMonster());
        }

        p2.DisplayListOfMonsters();
    }

    /**
     * Calculates the fitness for p1, based on the number of monsters beaten in
     * total - and the remaining HP of the monsters that haven't been defeated
     * yet.
     */
    private float calculateFitness() {
        return this.defeated + this.p2.percentOfAllMonsters();

    }
}
