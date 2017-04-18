package DT;

import general.Battle;
import general.Decision;

import monsters.Monster;

import trainers.Trainer;

//Switches the current active monster to the monster on the user's
// team with the lowest HP.
public class Behavior_SwitchToMonsterWithLowestHP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

        // Track lowest HP value found and the associated monster
        int lowestHp = Integer.MAX_VALUE;
        Monster lowestHpMon = null;

        // Iterate through monsters
        for (Monster m : user.listMonsters()) {
            int monHp = m.getHP();
            if (!m.isAlive() || m == user.getActiveMonster()) continue;
            if (monHp > 0 && monHp < lowestHp) {
                lowestHp = monHp;
                lowestHpMon = m;
            }
        }
        if (lowestHpMon != null) {
            return new Decision.ChangeMonster(lowestHpMon);
        } else
            return null;
    }
}
