package general;

import trainers.Item;
import trainers.Trainer;
import monsters.Monster;
import moves.Move;

/**
 * Genetic Algorithm that performs mutations on the
 * decision tree
 * 
 */
public abstract interface GeneticAlgorithm {
    /**
     * abstract method that executes mutation
     * @param b The battle
     * @param user The user
     */
    abstract void executeMutation(Battle b, Trainer user);
    
    /**
     * Mutate the parameters of a node
     * 
     */
    public class MutateParameters implements GeneticAlgorithm {
        
        Monster new_monster;
        
        public MutateParameters(Monster new_monster) {
            this.new_monster = new_monster;
        }
        
        public void executeMutation(Battle b, Trainer user) {
            user.changeActive(new_monster);
        }
        
    }
    
    /**
     * Remove a node from the decision tree
     * 
     */
    public class RemoveNode implements GeneticAlgorithm {
        
        Item item_to_use;
        
        public void executeMutation(Battle b, Trainer user) {
            // TODO
        }
    }
    
    /**
     * Adds a node to the decision tree
     * 
     */
    public class AddNode implements GeneticAlgorithm {
        
        Move move_to_use; 
        
        public AddNode(Move move) {
        	this.move_to_use = move;
        }
        
        public void executeMutation(Battle b, Trainer user) {
            // TODO
            //user.getActiveMonster().
        }
        
    }
    
    /**
     * Swaps nodes in a decision tree
     * 
     */
    public class SwapNodes implements GeneticAlgorithm {
        
        Move move_to_use; 
        
        public SwapNodes(Move move) {
        	this.move_to_use = move;
        }
        
        public void executeMutation(Battle b, Trainer user) {
            // TODO
            //user.getActiveMonster().
        }
        
    }
}
