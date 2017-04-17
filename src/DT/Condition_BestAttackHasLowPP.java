package DT;

import general.Battle;

import damage.Damage;
import general.Attack;
import moves.MoveSet;
import trainers.Trainer;


// Returns true if the user's active monster's strongest attack (the
// attack that will deal the most damage to the opponent) has a
// percentage of PP lower than parameter.
public class Condition_BestAttackHasLowPP extends Condition {

    // Constructor
    public Condition_BestAttackHasLowPP(double parameter) {
        super(parameter);
        uses_parameter = true;
        upper_bound = 1.0;
        lower_bound = 0.0;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Damage d = new Damage();

        // Get the strongest move and check how much PP is left as a
        // percentage of max PP
        Attack strongestMove = d.getStrongestMove(user
                .getActiveMonster().listMoves(), user
                .getActiveMonster(), battle.getOpponentsMonster(user));
        float percentPP = MoveSet.getMove(strongestMove).getPP()
                / MoveSet.getMove(strongestMove).getMaxPP();

        return percentPP < parameter;
    }
}
