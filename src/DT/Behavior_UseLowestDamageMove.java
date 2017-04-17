package DT;



import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;
import moves.MoveSet;

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
