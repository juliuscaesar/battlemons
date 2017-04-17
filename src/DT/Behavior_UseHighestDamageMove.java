package DT;



import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;
import moves.MoveSet;

import trainers.Trainer;

/**
 * Select and use the attack that will inflict the most damage
 */
public class Behavior_UseHighestDamageMove extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
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
