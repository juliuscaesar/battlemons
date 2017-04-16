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
 * Select and use the attack that will inflict the most damage
 */
public class Behavior_UseHighestDamageMove extends Behavior {
    Decision execute(Battle battle, Trainer user) {
        double highestDmg = 0;
        Monster monster = user.getActiveMonster();
        Monster opponent = battle.getOpponentsMonster(user);
        Damage d = new Damage();
        Attack highestAttack = null;

        for (Attack attack : monster.listMoves()) {
            double damage = d.highestPossibleDamage(attack, monster,
                    opponent);
            if (damage > highestDmg) {
                highestDmg = damage;
                highestAttack = attack;
            }
        }
        if (highestAttack == null) return null;
        return new Decision.UseMove(MoveSet.getMove(highestAttack));
    }
}
