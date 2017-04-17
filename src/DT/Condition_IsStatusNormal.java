package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import general.Status;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

public class Condition_IsStatusNormal extends Condition {
    public Condition_IsStatusNormal() {
        super();
        upper_bound = Double.MAX_VALUE;
        lower_bound = 0.0;
        uses_parameter = false;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = user.getActiveMonster();
        return (currentMon.getStatus() == Status.Normal);
    }
}
