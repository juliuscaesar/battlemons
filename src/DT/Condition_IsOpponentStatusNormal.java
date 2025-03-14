package DT;

import general.Battle;
import monsters.Monster;

import general.Status;
import trainers.Trainer;


public class Condition_IsOpponentStatusNormal extends Condition {
    public Condition_IsOpponentStatusNormal() {
        super();
        uses_parameter = false;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster currentOpponentMon = battle.getOpponentsMonster(user);
        return (currentOpponentMon.getStatus() == Status.Normal);
    }
}
