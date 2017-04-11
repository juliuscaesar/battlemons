import general.Battle;
import trainers.Trainer;
import monsters.Monster;
import trainers.Item;
import java.util.*;

/**
 * Runs a Battle
 */
 public class RunBattle {

   public static void main(String[] args) {

     /**
      * Create empty teams and item lists for now
      */
     List<Monster> trainer1team = new ArrayList<Monster>();
     List<Monster> trainer2team = new ArrayList<Monster>();

     List<Item> trainer1items = new ArrayList<Item>();
     List<Item> trainer2items = new ArrayList<Item>();

     // create trainers to run this battle with
     Trainer trainer1 = new Trainer("Caesar", trainer1team, trainer1items);
     Trainer trainer2 = new Trainer("Nishant", trainer2team, trainer2items);

     /**
      * Create this battle instance
      */
     Battle b = new Battle(trainer1, trainer2);

     System.out.println("Beginning the battle");
     // Run the battle and receive the fitness float
     float fitness = b.runBattle();

     System.out.println("Fitness:" + fitness);
   }
 }
