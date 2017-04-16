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

//Switches the current active monster to the monster on the user's
// team with the lowest HP.
public class Behavior_SwitchToMonsterWithLowestHP extends Behavior {
    Decision execute(Battle battle, Trainer user) {

        // Track lowest HP value found and the associated monster
        int lowestHp = Integer.MAX_VALUE;
        Monster lowestHpMon = null;

        // Iterate through monsters
        for (Monster m : user.listMonsters()) {
            int monHp = m.getHP();
            if (monHp > 0 && monHp < lowestHp) {
                lowestHp = monHp;
                lowestHpMon = m;
            }
        }
        // TODO figure out how to handle nulls
        return new Decision.ChangeMonster(lowestHpMon);
    }
}
