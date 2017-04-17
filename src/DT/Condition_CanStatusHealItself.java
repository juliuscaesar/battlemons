package DT;

import general.Battle;
import monsters.Monster;

import java.util.List;

import general.Status;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

public class Condition_CanStatusHealItself extends Condition {
    public Condition_CanStatusHealItself() {
        super();
        upper_bound = Double.MAX_VALUE;
        lower_bound = 0.0;
        uses_parameter = true;
    }

    public boolean check_condition(Battle battle, Trainer user) {
        Monster currentMon = user.getActiveMonster();
        List<Item> itemsOnUser = user.listItems();

        for (Item item : itemsOnUser) {
            if (currentMon.getStatus().equals(Status.Poison)
                    && item.getItemEnum().equals(ItemEnum.Antidote)) {
                return true;
            }

            if (currentMon.getStatus().equals(Status.Burn)
                    && item.getItemEnum().equals(ItemEnum.BurnHeal)) {
                return true;
            }

            if (currentMon.getStatus().equals(Status.Freeze)
                    && item.getItemEnum().equals(ItemEnum.IceHeal)) {
                return true;
            }

            if (currentMon.getStatus().equals(Status.Paralysis)
                    && item.getItemEnum().equals(ItemEnum.ParalyzHeal)) {
                return true;
            }

            if (currentMon.getStatus().equals(Status.Poison)
                    && item.getItemEnum().equals(ItemEnum.Antidote)) {
                return true;
            }

        }

        return false;
    }

}
