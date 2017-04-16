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
/**
 * Select and use the attack that will inflict the least damage
 */
public class Behavior_UseLowestDamageMove extends Behavior {
    Decision execute(Battle battle, Trainer user) {
        double lowestDmg = 0;
        Monster monster = user.getActiveMonster();
        Monster opponent = battle.getOpponentsMonster(user);
        Damage d = new Damage();
        Attack lowestAttack = null;

        for (Attack attack : monster.listMoves()) {
            double damage = d.lowestPossibleDamage(attack, monster,
                    opponent);
            if (damage < lowestDmg) {
                lowestDmg = damage;
                lowestAttack = attack;
            }
        }
        if (lowestAttack == null) return null;
        return new Decision.UseMove(MoveSet.getMove(lowestAttack));
    }
}
