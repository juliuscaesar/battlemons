package DT;

import java.util.List;

import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Element;
import general.Status;
import monsters.Monster;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
import trainers.Trainer;

public class Behavior_HealStatus extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        Monster activeMonsterOfUser = user.getActiveMonster();
        IceHealItem cureFreeze = new IceHealItem();
        AwakeningItem cureSleep = new AwakeningItem();
        BurnHealItem cureBurn = new BurnHealItem();
        ParalyzHealItem cureParalysis = new ParalyzHealItem();
        Antidote curePoison = new Antidote();

        if (activeMonsterOfUser.getStatus().equals(Status.Freeze)) {
            return new Decision.UseIceHealItem(cureFreeze,
                    activeMonsterOfUser);
        }

        if (activeMonsterOfUser.getStatus().equals(Status.Sleep)) {
            return new Decision.UseSleepHealItem(cureSleep,
                    activeMonsterOfUser);
        }

        if (activeMonsterOfUser.getStatus().equals(Status.Burn)) {
            return new Decision.UseBurnHealItem(cureBurn,
                    activeMonsterOfUser);
        }

        if (activeMonsterOfUser.getStatus().equals(Status.Paralysis)) {
            return new Decision.UseParalyzHealItem(cureParalysis,
                    activeMonsterOfUser);
        }

        if (activeMonsterOfUser.getStatus().equals(Status.Poison)) {
            return new Decision.UsePoisonHealItem(curePoison,
                    activeMonsterOfUser);
        }

        return null;
    }
}
