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

public class Behavior_InflictStatusEffect extends Behavior {
    Decision execute(Battle battle, Trainer user) {

        Monster monster = user.getActiveMonster();

        for (Attack attack : monster.listMoves()) {
            if (MoveSet.getMove(attack).isStatusMove()) {
                return new Decision.UseMove(MoveSet.getMove(attack));
            }

        }
        return null;

    }
}
