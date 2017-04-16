package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

/**
 * Check if the opponent's health is lower than x
 */
public class Condition_OpponentHealthLowerThanValue extends Condition {

    // Constructor
    public Condition_OpponentHealthLowerThanValue(float parameter) {
        super(parameter);
        upper_bound = Double.MAX_VALUE;
        lower_bound = 0.0;
        uses_parameter = true;
    }

    boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = battle.p2.getActiveMonster();
        return currentMon.getHP() <= parameter;
    }
}
