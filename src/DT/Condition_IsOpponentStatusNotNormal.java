package DT;

import general.Battle;
import monsters.Monster;

import general.Status;
import trainers.Trainer;


public class Condition_IsOpponentStatusNotNormal extends Condition {
    public Condition_IsOpponentStatusNotNormal() {
        super();
        upper_bound = Double.MAX_VALUE;
        lower_bound = 0.0;
        uses_parameter = true;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster currentOpponentMon = battle.getOpponentsMonster(user);
        return (currentOpponentMon.getStatus() != Status.Normal);
    }
}
