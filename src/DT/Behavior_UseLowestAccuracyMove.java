package DT;

import java.util.List;

import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Element;
import general.Status;
import monsters.Monster;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
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
