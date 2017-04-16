package DT;

import general.Battle;
import monsters.Monster;
import trainers.Trainer;

//Returns true if the user's current monster's health percentage is at
// least the parameter.
public class Condition_HealthGreaterThanPercent extends Condition {

    // Constructor
    public Condition_HealthGreaterThanPercent(float parameter) {
        super(parameter);
        upper_bound = 1.0;
        lower_bound = 0.0;
        uses_parameter = true;
    }

    boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = user.getActiveMonster();
        return currentMon.getPercentHP() > parameter;
    }
}