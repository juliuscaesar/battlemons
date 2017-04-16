package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import general.Status;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

public class Condition_IsOpponentStatusNormal extends Condition {
    public Condition_IsOpponentStatusNormal() {
        super();
        upper_bound = Double.MAX_VALUE;
        lower_bound = 0.0;
        uses_parameter = false;
    }

    boolean check_condition(Battle battle, Trainer user) {
        Monster currentOpponentMon = battle.getOpponentsMonster(user);
        return (currentOpponentMon.getStatus() == Status.Normal);
    }
}
