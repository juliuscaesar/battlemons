package general;

import trainers.Item;
import trainers.Trainer;
import monsters.Monster;
import moves.Move;

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
    
    public class UseItem implements Decision {
        
        Item item_to_use;
        
        public void executeDecision(Battle b, Trainer user) {
            // TODO
        }
    }
    
    public class UseMove implements Decision {
        
        Move move_to_use; 
        
        public void executeDecision(Battle b, Trainer user) {
            // TODO
            //user.getActiveMonster().
        }
        
    }
}
