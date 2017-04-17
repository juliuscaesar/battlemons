package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import moves.MoveSet;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

// Returns true if the user's active monster has at least one move with
// no PP left.
public class Condition_IfSomeMoveHasNoPP extends Condition {

    // Constructor
    public Condition_IfSomeMoveHasNoPP() {
        super();
        uses_parameter = false;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        for (Attack a : user.getActiveMonster().listMoves()) {
            if (MoveSet.getMove(a).getPP() == 0) {
                return true;
            }
        }
        return false;
    }

}
