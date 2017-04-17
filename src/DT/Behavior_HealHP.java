package DT;

import general.Battle;
import general.Decision;
import monsters.Monster;
import trainers.FreshwaterItem;
import trainers.Trainer;

public class Behavior_HealHP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        Monster activeMonsterOfUser = user.getActiveMonster();
        FreshwaterItem replenishHP = new FreshwaterItem();

        return new Decision.UseHealHPItem(replenishHP,
                activeMonsterOfUser);
    }
}