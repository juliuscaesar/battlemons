package DT;

import java.util.List;

import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Element;
import general.Status;
import monsters.Monster;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
import trainers.Trainer;

public class Behavior_HealHP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        Monster activeMonsterOfUser = user.getActiveMonster();
        FreshwaterItem replenishHP = new FreshwaterItem();

        return new Decision.UseHealHPItem(replenishHP,
                activeMonsterOfUser);
    }
}