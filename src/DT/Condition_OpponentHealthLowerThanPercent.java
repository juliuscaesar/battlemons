package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

/**
 * Check if the opponent's health is lower than x%
 */
public class Condition_OpponentHealthLowerThanPercent extends Condition {

    // Constructor
    public Condition_OpponentHealthLowerThanPercent(double parameter) {
        super(parameter);
        upper_bound = 1.0;
        lower_bound = 0.0;
        uses_parameter = true;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = battle.p2.getActiveMonster();
        return currentMon.getPercentHP() <= parameter;
    }
}
