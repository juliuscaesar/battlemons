package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

// Returns true if the user's current monster's health is at
// least the parameter.
public class Condition_HealthGreaterThanValue extends Condition {

    // Constructor
    public Condition_HealthGreaterThanValue(double parameter) {
        super(parameter);
        upper_bound = Double.MAX_VALUE;
        lower_bound = 0.0;
        uses_parameter = true;
    }

    boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = user.getActiveMonster();
        return currentMon.getHP() > parameter;
    }
}
