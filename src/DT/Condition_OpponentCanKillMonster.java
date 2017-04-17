package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import trainers.Trainer;


// Returns true if the opponent's current monster has a move that can
// kill the user's current monster on the next turn.
public class Condition_OpponentCanKillMonster extends Condition {

    // Constructor
    public Condition_OpponentCanKillMonster() {
        super();
        uses_parameter = false;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster opponent = battle.getOpponentsMonster(user);
        Damage d = new Damage();

        // Iterate through opponent's moves
        for (Attack a : opponent.listMoves()) {

            // Check possible damage dealt by this move; if it exceeds
            // the current monster's health, it's dead
            double possibleDamage = d.highestPossibleDamage(a,
                    opponent, user.getActiveMonster());
            if (possibleDamage > user.getActiveMonster().getHP()) {
                return true;
            }
        }
        return false;
    }
}
