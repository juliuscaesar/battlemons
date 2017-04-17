package DT;


import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;
import moves.MoveSet;

import trainers.Trainer;

public class Behavior_UseLowestAccuracyMove extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        int lowestAcc = 0;
        Monster monster = user.getActiveMonster();

        Attack lowestAttack = null;

        for (Attack attack : monster.listMoves()) {
            int accuracy = MoveSet.getMove(attack).getAcc();
            if (accuracy < lowestAcc) {
                lowestAcc = accuracy;
                lowestAttack = attack;
            }
        }
        if (lowestAttack == null) return null;
        return new Decision.UseMove(MoveSet.getMove(lowestAttack));
    }
}
