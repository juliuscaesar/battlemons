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

// Switches to any monster on the user's team whose type is weak
// (defending) against the opponent's type (attacking).
public class Behavior_SwitchToMonsterWithWeakType extends Behavior {
    Decision execute(Battle battle, Trainer user) {

        // Check opponent's type
        Monster opponent = battle.getOpponentsMonster(user);
        Element opposingType = opponent.getElem();

        // If the current matchup is weak, return null
        if (Element.getMatchupValue(user.getActiveMonster().getElem(),
                opposingType) < 1.) {
            return null;
        }

        // Look for a monster on the user's team with a weak matchup
        for (Monster m : user.listMonsters()) {
            if (m.isAlive()
                    && Element.getMatchupValue(m.getElem(),
                            opposingType) < 1.) {
                return new Decision.ChangeMonster(m);
            }
        }
        return null;
    }
}
