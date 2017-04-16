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

public class Behavior_HealPP extends Behavior {
    Decision execute(Battle battle, Trainer user) {

        int lowestPP = 0;
        EtherItem replenishPP = new EtherItem();
        Monster monster = user.getActiveMonster();

        Attack lowestPPAttack = null;

        for (Attack attack : monster.listMoves()) {
            int powerpoints = MoveSet.getMove(attack).getPP();
            if (powerpoints < lowestPP) {
                lowestPP = powerpoints;
                lowestPPAttack = attack;
            }
        }

        if (lowestPPAttack == null) return null;

        return new Decision.UseMove(MoveSet.getMove(lowestPPAttack));
    }
}
