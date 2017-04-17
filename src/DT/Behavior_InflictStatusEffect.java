package DT;


import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;
import moves.MoveSet;
import trainers.Trainer;

public class Behavior_InflictStatusEffect extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

        Monster monster = user.getActiveMonster();

        for (Attack attack : monster.listMoves()) {
            if (MoveSet.getMove(attack).isStatusMove()) {
                return new Decision.UseMove(MoveSet.getMove(attack));
            }

        }
        return null;

    }
}
