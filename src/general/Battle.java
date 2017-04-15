package general;

import java.util.Random;

import monsters.MonsterSet;
import trainers.Trainer;

public class Battle {

    Trainer p1; // "our" AI
    Trainer p2; // the base AI
    Random rng;
    TextOutput textOutput;
    int defeated;
    

    public Battle(Trainer p1, Trainer p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.rng = new Random(0); // TODO should this be unique per genetic
                                  // mutation? or always 0?
        this.defeated = 0;
    }

    // returns fitness
    public float runBattle() {

        while (true) {
            p1.makeDecision(this);
            p2.makeDecision(this);

            Trainer order[];

            // Determine move order

            // player 1 goes first automatically - not using a move
            if (!(p1.getDecision() instanceof Decision.UseMove)
                    && p2.getDecision() instanceof Decision.UseMove) {
                order = new Trainer[] { p1, p2 };
            }
            // player 2 goes first automatically - not using a move
            else if (!(p2.getDecision() instanceof Decision.UseMove)
                    && p1.getDecision() instanceof Decision.UseMove) {
                order = new Trainer[] { p2, p1 };
            }
            // neither player is using a move - just do whatever
            else if (!(p1.getDecision() instanceof Decision.UseMove)
                    && !(p2.getDecision() instanceof Decision.UseMove)) {
                order = new Trainer[] { p1, p2 };
            }
            // both players are using moves - follow speed order
            else {
                if (p1.getActiveMonster().getSpeed() > p2.getActiveMonster()
                        .getSpeed()) {
                    order = new Trainer[] { p1, p2 };
                } else if (p2.getActiveMonster().getSpeed() > p1
                        .getActiveMonster().getSpeed()) {
                    order = new Trainer[] { p2, p1 };
                } else { // It's a tie - flip a coin
                    if (rng.nextBoolean()) {
                        order = new Trainer[] { p1, p2 };
                    } else {
                        order = new Trainer[] { p2, p1 };
                    }
                }
            }

            // Do the battle turns
            if (!takeTurn(order[0], order[1])) break;
            if (!takeTurn(order[1], order[0])) break;

        }

        return calculateFitness(); // TODO fitness
    }

    /**
     * Take Turn method.
     * 
     * @param user
     * @param opponent
     * @return
     */
    boolean takeTurn(Trainer user, Trainer opponent) {

        // Check status effects
        user.getActiveMonster().updateStats();
        if(user.getActiveMonster().canMove()){
        	return true;
        }

        // display some initial information to console
       user.DisplayListOfMonsters();
       opponent.DisplayListOfMonsters();
        
        // Do the thing!
        user.getDecision().executeDecision(this, user);        
        // produce output as text
        textOutput.printStuffToConsole(user, opponent);

        // Check monsters to see who died
        if (!checkMonster(user)) 
        {
        	System.out.println("No monsters left for " + user);
        	return false;
        	
        }
        
        if (!checkMonster(opponent)) 
        {
        	System.out.println("No monsters left for " + opponent);
        	return false;
        	
        }
        
        user.getActiveMonster().applyStatusDamage();
        System.out.println("The current status of the user's monster is" + user.getActiveMonster().getStatus());

        // Check user's monster again to see if they died from status effects
        if (!checkMonster(user)) 
        {
        	System.out.println("No monsters left for " + user);
        	return false;
        	
        }

        return true;
    }

    // Checks whether or not the given trainer's monster is alive, and finds a
    // new one if possible. If the monster is still alive, or a new one is
    // swapped to, this returns true; otherwise, it returns false.
    boolean checkMonster(Trainer t) {

        // Check if the current monster is alive
        if (!t.getActiveMonster().isAlive()) { // If not, try to find a new one

            // Check for any alive monsters
            if (t.hasAliveMonster()) {

                // If we've found some, choose the best one
                t.chooseNewMonster();

            } else { // If we don't have any...

                // If this is the "enemy" AI, just make up some more monsters
                if (t.equals(p2)) {

                    generateNewEnemyMonsters();
                    t.chooseNewMonster();

                } else { // If this is "good" AI, it's game over

                    return false;

                }
            }
        }

        // If the monster's still alive, or we found a new one, NBD
        return true;
    }

    private void generateNewEnemyMonsters() {
    	p2.clearMonsters();
    	
    	System.out.println("Generating new set of monsters for the opponent");
    	
    	for(int i = 0; i < 6; i++){    		    		
    		p2.addMonster(MonsterSet.getRandomMonster());
    	}
    }
    
    private float calculateFitness(){
    	return this.defeated + this.p2.percentOfAllMonsters();
    	
    }
}
