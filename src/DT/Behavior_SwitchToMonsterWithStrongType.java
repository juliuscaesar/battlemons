package DT;

import general.Battle;
import general.Decision;
import general.Element;

import monsters.Monster;

import trainers.Trainer;

// Switches to any monster on the user's team whose type is strong
// (defending) against the opponent's type (attacking).
public class Behavior_SwitchToMonsterWithStrongType extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

        // Check opponent's type
        Monster opponent = battle.getOpponentsMonster(user);
        Element opposingType = opponent.getElem();

        // If the current matchup is strong, return null
        if (Element.getMatchupValue(user.getActiveMonster().getElem(),
                opposingType) > 1.) {
            return null;
        }

        // Look for a monster on the user's team with a strong matchup
        for (Monster m : user.listMonsters()) {

            if (!m.isAlive()) continue;

            if (Element.getMatchupValue(m.getElem(), opposingType) > 1.) {
                return new Decision.ChangeMonster(m);
            }
        }

        // if nothing's found, return null
        return null;
    }
}
