package DT;

import general.Battle;
import general.Decision;

import monsters.Monster;

import trainers.Trainer;

// Switches the current active monster to the monster on the user's team
// with the highest HP.
public class Behavior_SwitchToMonsterWithHighestHP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        int highestHp = 0;
        Monster highestHpMon = null;
        for (Monster m : user.listMonsters()) {

            if (!m.isAlive() || m == user.getActiveMonster()) continue;

            int monHp = m.getHP();
            if (monHp > 0 && monHp > highestHp) {
                highestHp = monHp;
                highestHpMon = m;
            }
        }
        // TODO figure out how to handle nulls
        if (highestHpMon == null) {
            return null;
        }
        return new Decision.ChangeMonster(highestHpMon);
    }
}
