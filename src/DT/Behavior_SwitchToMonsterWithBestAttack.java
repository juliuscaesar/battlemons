package DT;



import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;

import trainers.Trainer;

// Switches to the monster on the user's team with the strongest attack
// (most damage dealt) to the opponent.
public class Behavior_SwitchToMonsterWithBestAttack extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

        // Track highest damage and associated monster
        double highestDmg = 0;
        Monster highestDmgMon = null;

        Monster opponent = battle.getOpponentsMonster(user);
        Damage d = new Damage();

        // Iterate through user's monsters
        for (Monster m : user.listMonsters()) {

            if (!m.isAlive()) continue;

            // Iterate through monster's moves
            for (Attack a : m.listMoves()) {

                // Look for high damage moves
                double potentialDmg = d.highestPossibleDamage(a, m,
                        opponent);
                if (potentialDmg > highestDmg) {
                    highestDmg = potentialDmg;
                    highestDmgMon = m;
                }
            }
        }

        // If nothing was found, return null
        if (highestDmgMon == null) return null;
 
        return new Decision.ChangeMonster(highestDmgMon);
    }
}
