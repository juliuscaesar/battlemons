package DT;


import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;
import moves.MoveSet;

import trainers.Trainer;

public class Behavior_UseHighestAccuracyMove extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        int highestAcc = 0;
        Monster monster = user.getActiveMonster();

        Attack highestAttack = null;

        for (Attack attack : monster.listMoves()) {
            int accuracy = MoveSet.getMove(attack).getAcc();
            if (accuracy > highestAcc) {
                highestAcc = accuracy;
                highestAttack = attack;
            }
        }
        if (highestAttack == null) return null;
        return new Decision.UseMove(MoveSet.getMove(highestAttack));
    }
}
