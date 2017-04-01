package general;

import trainers.Item;
import trainers.Trainer;
import monsters.Monster;
import moves.Move;

public abstract class Decision {
    
    abstract void executeDecision(Battle b, Trainer user);

    class ChangeMonster extends Decision {
        
        Monster new_monster;
        
        void executeDecision(Battle b, Trainer user) {
            user.changeActive(new_monster);
        }
        
    }
    
    class UseItem extends Decision {
        
        Item item_to_use;
        
        void executeDecision(Battle b, Trainer user) {
            // TODO
        }
    }
    
    class UseMove extends Decision {
        
        Move move_to_use; 
        
        void executeDecision(Battle b, Trainer user) {
            // TODO
            //user.getActiveMonster().
        }
        
    }
}
