package DT;

import general.Battle;
import monsters.Monster;

import general.Status;
import trainers.Trainer;


public class Condition_IsStatusNormal extends Condition {
    public Condition_IsStatusNormal() {
        super();
        uses_parameter = false;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = user.getActiveMonster();
        return (currentMon.getStatus() == Status.Normal);
    }
}
